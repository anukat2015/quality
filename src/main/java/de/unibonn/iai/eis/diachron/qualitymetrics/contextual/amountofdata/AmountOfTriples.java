package de.unibonn.iai.eis.diachron.qualitymetrics.contextual.amountofdata;

import java.util.List;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.sparql.core.Quad;

import de.unibonn.iai.eis.diachron.datatypes.ProblemList;
import de.unibonn.iai.eis.diachron.qualitymetrics.QualityMetric;
import de.unibonn.iai.eis.diachron.vocabularies.DAQ;
import de.unibonn.iai.eis.diachron.vocabularies.DQM;

/**
 * @author Nikhil Patra
 * 
 *         Amount of triples class counts the number of triples present in the
 *         dataset. It will check on the size of the dataset which is the lower
 *         bound for the number of triples present in the dataset.
 * 
 *         Metric Value: According to The range in which the size lies in.
 * 
 */
public class AmountOfTriples implements QualityMetric {
	
	//TODO: logging
	private final Resource METRIC_URI = DQM.AmountOfTriplesMetric; //TODO: define amount of triples
	private static Logger logger = Logger.getLogger(AmountOfTriples.class);
	protected double metricValue;
	protected int numTriples;

	// -- Stats taken from lod-cloud.net --//
	protected long high = 1000000000;
	protected long mediumHigh = 10000000;
	protected long mediumLow = 500000;
	protected long low = 10000;
	

	public void compute(Quad quad) {
			numTriples++;
	}

	public double metricValue() {

		if (numTriples > high)
			metricValue = 1;
		else if ((numTriples <= high) && (numTriples > mediumHigh))
			metricValue = 0.80;
		else if ((numTriples <= mediumHigh) && (numTriples > mediumLow))
			metricValue = 0.60;
		else if ((numTriples <= mediumLow) && (numTriples > low))
			metricValue = 0.40;
		else
			metricValue = 0.20;
		
		return metricValue;
	}

	public List<Statement> toDAQTriples() {
		// TODO Auto-generated method stub
		return null;
	}


	public Resource getMetricURI() {
		return this.METRIC_URI;
	}

	public ProblemList<?> getQualityProblems() {
		// TODO Auto-generated method stub
		return null;
	}

}
