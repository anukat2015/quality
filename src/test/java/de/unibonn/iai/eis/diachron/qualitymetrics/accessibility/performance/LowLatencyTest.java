package de.unibonn.iai.eis.diachron.qualitymetrics.accessibility.performance;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.hp.hpl.jena.sparql.core.Quad;

import de.unibonn.iai.eis.diachron.qualitymetrics.utilities.TestLoader;
import eu.diachron.qualitymetrics.accessibility.performance.LowLatency;

public class LowLatencyTest extends Assert {
	
	private static Logger logger = LoggerFactory.getLogger(LowLatencyTest.class);
	
	protected TestLoader loader = new TestLoader();
	protected LowLatency metric = new LowLatency();
	
	@Before
	public void setUp() throws Exception {
		loader.loadDataSet();
	}

	@After
	public void tearDown() throws Exception {
		// No clean-up required
	}

	@Test
	public void testLowLatency() {
		// Load quads...
		List<Quad> streamingQuads = loader.getStreamingQuads();
		int countLoadedQuads = 0;
		
		for(Quad quad : streamingQuads){
			// Here we start streaming triples to the quality metric
			metric.compute(quad);
			countLoadedQuads++;
		}
		logger.trace("Quads loaded, {} quads", countLoadedQuads);
		
		// Obtain the average measurement of the latency elicited by accessing the dataset's URI
		double metricValue = metric.metricValue();
		System.out.println("Computed low-latency metric: " + metricValue);

		assertTrue("Latency is out of range", (metricValue >= 0.0) && (metricValue <= 1.0));
	}

}
