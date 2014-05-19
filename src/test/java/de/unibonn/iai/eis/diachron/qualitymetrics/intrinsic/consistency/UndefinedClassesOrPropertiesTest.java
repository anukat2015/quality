package de.unibonn.iai.eis.diachron.qualitymetrics.intrinsic.consistency;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.hp.hpl.jena.sparql.core.Quad;

import de.unibonn.iai.eis.diachron.configuration.DataSetMappingForTestCase;
import de.unibonn.iai.eis.diachron.configuration.OutputFileMappingForQualityProblems;
import de.unibonn.iai.eis.diachron.qualitymetrics.utilities.TestLoader;
import de.unibonn.iai.eis.diachron.qualitymetrics.utilities.VocabularyReader;

/**
 * Test class for {@link de.unibonn.iai.eis.diachron.qualitymetrics.intrinsic.consistency.UndefinedClassesOrProperties#compute(com.hp.hpl.jena.sparql.core.Quad)}.
 * 
 * @author Muhammad Ali Qasmi
 * @date 11th March 2014
 */
public class UndefinedClassesOrPropertiesTest extends Assert {

	static Logger logger = Logger.getLogger(UndefinedClassesOrPropertiesTest.class);
	
	protected TestLoader loader = new TestLoader();
	protected UndefinedClassesOrProperties undefinedClassesOrProperties = new UndefinedClassesOrProperties(); 
	@Before
	public void setUp() throws Exception {
		BasicConfigurator.configure();
		loader.loadDataSet(DataSetMappingForTestCase.UndefinedClassesOrProperties);
	}

	@After
	public void tearDown() throws Exception {
		VocabularyReader.clear();
	}
	
	@Test
	/**
	 * Test method for {@link de.unibonn.iai.eis.diachron.qualitymetrics.intrinsic.consistency.UndefinedClassesOrProperties#compute(com.hp.hpl.jena.sparql.core.Quad)}.
	 */
	public final void testCompute() {
		List<Quad> streamingQuads = loader.getStreamingQuads();
		for(Quad quad : streamingQuads){
			undefinedClassesOrProperties.compute(quad);
		}
		assertEquals(0.076923076, undefinedClassesOrProperties.metricValue(), 0.00001);
	}
	
	@Test
	/**
     * Test method for {@link de.unibonn.iai.eis.diachron.qualitymetrics.intrinsic.consistency.UndefinedClassesOrProperties#compute(com.hp.hpl.jena.sparql.core.Quad)}.
     */
	public final void testOutProblematicInstancesToStream() {
	        try {
	                
	            List<Quad> streamingQuads = loader.getStreamingQuads();
	            for(Quad quad : streamingQuads){
	                    undefinedClassesOrProperties.compute(quad);
	            }
	                
	            OutputStream tmpStream = null;
	            tmpStream = new FileOutputStream(OutputFileMappingForQualityProblems.UndefinedClassesOrProperties);
	            undefinedClassesOrProperties.outProblematicInstancesToStream(DataSetMappingForTestCase.UndefinedClassesOrProperties,tmpStream);
	            tmpStream.close();
	        } catch (FileNotFoundException e) {
	                e.printStackTrace();
	        } catch (IOException e) {
	                e.printStackTrace();
	        }
	    }

}
