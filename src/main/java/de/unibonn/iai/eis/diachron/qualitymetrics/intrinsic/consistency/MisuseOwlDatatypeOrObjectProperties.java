package de.unibonn.iai.eis.diachron.qualitymetrics.intrinsic.consistency;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.core.Quad;

import de.unibonn.iai.eis.diachron.datatypes.ProblemList;
import de.unibonn.iai.eis.diachron.qualitymetrics.AbstractQualityMetric;

public class MisuseOwlDatatypeOrObjectProperties extends AbstractQualityMetric{

	private static String NAMESPACE_MATCH_SUBSTRING = "/owl#";
	private static String OWL_DATA_TYPE_PROPERTY = "datatypeproperty";
	private static String OWL_OBJECT_PROPERTY = "objectproperty";
	private List<Node> owlDatatypePropertyList = new ArrayList<Node>();
	private List<Node> owlObjectPropertyList = new ArrayList<Node>();
		
	protected long misuseDatatypeProperties = 0;
	protected long totalDatatypeProperties = 0;
	
	protected long misuseObjectProperties = 0;
	protected long totalObjectProperties = 0;
	
	public long getMisuseDatatypeProperties() {
		return misuseDatatypeProperties;
	}

	public long getTotalDatatypeProperties() {
		return totalDatatypeProperties;
	}

	public long getMisuseObjectProperties() {
		return misuseObjectProperties;
	}

	public long getTotalObjectProperties() {
		return totalObjectProperties;
	}
	
	protected static Logger logger = Logger.getLogger(MisuseOwlDatatypeOrObjectProperties.class);
	
	/**
	 * Constructor
	 * @param quadList
	 */
	public MisuseOwlDatatypeOrObjectProperties(List<Quad> quadList){
		this.filterAllOwlProperties(quadList);
	}
	
	public void clearAllOwlPropertiesList()
	{
		this.owlDatatypePropertyList.clear();
		this.owlObjectPropertyList.clear();
	}
	
	protected void filterAllOwlProperties(List<Quad> quadList){
		for(Quad quad : quadList){
			Node object = quad.getObject(); //retrieve predicate
			
			if(object.isURI()){ //check if predicate is URI
				// check if predicate refers to OWL namespace
				if ( object.getNameSpace().contains(NAMESPACE_MATCH_SUBSTRING) &&
						object.getURI().split("#").length > 1){

					// retrieve predicate value
					String tmpPropertyName = object.getURI().split("#")[1];
					if (tmpPropertyName.toLowerCase().equals(OWL_DATA_TYPE_PROPERTY.toLowerCase())){
						
						logger.debug(quad.getSubject() + " is of owl data type property");
						this.owlDatatypePropertyList.add(quad.getSubject());
					}
					else if (tmpPropertyName.toLowerCase().equals(OWL_OBJECT_PROPERTY.toLowerCase())){
						
						logger.debug(quad.getSubject()  + " is of owl object property");
						this.owlObjectPropertyList.add(quad.getSubject());						
					}
				}
			}
		}
	}
	
	public void compute(Quad quad) {
		Node predicate = quad.getPredicate();
		Node object = quad.getObject();
		//owl:DatatypeProperty relates some resource to a literal
		if (this.owlDatatypePropertyList.contains(predicate)){
			this.totalDatatypeProperties++;
			if (!object.isLiteral()){
				this.misuseDatatypeProperties++;
			}
		}
		// owl:ObjectProperty relates some resource another resource
		else if (this.owlObjectPropertyList.contains(predicate)){
			this.totalObjectProperties++;
			if (!object.isURI()){
				this.misuseObjectProperties++;
			}
		}
	}

	public double metricValue() {
		logger.trace("metricValue() --Started--");
		logger.debug("Number of Misuse Owl Datatype Properties :: " +  this.misuseDatatypeProperties);
		logger.debug("Total Owl Datatype Properties :: " +  this.totalDatatypeProperties);
		logger.debug("Number of Misuse Owl Object Properties :: " +  this.misuseObjectProperties);
		logger.debug("Total Owl Object Properties :: " +  this.totalObjectProperties);
		
		long tmpTotalMisusedProperties = this.misuseDatatypeProperties + this.misuseObjectProperties;
		long tmpTotalProperties = this.totalDatatypeProperties + this.totalObjectProperties;
		//return ZERO if total number of owl properties are ZERO [WARN]
		if (tmpTotalProperties <= 0) {
			logger.warn("Total number of owl properties in given document is found to be zero.");
			return 0.0;
		}
		
		double metricValue = (double) tmpTotalMisusedProperties / tmpTotalProperties;
		logger.debug("Metric Value :: " +  metricValue);
		logger.trace("metricValue() --Ended--");
		return metricValue;	}

	public Resource getMetricURI() {
		// TODO Auto-generated method stub
		return null;
	}

	public ProblemList<?> getQualityProblems() {
		// TODO Auto-generated method stub
		return null;
	}
}