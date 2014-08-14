package eu.diachron.qualitymetrics.representational.understandability;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.xerces.util.URI;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.core.Quad;

import de.unibonn.iai.eis.luzzu.assessment.QualityMetric;
import de.unibonn.iai.eis.luzzu.datatypes.ProblemList;
import de.unibonn.iai.eis.luzzu.exceptions.ProblemListInitialisationException;
import eu.diachron.semantics.vocabulary.DQM;
/**
 * WhitespaceInAnnotation consider the following widely used annotation
 * properties (labels, comments, notes, etc.) and identifies triples whose
 * property is from a pre-configured list of annotation properties, and whose
 * object value has leading or ending white space in string.
 * 
 * list of widely used annotation properties are stored in
 * ..src/main/resources/AnnotationPropertiesList.txt
 * 
 * Metric value Range = [0 - 1] Best Case = 0 Worst Case = 1
 * 
 * The metric value is defined as the ratio of annotations with whitespace to 
 * all annotations (triples having such properties).
 * 
 * This metric is from the list of constrains for scientific pilots and is
 * introduced in the Deliverable 3.1 (Table 20)
 * 
 * @author Muhammad Ali Qasmi
 * @date 19th June 2014
 */
public class WhitespaceInAnnotation implements QualityMetric {

	/**
	 * Metric URI
	 */
	private final Resource METRIC_URI = DQM.WhitespaceInAnnotationMetric;
	/**
	 * logger static object
	 */
	static Logger logger = Logger.getLogger(WhitespaceInAnnotation.class);
	/**
	 * list of problematic quads
	 */
	protected List<Quad> problemList = new ArrayList<Quad>();
	/**
	 * Default file path and name for the file that contains list of annotation
	 * properties
	 */
	protected static String defaultFilePathName = "src/main/resources/AnnotationPropertiesList.txt";
	/**
	 * Number of literals count
	 */
	protected long totalNumberOfLiterals = 0;
	/**
	 * Number of white space literals count
	 */
	protected long totalNumberOfWhitespaceLiterals = 0;
	/**
	 * list of annotation properties to be evaluated.
	 */
	protected static Set<String> annotationPropertiesSet = new HashSet<String>();

	/**
	 * loads list of annotation properties in set
	 * 
	 * if filePathName is null then default path will be used.
	 * 
	 * @param filePathName
	 *            - file Path and name, default :
	 *            src/main/resources/AnnotationPropertiesList.txt
	 */
	public static void loadAnnotationPropertiesSet(String filePathName) {
		File file = null;
		String tmpFilePathName = (filePathName == null) ? WhitespaceInAnnotation.defaultFilePathName
				: filePathName;
		try {
			if (!tmpFilePathName.isEmpty()) {
				file = new File(tmpFilePathName);
				if (file.exists() && file.isFile()) {
					String strLine = null;
					BufferedReader in = new BufferedReader(new FileReader(file));
					while ((strLine = in.readLine()) != null) {
						URI tmpURI = new URI(strLine);
						if (tmpURI != null) {
							WhitespaceInAnnotation.annotationPropertiesSet
									.add(strLine);
						}
					}
					in.close();
				}
			}
		} catch (FileNotFoundException e) {
			logger.debug(e.getStackTrace());
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.debug(e.getStackTrace());
			logger.error(e.getMessage());
		}
	}

	/**
	 * clears list of annotation properties in set
	 */
	public static void clearAnnotationPropertiesSet() {
		WhitespaceInAnnotation.annotationPropertiesSet.clear();
	}

	/**
	 * Checks whether given quad has predicate with URI found in annotation
	 * properties set if true then checks the object's value in that quad;
	 * whether it is whitespace or not.
	 * 
	 */
	
	public void compute(Quad quad) {
		try {
			Node predicate = quad.getPredicate();
			if (predicate.isURI()) { // check is the predicate is URI or not
				if (WhitespaceInAnnotation.annotationPropertiesSet
						.contains(predicate.getURI())) { // check if given
															// predicate is
															// found in
															// annotation
															// properties list
					Node object = quad.getObject();
					this.totalNumberOfLiterals++; // increment total number of
													// literals
					if (object.isLiteral()) { // check whether object is literal
												// or not
						String value = object.getLiteralValue().toString(); // retrieve
																			// object's
																			// value
						String trimValue = value.trim(); // removes whitespace
															// from both ends

						if (trimValue != null && !trimValue.isEmpty()) { // check
																			// if
																			// object's
																			// value
																			// is
																			// null
																			// or
																			// empty
							if (value.length() != trimValue.length()) { // compare
																		// length
																		// of
																		// both
																		// string
								this.totalNumberOfWhitespaceLiterals++; // increment
																		// whitespace
																		// literal
																		// count
								this.problemList.add(quad); // add invalid quad
															// in problem list
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.debug(e.getStackTrace());
			logger.debug(e.getMessage());
		}
	}

	/**
	 * metric value = total number of whitespace literals / total number of
	 * literals
	 * 
	 * @return ( (total number of whitespace literals) / (total number of
	 *         literals) )
	 */
	
	public double metricValue() {
		logger.debug("Total number of whitespace literals : "
				+ this.totalNumberOfWhitespaceLiterals);
		logger.debug("Total total number of literals : "
				+ this.totalNumberOfLiterals);
		if (this.totalNumberOfLiterals <= 0) {
			logger.warn("Total total number of literals are ZERO");
			return 0;
		}
		return ((double) this.totalNumberOfWhitespaceLiterals / (double) this.totalNumberOfLiterals);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.unibonn.iai.eis.diachron.qualitymetrics.AbstractQualityMetric#getMetricURI
	 * ()
	 */
	
	public Resource getMetricURI() {
		return this.METRIC_URI;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.unibonn.iai.eis.diachron.qualitymetrics.AbstractQualityMetric#
	 * getQualityProblems()
	 */
	
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
