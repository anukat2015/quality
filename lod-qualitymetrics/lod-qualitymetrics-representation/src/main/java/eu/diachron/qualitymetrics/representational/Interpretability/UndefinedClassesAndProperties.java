/**
 * 
 */
package eu.diachron.qualitymetrics.representational.Interpretability;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.core.Quad;
import com.hp.hpl.jena.vocabulary.RDF;

import de.unibonn.iai.eis.diachron.semantics.DQM;
import de.unibonn.iai.eis.luzzu.assessment.QualityMetric;
import de.unibonn.iai.eis.luzzu.datatypes.ProblemList;
import de.unibonn.iai.eis.luzzu.exceptions.ProblemListInitialisationException;
import de.unibonn.iai.eis.luzzu.semantics.vocabularies.QPRO;
import eu.diachron.qualitymetrics.representational.utils.SharedResources;
import eu.diachron.qualitymetrics.utilities.VocabularyLoader;

/**
 * @author Jeremy Debattista
 * 
 * This metric measures the number of undefined classes and
 * properties used by a data publisher in the assessed dataset.
 * By undefined classes and properties we mean that such resources
 * are used without any formal definition (e.g. using foaf:image 
 * instead of foaf:img).
 * 
 * 
 */
public class UndefinedClassesAndProperties implements QualityMetric {

	private int undefinedClasses = 0;
	private int undefinedProperties = 0;
	private int totalClasses = 0;
	private int totalProperties = 0;
	
	private static Logger logger = LoggerFactory.getLogger(UndefinedClassesAndProperties.class);
	private SharedResources shared = SharedResources.getInstance();
	private List<Quad> _problemList = new ArrayList<Quad>();

	
	@Override
	public void compute(Quad quad) {
		Node predicate = quad.getPredicate();
		
		
		if (predicate.hasURI(RDF.type.getURI())){
			// Checking for classes
			Node object = quad.getObject();
			logger.info("checking class: " + object.getURI());

			if (!(object.isBlank())){
				this.totalClasses++;
				
				Boolean seen = shared.classOrPropertyDefined(object.getURI());
				Boolean defined = null;
				if (seen == null) {
					defined = VocabularyLoader.checkTerm(object);
					shared.addClassOrProperty(object.getURI(), defined);
				}
				else defined = seen;
				
				if (!defined){
					this.undefinedClasses++;
					Quad q = new Quad(null, object, QPRO.exceptionDescription.asNode(), DQM.UndefinedClass.asNode());
					this._problemList.add(q);
				}
			}
			
		} else {
			// Checking for properties
			this.totalProperties++;
			logger.info("checking predicate: " + predicate.getURI());

			Boolean seen = shared.classOrPropertyDefined(predicate.getURI());
			Boolean defined = null;
			if (seen == null) {
				defined = VocabularyLoader.checkTerm(predicate);
				shared.addClassOrProperty(predicate.getURI(), defined);
			}
			else defined = seen;
			
			if (!defined){
				this.undefinedProperties++;
				Quad q = new Quad(null, predicate, QPRO.exceptionDescription.asNode(), DQM.UndefinedProperty.asNode());
				this._problemList.add(q);
			}
		}
	}

	@Override
	public double metricValue() {
		return (this.undefinedClasses + this.undefinedProperties == 0) ? 1.0 
				: 1.0 - ((double)(this.undefinedClasses + this.undefinedProperties)/(double)(this.totalClasses + this.totalProperties));
	}

	@Override
	public Resource getMetricURI() {
		return DQM.UndefinedClassesAndPropertiesMetric;
	}

	public ProblemList<?> getQualityProblems() {
		ProblemList<Quad> pl = null;
			try {
				pl = new ProblemList<Quad>(this._problemList);
			} catch (ProblemListInitialisationException e) {
				logger.error(e.getMessage());
			}
		return pl;
	}

	@Override
	public boolean isEstimate() {
		return false;
	}

	@Override
	public Resource getAgentURI() {
		return 	DQM.LuzzuProvenanceAgent;
	}
	
}
