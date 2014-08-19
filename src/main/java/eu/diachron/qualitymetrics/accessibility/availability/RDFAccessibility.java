/**
 * 
 */
package eu.diachron.qualitymetrics.accessibility.availability;

import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.sparql.core.Quad;

import de.unibonn.iai.eis.luzzu.assessment.QualityMetric;
import de.unibonn.iai.eis.luzzu.datatypes.ProblemList;
import de.unibonn.iai.eis.luzzu.exceptions.ProblemListInitialisationException;
import de.unibonn.iai.eis.luzzu.semantics.vocabularies.VOID;
import eu.diachron.qualitymetrics.utilities.HTTPConnector;
import eu.diachron.qualitymetrics.utilities.HTTPConnectorReport;
import eu.diachron.semantics.vocabulary.DQM;

/**
 * @author Jeremy Debattista
 * 
 *     Check if data dumps (void:dataDump) exists and are reachable and parsable.
 *     
 */
public class RDFAccessibility implements QualityMetric {
	
	static Logger logger = Logger.getLogger(RDFAccessibility.class);
	
	/**
	 * list of problematic quads
	 */
	protected List<Quad> problemList = new ArrayList<Quad>();

	private final Resource METRIC_URI = DQM.RDFAvailabilityMetric;
	
	private double metricValue = 0.0d;
	private double countRDF = 0.0d;
	private double positiveRDF = 0.0d;

	public void compute(Quad quad) {
		// TODO Meaningful error logging
		if (quad.getPredicate().getURI().equals(VOID.dataDump.getURI())) {

			countRDF++;
			
			HTTPConnectorReport report = HTTPConnector.connectToURI(quad.getObject().getURI(), "", false, true);
			if (report.getResponseCode() == 200) { positiveRDF++;}
			else {
				this.problemList.add(quad);
			}
			
		}

	}

	public double metricValue() {
		metricValue = positiveRDF / countRDF;

		return metricValue;
	}


	public Resource getMetricURI() {
		return this.METRIC_URI;
	}

	public ProblemList<?> getQualityProblems() {
		ProblemList<Quad> tmpProblemList = null;
		try {
			tmpProblemList = new ProblemList<Quad>(this.problemList);
		} catch (ProblemListInitialisationException problemListInitialisationException) {
			logger.debug(problemListInitialisationException.getStackTrace());
			logger.error(problemListInitialisationException.getMessage());
		}
		return tmpProblemList;
	}
	
}