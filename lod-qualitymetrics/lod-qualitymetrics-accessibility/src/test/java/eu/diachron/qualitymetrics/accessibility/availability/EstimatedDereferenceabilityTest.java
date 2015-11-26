package eu.diachron.qualitymetrics.accessibility.availability;

import java.util.List;

import eu.diachron.qualitymetrics.utilities.TestLoader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.hp.hpl.jena.sparql.core.Quad;

public class EstimatedDereferenceabilityTest extends Assert {
	
	
	protected TestLoader loader = new TestLoader();
	protected EstimatedDereferenceability metric = new EstimatedDereferenceability();

	@Before
	public void setUp() throws Exception {
		//loader.loadDataSet("testdumps/sample_deref.ttl");
		loader.loadDataSet("/Users/jeremy/Downloads/LAK-DATASET-DUMP.nt");
		
	}
	
	@Test
	public void testDereferenceability() {
		// Load quads...
		List<Quad> streamingQuads = loader.getStreamingQuads();
		int counter = 0;
		
		for(Quad quad : streamingQuads){
			metric.compute(quad);
			counter++;
		}
		System.out.println(counter);
		
		
		assertEquals(0.052,metric.metricValue(),0.001);
	}

}