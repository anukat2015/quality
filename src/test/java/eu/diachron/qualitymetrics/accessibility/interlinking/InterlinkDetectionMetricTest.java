/**
 * 
 */
package eu.diachron.qualitymetrics.accessibility.interlinking;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.hp.hpl.jena.sparql.core.Quad;

import eu.diachron.qualitymetrics.utilities.TestLoader;

/**
 * @author Jeremy Debattista
 * 
 */
public class InterlinkDetectionMetricTest extends Assert{
	
	protected TestLoader loader = new TestLoader();
	protected InterlinkDetectionMetric act_metric = new InterlinkDetectionMetric();

	@Before
	public void setUp() throws Exception {
		loader.loadDataSet();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInterlinkDetectionMetric() {
		List<Quad> streamingQuads = loader.getStreamingQuads();
		
		for(Quad quad : streamingQuads){
			// here we start streaming triples to the quality metric
			act_metric.compute(quad);
		}
		act_metric.metricValue();
		
//		assertEquals(1.0,metric.metricValue(), 0.0);
	}
}