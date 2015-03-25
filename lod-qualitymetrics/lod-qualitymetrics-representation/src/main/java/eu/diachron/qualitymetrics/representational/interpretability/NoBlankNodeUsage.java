/**
 * 
 */
package eu.diachron.qualitymetrics.representational.interpretability;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.core.Quad;
import com.hp.hpl.jena.vocabulary.RDF;

import de.unibonn.iai.eis.diachron.mapdb.MapDbFactory;
import de.unibonn.iai.eis.diachron.semantics.DQM;
import de.unibonn.iai.eis.luzzu.assessment.QualityMetric;
import de.unibonn.iai.eis.luzzu.datatypes.ProblemList;

/**
 * @author Jeremy Debattista
 * 
 * This metric calculates the number of blank nodes found in the subject or the object
 * of a triple. The metric value returns a value [0-1] where a higher number of blank nodes
 * will result in a value closer to 0.
 */
public class NoBlankNodeUsage implements QualityMetric {

	private static Logger logger = LoggerFactory.getLogger(NoBlankNodeUsage.class);
	
	//we will store all data level constraints that are URIs
	private Set<String> uniqueDLC = MapDbFactory.createFilesystemDB().createHashSet("unique-dlc-set").make();
	private Set<String> uniqueBN = MapDbFactory.createFilesystemDB().createHashSet("unique-bn-set").make();
	
	
	@Override
	public void compute(Quad quad) {
		Node predicate = quad.getPredicate();
		Node subject = quad.getSubject();
		Node object = quad.getObject();

		logger.debug("Assessing quad: " + quad.asTriple().toString());
		
		// we will skip all "typed" triples
		if (!(predicate.hasURI(RDF.type.getURI()))){
			if (subject.isBlank()) uniqueBN.add(subject.getBlankNodeLabel());
			else uniqueDLC.add(subject.getURI());
			
			if ((object.isURI())){
				if (object.isBlank()) uniqueBN.add(object.getBlankNodeLabel());
				else uniqueDLC.add(object.getURI());
			}
		}
	}

	@Override
	public double metricValue() {
		return ((double) uniqueDLC.size()) / ((double) uniqueDLC.size() + (double) uniqueBN.size());
	}

	@Override
	public Resource getMetricURI() {
		return DQM.NoBlankNodeMetric;
	}

	@Override
	public ProblemList<?> getQualityProblems() {
		return null;
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