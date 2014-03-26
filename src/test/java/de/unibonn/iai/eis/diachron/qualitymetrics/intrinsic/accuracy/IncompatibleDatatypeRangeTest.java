package de.unibonn.iai.eis.diachron.qualitymetrics.intrinsic.accuracy;

import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.hp.hpl.jena.sparql.core.Quad;

import de.unibonn.iai.eis.diachron.configuration.DataSetMappingForTestCase;
import de.unibonn.iai.eis.diachron.qualitymetrics.utilities.TestLoader;

/**
 * @author Muhammad Ali Qasmi
 * @date 20th Feb 2014
 */
public class IncompatibleDatatypeRangeTest extends Assert {

	static Logger logger = Logger.getLogger(IncompatibleDatatypeRangeTest.class);
	
	protected TestLoader loader = new TestLoader();
	IncompatibleDatatypeRange incompatibleDatatypeRange = new IncompatibleDatatypeRange();
	
	@Before
	public void setUp() throws Exception {
		BasicConfigurator.configure();
		loader.loadDataSet(DataSetMappingForTestCase.IncompatibleDatatypeRange);
	}

	@After
	public void tearDown() throws Exception {
		IncompatibleDatatypeRange.clearCache();
	}

	/**
	 * 
	 */
	@Test
	public final void testCompute() {
		logger.trace("testCompute() --Started--");
		List<Quad> streamingQuads = loader.getStreamingQuads();
		for(Quad quad : streamingQuads){
			incompatibleDatatypeRange.compute(quad);
		}
		logger.info("Incompatible Data Type Literals ::" + incompatibleDatatypeRange.getIncompatiableLiterals());
		logger.info("Total Literals ::" + incompatibleDatatypeRange.getTotalLiterals());
		logger.info("Metric Value ::" + incompatibleDatatypeRange.metricValue());
		assertEquals(0.153846153,incompatibleDatatypeRange.metricValue(), 0.00001);
		logger.trace("testCompute() --Ended--");
	}

}