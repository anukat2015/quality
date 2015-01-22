package eu.diachron.qualitymetrics.accessibility.interlinking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.core.Quad;
import com.hp.hpl.jena.vocabulary.RDF;

import de.unibonn.iai.eis.diachron.commons.bigdata.ReservoirSampler;
import de.unibonn.iai.eis.luzzu.assessment.QualityMetric;
import de.unibonn.iai.eis.luzzu.datatypes.ProblemList;
import eu.diachron.qualitymetrics.accessibility.availability.ResourceBaseURIOracle;
import eu.diachron.semantics.vocabulary.DQM;

/**
 * Measures the degree to which the resource is linked to external data providers, 
 * that is, refers to the detection of links that connect the resource to external data provided by another data sources.
 * 
 * Based on: [1] Hogan Aidan, Umbrich Jürgen. An empirical survey of Linked Data conformance. Section 5.2, 
 * Linking, Issue VI: Use External URIs (page 26).
 * @author Santiago Londoño 
 */
public class EstimatedLinkExternalDataProviders implements QualityMetric {
	
	private final Resource METRIC_URI = DQM.LinksToExternalDataProvidersMetric;
	
	final static Logger logger = LoggerFactory.getLogger(EstimatedLinkExternalDataProviders.class);
	
	/**
	 * Parameter: default size of the Bloom filters, determines the precision of the estimations
	 */
	private static int reservoirSize = 5000;
		
	/**
	* A reservoir containing the top-level domains (or base URIs) found among all the data-level constants of the 
	* dataset (data level constants are: subjects of triples and objects of triples not subject to an rdf:type predicate)
	*/
	private ReservoirSampler<String> reservoirTldRIs = new ReservoirSampler<String>(reservoirSize, true);
	
	/**
     * Object used to determine the base URI of the resource based on its contents
     */
	private ResourceBaseURIOracle baseURIOracle = new ResourceBaseURIOracle();
	
	/**
	 * Counts the total number of data-level constant URIs found among the triples of the resource
	 */
	private int totalDataLevelConstURIs = 0;

	/**
	 * Processes a single quad making part of the dataset. Determines whether the subject and/or object of the quad 
	 * are data-level URIs, if so, extracts their pay-level domain and adds them to the set of TLD URIs.
	 * @param quad Quad to be processed as part of the computation of the metric
	 */
	public void compute(Quad quad) {

		// Feed the base URI oracle, which will be used to determine the resource's base URI
		this.baseURIOracle.addHint(quad);

		// Extract the URIs of the subject and object of the quad
		String subjectURI = (quad.getSubject().isURI())?(quad.getSubject().getURI()):("");
		String objectURI = (quad.getObject().isURI())?(quad.getObject().getURI()):("");
		
		// Process subject URI
		if(!subjectURI.equals("")) {
			// Extract the TLD of the subject's URI and process it
			this.processTopLevelDomain(ResourceBaseURIOracle.extractPayLevelDomainURI(subjectURI));
			this.totalDataLevelConstURIs++;
		}
		
		// As this metric is defined to account for data-level URIs, check that the predicate is not rdf:type
		// (as per the definition of data-level constant provided by Hogan et al. [1], section 3.1.3, pg. 6)
		if(quad.getPredicate().isURI() && !quad.getPredicate().getURI().equals(RDF.type.getURI())) {
			
			// Process object URI
			if(!objectURI.equals("")) {
				// Extract the TLD of the object's URI and process it, a new data-level TLD has been found
				logger.trace("Data-level URI found in object: {} with predicate: {}", objectURI, quad.getPredicate().getURI());
				this.processTopLevelDomain(ResourceBaseURIOracle.extractPayLevelDomainURI(objectURI));
				this.totalDataLevelConstURIs++;
			}
		}
	}

	/**
	 * Compute the value of the metric as the ratio between the number of different TLDs found among the data-level 
	 * constants of the resource that are different of the resource's TLD and the total number of 
	 * data-level constant URIs found in the resource.
	 * @return value of the existence of links to external data providers metric computed on the current resource
	 */	
	public double metricValue() {
		
		// Determine the current resource's TLD
		String resBaseURI = this.baseURIOracle.getEstimatedResourceBaseURI();
		String resTLD = ResourceBaseURIOracle.extractPayLevelDomainURI(resBaseURI);
		
		// Count the number of external TLDs found in the resource
		int totalExtTLDs = 0;		
		
		for(String curTLD : this.reservoirTldRIs.getItems()) {
			
			// If currently examined TDL does not belong to the resource's TLD (or vice-versa), the current TLD is external
			logger.debug("Comparing TLD in triples: {} with resource's TLD: {}", curTLD, resTLD);
			
			if(!(curTLD.contains(resTLD) || resTLD.contains(curTLD))) {
				logger.debug("OK, Top-level domain {} is external", curTLD);
				totalExtTLDs++;
			}
		}
		
		// Reservoir sampling starts discarding URIs after the reservoir gets filled, in order to prevent it from
		// growing beyond its maximum size. If that's the case, the size of the reservoir is to be used as base in the
		// computation of the external TLDs ratio, instead of the actual total number of data-level constant URIs found
		int baseCountDataLevelConstURIs = this.totalDataLevelConstURIs;
		
		if(this.reservoirTldRIs.isFull()) {
			baseCountDataLevelConstURIs = this.reservoirTldRIs.size();
		}
		
		if(this.totalDataLevelConstURIs > 0) {
			return (double)totalExtTLDs / ((double)baseCountDataLevelConstURIs);
		} else {
			return 0.0;
		}
	}

	public Resource getMetricURI() {
		return this.METRIC_URI;
	}

	public ProblemList<?> getQualityProblems() {
		// TODO Auto-generated method stub
		return null;
	}
		
	public static int getReservoirSize() {
		return reservoirSize;
	}

	public static void setReservoirSize(int reservoirSize) {
		EstimatedLinkExternalDataProviders.reservoirSize = reservoirSize;
	}

	/**
	 * Verifies a TLD found in a subject or object and decides if must be added to the set of pay-level domain URIs
	 */
	private boolean processTopLevelDomain(String tld) {
		
		boolean wasAdded = false;
		
		if(tld != null && tld.length() > 0) {
			// Determine whether the top-level domain already exists in the reservoir...
			if(this.reservoirTldRIs.findItem(tld) == null) {
				// and add it, if not found
				wasAdded = this.reservoirTldRIs.add(tld);
				logger.debug("New TLD added to reservoir: {}", tld);
			} else {
				logger.debug("TLD already in the reservoir: {}", tld);
				wasAdded = true;
			}
		}
		
		return wasAdded;
	}

}
