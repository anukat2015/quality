package eu.diachron.qualitymetrics.intrinsic.consistency;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.datatypes.RDFDatatype;
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
 * * This metric reports outliers or conflicts of literal datatypes. Since it is
 * meant to measure the homogeneity and not the validity possible rdfs:range
 * restrictions are not evaluated.
 * 
 * This metric makes two distinctions as far as homogeneity is concerned: 1) if
 * there are just a few triples of one predicate having a datatype different
 * from all other triples, they are considered as outliers and are all reported
 * 2) if there is no obvious ratio of possibly wrong and possibly right triples
 * the conflicting view candidates are reported
 * 
 * Metric Value Range : [0 - 1]
 * Best Case : 0
 * Worst Case : 1
 * 
 * @author Muhammad Ali Qasmi
 * @date 12th May 2014
 */
public class HomogeneousDatatypes implements QualityMetric {
	/**
	 * Metric URI
	 */
	private final Resource METRIC_URI = DQM.HomogeneousDatatypesMetric;
	/**
	 * logger static object
	 */
	static Logger logger = Logger.getLogger(HomogeneousDatatypes.class);
	/**
	 * threshold value to declare whether a property is heterogeneous or not.
	 */
	protected static Long THRESHOLD = new Long(99);
	/**
	 * number of properties with heterogeneous data type
	 */
	protected long propertiesWithHeterogeneousDatatype = 0;
	/**
	 * total number of properties
	 */
	protected long totalProperties = 0;
	/**
	 * data structure store node w.r.t. it rdf data type and also maintain it
	 * the total number of hits (counts) for the given property in a collection
	 * of quads.
	 */
	protected Hashtable<Node, Hashtable<RDFDatatype, Long>> propertiesDatatypeMatrix = new Hashtable<Node, Hashtable<RDFDatatype, Long>>();
	/**
	 * list of problematic nodes
	 */
	protected List<Node> problemList = new ArrayList<Node>();

	/**
	 * This method extracts properties from a given quad and stores it into the
	 * hash table with required information e.g. rdf type, count.
	 */
	
	public void compute(Quad quad) {
		logger.trace("compute() --Started--");
		try {

			Node predicate = quad.getPredicate(); // retrieve predicate
			Node object = quad.getObject(); // retrieve object
			if (object.isLiteral()) {

				this.totalProperties++;

				// retrieves rdfDataType from literal
				RDFDatatype rdfdataType = object.getLiteralDatatype();

				// check if rdf data type is a valid data type
				if (rdfdataType != null) {

					if (propertiesDatatypeMatrix.containsKey(predicate)) { // matrix
																			// contains
																			// given
																			// object
						Hashtable<RDFDatatype, Long> tmpObjectTypes = propertiesDatatypeMatrix
								.get(predicate);
						if (tmpObjectTypes != null) { // given datatype
														// association already
														// exists

							Long tmpCount = new Long(0); // datatype count
															// initial value
															// ZERO

							if (tmpObjectTypes.containsKey(rdfdataType)) {
								tmpCount = tmpObjectTypes.get(rdfdataType);
								tmpCount++;

								tmpObjectTypes.remove(rdfdataType);
								tmpObjectTypes.put(rdfdataType, tmpCount);
							} else {
								tmpCount++;
								tmpObjectTypes.put(rdfdataType, tmpCount);
							}
							propertiesDatatypeMatrix.remove(predicate);
							propertiesDatatypeMatrix.put(predicate,
									tmpObjectTypes);
						} else { // given datatype association does NOT exists

							tmpObjectTypes = new Hashtable<RDFDatatype, Long>();
							tmpObjectTypes.put(rdfdataType, new Long(1));

							propertiesDatatypeMatrix.remove(predicate);
							propertiesDatatypeMatrix.put(predicate,
									tmpObjectTypes);
						}
					} else {// matrix does not contain given object
						Hashtable<RDFDatatype, Long> tmpObjectTypes = new Hashtable<RDFDatatype, Long>();
						tmpObjectTypes.put(rdfdataType, new Long(1));
						propertiesDatatypeMatrix.put(predicate, tmpObjectTypes);
					}
				}
			}
		} catch (Exception exception) {
			logger.debug(exception);
			logger.error(exception.getMessage());
		}
		logger.trace("compute() --Ended--");
	}

	/**
	 * This method identifies whether a given property is heterogeneous or not.
	 * 
	 * @param givenTable
	 *            - property (its rdf type and its count)
	 * @param threshold
	 *            - to declare a property as heterogeneous
	 * @return true - if heterogeneous
	 */
	protected boolean isHeterogeneousDataType(
			Hashtable<RDFDatatype, Long> givenTable, Long threshold) {

		Long tmpMax = new Long(0); // for count of Max dataType
		Long tmpTotal = new Long(0); // for count of total

		Enumeration<RDFDatatype> enumKey = givenTable.keys();

		while (enumKey.hasMoreElements()) {
			RDFDatatype key = enumKey.nextElement();
			Long value = givenTable.get(key);
			tmpMax = (value > tmpMax) ? value : tmpMax; // get Max Datatype
			tmpTotal += value; // count total
		}

		return (((tmpMax / tmpTotal) * 100) >= threshold) ? true : false;
	}

	/**
	 * This method counts number of heterogeneous data type properties
	 * 
	 * @return
	 */
	protected long countHeterogeneousDataTypePropeties() {
		long tmpCount = 0;
		Enumeration<Node> enumKey = propertiesDatatypeMatrix.keys();
		while (enumKey.hasMoreElements()) {
			Node key = enumKey.nextElement();
			if (!isHeterogeneousDataType(propertiesDatatypeMatrix.get(key),
					HomogeneousDatatypes.THRESHOLD)) {
				tmpCount++;
				this.problemList.add(key);
			}
		}
		return tmpCount;
	}

	/**
	 * Returns metric value for the object of this class
	 * 
	 * @return (number of heterogeneous properties ) / (total number of
	 *         properties)
	 */
	
	public double metricValue() {

		logger.trace("metricValue() --Started--");

		this.propertiesWithHeterogeneousDatatype = countHeterogeneousDataTypePropeties();

		// return ZERO if total number of properties are ZERO [WARN]
		if (totalProperties <= 0) {
			logger.warn("Total number of properties in given document is found to be zero.");
			return 0.0;
		}

		double metricValue = (double) propertiesWithHeterogeneousDatatype
				/ totalProperties;

		logger.trace("metricValue() --Ended--");

		return metricValue;
	}

	/**
	 * Returns Metric URI
	 * 
	 * @return metic URI
	 */
	
	public Resource getMetricURI() {
		return this.METRIC_URI;
	}

	/**
	 * Returns list of problematic quads
	 * 
	 * @return list of problematic quads
	 */
	
	public ProblemList<?> getQualityProblems() {
		ProblemList<Node> tmpProblemList = null;
		try {
			tmpProblemList = new ProblemList<Node>(this.problemList);
		} catch (ProblemListInitialisationException problemListInitialisationException) {
			logger.debug(problemListInitialisationException);
			logger.error(problemListInitialisationException.getMessage());
		}
		return tmpProblemList;
	}

}
