package eu.diachron.qualitymetrics.intrinsic.consistency;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.hp.hpl.jena.sparql.core.Quad;

import eu.diachron.qualitymetrics.utilities.TestLoader;

public class EstimateEntitiesAsMembersOfDisjointClassesTest extends Assert{
	
	protected TestLoader loader = new TestLoader();
	protected EstimateSimpleEntitiesAsMembersOfDisjointClasses metric = new EstimateSimpleEntitiesAsMembersOfDisjointClasses();


	@Before
	public void setUp() throws Exception {
		loader.loadDataSet("testdumps/SampleInput_EntitiesAsMembersOfDisjointClasses_Minimal.ttl");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * a minimal example for entities being members of disjoint classes: one entity is (of foaf:Person and foaf:Document), one isn't.
	 * 
	 * Note that the FOAF vocabulary has been published as LOD, and that foaf:Person is explicitly declared disjoint with foaf:Document.
	 */
	@Test
	public void testEntitiesAsMembersOfDisjointClassesMinimalExample() {
		List<Quad> streamingQuads = loader.getStreamingQuads();
		
		for(Quad quad : streamingQuads){
			// here we start streaming triples to the quality metric
			metric.compute(quad);
		}
		
		// 1 / 2
		assertEquals(0.5,metric.metricValue(), 0.0001);
	}	


}