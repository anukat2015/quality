package de.unibonn.iai.eis.diachron.io;

import java.util.Hashtable;

import org.apache.jena.atlas.web.HttpException;
import org.apache.jena.riot.RiotException;
import org.apache.log4j.Logger;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import de.unibonn.iai.eis.diachron.qualitymetrics.intrinsic.consistency.UndefinedClassesOrProperties;

/**
 * 
 * @author Muhammad Ali Qasmi
 * @date 13th March 2014
 */
public class VocabularyReader {
	
	static Logger logger = Logger.getLogger(UndefinedClassesOrProperties.class);
	static Hashtable<String, Model>vocabularies = new Hashtable<String, Model>();
	
	/**
	 * 
	 */
	public static void clear(){
		VocabularyReader.vocabularies.clear();
	}
	
	/**
	 * Reads vocabulary from given URL or cache
	 * 
	 * @param url
	 * @return model
	 */
	public static Model read(String url){
		Model model = null;
        try {
	        if (VocabularyReader.vocabularies.containsKey(url)){
	        	logger.debug(url + " :: vocabulary loaded from cache.");
	        	return VocabularyReader.vocabularies.get(url);
	        }
	        else {
	        	logger.debug(url + " :: vocabulary loaded from web.");
	        	model = ModelFactory.createDefaultModel();
	        	model.read(url);
	        	VocabularyReader.vocabularies.put(url, model);
	        }
        }
        catch (RiotException roitException){
        	logger.debug(url + " :: unable to loaded vocabulary from web.");
        	logger.debug(roitException);
        	logger.fatal(roitException.getMessage());
        	VocabularyReader.vocabularies.put(url, null);
        	return null;
        }
        catch (HttpException httpException){
        	logger.debug(url + " :: unable to loaded vocabulary from web.");
        	logger.debug(httpException);
        	logger.error(httpException.getMessage());
        	return null;
        }
        catch (NullPointerException nullPointerException){
        	logger.debug(url + " :: unable to loaded vocabulary from web.");
        	logger.debug(nullPointerException);
        	logger.error(nullPointerException.getMessage());
        	return null;
        }
        return model;
	}
}
