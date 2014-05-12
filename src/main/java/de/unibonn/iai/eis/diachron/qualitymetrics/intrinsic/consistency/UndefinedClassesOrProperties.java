package de.unibonn.iai.eis.diachron.qualitymetrics.intrinsic.consistency;



import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.core.Quad;
import com.hp.hpl.jena.vocabulary.RDFS;

import de.unibonn.iai.eis.diachron.datatypes.ProblemList;
import de.unibonn.iai.eis.diachron.exceptions.ProblemListInitialisationException;
import de.unibonn.iai.eis.diachron.qualitymetrics.AbstractQualityMetric;
import de.unibonn.iai.eis.diachron.qualitymetrics.utilities.VocabularyReader;

/**
 * @author Muhammad Ali Qasmi
 * @date 11th March 2014
 */
public class UndefinedClassesOrProperties extends AbstractQualityMetric{
	
	static Logger logger = Logger.getLogger(UndefinedClassesOrProperties.class);
		
	protected long undefinedClassesCount = 0;
	protected long totalClassesCount = 0;
	protected long undefinedPropertiesCount = 0;
	protected long totalPropertiesCount = 0;
	
	protected List<Quad> problemList = new ArrayList<Quad>();
	
	public void compute(Quad quad) {
		
		logger.trace("compute() --Started--");
		
		try {
			
			Node subject = quad.getSubject(); //retrieve subject
			Node predicate = quad.getPredicate(); //retrieve predicate
			Node object = quad.getObject(); //retrieve object
			
			
			if (subject.isURI()){ //check if subject is URI (not Blank)
				this.totalClassesCount++;
				//load model
				Model subjectModel = VocabularyReader.read(subject.getURI());
				if (subjectModel != null){ //check if system is able to retrieve model
					// search for URI resource from Model
					if (!subjectModel.getResource(subject.getURI()).isURIResource()){
						this.undefinedClassesCount++;
						this.problemList.add(quad);
					}
				}
			}
			
			if(predicate.isURI()){ //check if predicate is URI
				this.totalPropertiesCount++;
				//load model
				Model predicateModel = VocabularyReader.read(predicate.getURI());
				if (predicateModel  != null){ //check if system is able to retrieve model
					// search for URI resource from Model					
					if(predicateModel.getResource(predicate.getURI()).isURIResource()) {
						// search for its domain and range properties
						if (!( predicateModel.getResource(predicate.getURI()).hasProperty(RDFS.domain) && 
							 predicateModel.getResource(predicate.getURI()).hasProperty(RDFS.range))) {
							this.undefinedPropertiesCount++;
							this.problemList.add(quad);
						}
					}
				}
			}
			
			
			if (object.isURI()){ //check if object is URI (not blank or literal)
				this.totalClassesCount++;
				//load model
				Model objectModel =  VocabularyReader.read(object.getURI());
				if (objectModel != null){ //check if system is able to retrieve model
					// search for URI resource from Model
					if (!objectModel.getResource(object.getURI()).isURIResource()){
						this.undefinedClassesCount++;
						this.problemList.add(quad);
					}
				}
			}
		
		}
		catch (Exception exception){
			logger.debug(exception);
        	logger.error(exception.getMessage());
		}
		
		logger.trace("compute() --Ended--");
	}

	public double metricValue() {
		
		logger.trace("metricValue() --Started--");
		logger.debug("Number of Undefined Classes :: " +  this.undefinedClassesCount);
		logger.debug("Number of Classes :: " +  this.totalClassesCount);
		logger.debug("Number of Undefined Properties :: " +  this.undefinedPropertiesCount);
		logger.debug("Number of Properties :: " +  this.totalPropertiesCount);
		
		long tmpTotalUndefinedClassesAndUndefinedProperties = this.undefinedClassesCount + this.undefinedPropertiesCount;
		long tmpTotalClassesAndProperties = this.totalClassesCount + this.totalPropertiesCount;
		//return ZERO if total number of RDF literals are ZERO [WARN]
		if (tmpTotalClassesAndProperties <= 0) {
			logger.warn("Total number of classes and properties in given document is found to be zero.");
			return 0.0;
		}
		
		double metricValue = (double) tmpTotalUndefinedClassesAndUndefinedProperties / tmpTotalClassesAndProperties;
		logger.debug("Metric Value :: " +  metricValue);
		logger.trace("metricValue() --Ended--");
		return metricValue;
	}
	
	/**
	 * 
	 */
	public Resource getMetricURI() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns list of problematic Quads
	 */
	public ProblemList<?> getQualityProblems() {
		ProblemList<Quad> tmpProblemList = null;
		try {
			tmpProblemList = new ProblemList<Quad>(this.problemList); 
		} 
		catch (ProblemListInitialisationException problemListInitialisationException){
			logger.debug(problemListInitialisationException);
        	logger.error(problemListInitialisationException.getMessage());
		}
		return tmpProblemList;
	}

}