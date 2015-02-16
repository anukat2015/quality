/**
 * 
 */
package eu.diachron.qualitymetrics.accessibility.performance;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.core.Quad;

import de.unibonn.iai.eis.diachron.semantics.DQM;
import de.unibonn.iai.eis.luzzu.assessment.QualityMetric;
import de.unibonn.iai.eis.luzzu.datatypes.ProblemList;
import de.unibonn.iai.eis.luzzu.exceptions.ProblemListInitialisationException;

/**
 * @author Jeremy Debattista
 * 
 * This metric checks if a dataset uses slash URIs as identifiers for the resources
 * described in its dataset. According to Flemming, Hash URIs are convenient for small datasets
 * where a document contains data about more than one entity.
 * On the other hand, when dealing with large datasets, files might be too large to load a "hash" 
 * part from the document, therefore a Slash URI would be more appropriate since resources will
 * result into smaller documents (related to that resource) and thus loading faster.
 * Having said that, slash URIs would then require an additional 303 redirect. Overall, this
 * will influence the performance of a source when retrieving resources.
 * 
 * The document CoolURIs [http://www.w3.org/TR/cooluris] (specifically section 4 and 4.4) 
 * describes HashURIs and 303 Redirects (Slash URIs) in more detail. Hash URIs are preferred 
 * for small and stable datasets (such as vocabularies) since they provide stability to 
 * the retrieval performance, without the extra 303 redirects. On the other hand, slash uri's are 
 * preferred for large datasets, as at a point it will not be practical to "serve all related 
 * resource in a single document".
 * 
 * This metric will count the number of triples in a dataset, and if the count is > 500K triples,
 * then Slash URIs are required. Therefore, the metric will return 1.0 (or true) if slash uri's
 * are used when a dataset has a large amount of data or hash uri's are used if otherwise.
 */
public class CorrectURIUsage implements QualityMetric {

	final static Logger logger = LoggerFactory.getLogger(CorrectURIUsage.class);

	/**
	 * MapDB database, used to persist the Map containing the instances found to be declared in the dataset
	 */
	transient private static DB mapDB = DBMaker.newTempFileDB()
			.closeOnJvmShutdown()
			.deleteFilesAfterClose()
        	.make();
	

	 private Set<Resource> pSetHashURI = mapDB.createHashSet("HashURISet").make();
	 private Set<Resource> pSetSlashURI = mapDB.createHashSet("SlashURISet").make();

	 private List<Resource> _problemList = new ArrayList<Resource>();

	 private final Resource METRIC_URI = DQM.CorrectURIUsage;

	
	/**
	 * Setting for the maximum number of triples to be considered as large
	 */
	private static final int MAX_TRIPLES = 500000; 
	
	/**
	 * A triple Counter
	 */
	private Long tripleCounter = 0l;
	
	/**
	 * Slash URI Counter
	 */
	private Long slashURICounter = 0l;
	
	/**
	 * Hash URI Counter
	 */
	private Long hashURICounter = 0l;
	

	
	/** 
	 * For this metric we extract the subject that represents the resource.
	 * If the subject is a blank node, we just skip it, as blank nodes cannot
	 * be dereferenced.
	 * Else we increment the counter and check if it is a Slash URI or a Hash URI. 
	 */
	@Override
	public void compute(Quad quad) {
		String subject = (quad.getSubject().isURI()) ? quad.getSubject().getURI() : "";
		if (!(subject.equals(""))){
			logger.debug("Processing triple with subject URI: {}.", subject);

			this.tripleCounter++;
			
			// URIs ending in slash are valid, yet would be problematic to dissect. Remove trailing slash if found
			if(subject.endsWith("/")) {
				subject = subject.substring(0, subject.length() - 1);
			}
						
			if(!subject.equals("")) {
				// Only hierarchical URIs will be considered in the computation of the metric. Non-hierarchical URIs are not accounted for, 
				// as the fact that they do not represent a hierachy of resources, elicits that they cannot involve several de-reference steps
				int lastIndexOfSlash = subject.lastIndexOf('/');
				logger.debug("Analyzing hierarchical URI: {}. Last Index of /: {}", subject, lastIndexOfSlash);
				
				if(lastIndexOfSlash >= 0) {
					// Extract the resource name part and the scheme+path from the URI
					String schemePath = subject.substring(0, lastIndexOfSlash);
					String resourceName = subject.substring(lastIndexOfSlash + 1);
															
					// Decide whether the URI is a hash or slash URI: hash URIs are those containing a # character before the last word
					boolean isHashURI = (resourceName.lastIndexOf('#') >= 0);
					logger.debug("Hierarchical URI with path: {} and resource name: {}. Is Hash URI: {}", schemePath, resourceName, isHashURI);
					
					if (isHashURI) {
						this.pSetHashURI.add(ModelFactory.createDefaultModel().createResource(subject));
						this.hashURICounter++; 
					} else {
						this.pSetSlashURI.add(ModelFactory.createDefaultModel().createResource(subject));
						this.slashURICounter++;
					}
				}
			}
		} else {
			logger.debug("Subject is not a URI: {}. Not Processed", subject);

		}
		
	}

	@Override
	public double metricValue() {
		if (this.tripleCounter >= MAX_TRIPLES){
			if (this.slashURICounter == this.tripleCounter) return 1.0;
			else {
				this._problemList.addAll(this.pSetHashURI);
				return 0.0;
			}
		} else {
			if (this.hashURICounter == this.tripleCounter) return 1.0;
			else {
				this._problemList.addAll(this.pSetSlashURI);
				return 0.0;
			}
		}
	}

	
	@Override
	public Resource getMetricURI() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public ProblemList<?> getQualityProblems() {
		ProblemList<Resource> pl = null;
		try {
			pl = new ProblemList<Resource>(this._problemList);
		} catch (ProblemListInitialisationException e) {
//			logger.debug(e.getStackTrace());
			logger.error(e.getMessage());
		}
		return pl;
	}

}