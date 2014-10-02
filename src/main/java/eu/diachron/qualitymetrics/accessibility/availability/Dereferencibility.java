/**
 * 
 */
package eu.diachron.qualitymetrics.accessibility.availability;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.core.Quad;
import com.hp.hpl.jena.vocabulary.RDF;

import de.unibonn.iai.eis.diachron.datatypes.StatusCode;
import de.unibonn.iai.eis.luzzu.assessment.QualityMetric;
import de.unibonn.iai.eis.luzzu.datatypes.ProblemList;
import eu.diachron.qualitymetrics.cache.CachedHTTPResource;
import eu.diachron.qualitymetrics.cache.DiachronCacheManager;
import eu.diachron.qualitymetrics.utilities.HTTPRetriever;
import eu.diachron.semantics.vocabulary.DQM;

/**
 * @author Jeremy Debatista
 * 
 * This metric calculates the number of valid redirects (303) or hashed links
 * according to LOD Principles
 * 
 * Based on: <a href="http://www.hyperthing.org/">Hyperthing - A linked data Validator</a>
 * 
 * @see <a href="http://dl.dropboxusercontent.com/u/4138729/paper/dereference_iswc2011.pdf">
 * Dereferencing Semantic Web URIs: What is 200 OK on the Semantic Web? - Yang et al.</a>
 * 
 */
public class Dereferencibility implements QualityMetric {
	
	private final Resource METRIC_URI = DQM.DereferenceabilityMetric;

	final static Logger logger = LoggerFactory.getLogger(Dereferencibility.class);
	
	private double metricValue = 0.0;
	private double totalURI = 0;
	private double dereferencedURI = 0;
	
	private HTTPRetriever httpRetreiver = new HTTPRetriever();
	private DiachronCacheManager dcmgr = DiachronCacheManager.getInstance();
	private Queue<String> notFetchedQueue = new ConcurrentLinkedQueue<String>();
	private Set<String> uriSet = Collections.synchronizedSet(new HashSet<String>());
	private boolean metricCalculated = false;
	
	public void compute(Quad quad) {
		if (!(quad.getPredicate().getURI().equals(RDF.type.getURI()))){ // we are currently ignoring triples ?s a ?o
			String subject = quad.getSubject().toString();
			if (httpRetreiver.isPossibleURL(subject)){
				uriSet.add(subject);
			}
			
			String object = quad.getObject().toString();
			if (httpRetreiver.isPossibleURL(object)){
				uriSet.add(object);
			}
		}
	}

	public Resource getMetricURI() {
		return this.METRIC_URI;
	}

	public ProblemList<?> getQualityProblems() {
		// TODO Auto-generated method stub
		return null;
	}

	public double metricValue() {
		if (!this.metricCalculated){
			ArrayList<String> uriList = new ArrayList<String>();
			uriList.addAll(uriSet);
			httpRetreiver.addListOfResourceToQueue(uriList);
			
			try {
				httpRetreiver.start();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			do{
				this.startDereferencingProcess();
				uriSet.clear();
				uriSet.addAll(this.notFetchedQueue);
				this.notFetchedQueue.clear();
			}while(!this.notFetchedQueue.isEmpty());
			
			this.metricCalculated = true;
		}
		this.metricValue = this.dereferencedURI / this.totalURI;
		return this.metricValue;
	}
	
	
	/* Private Methods */
	private void startDereferencingProcess() {
		for(String uri : uriSet){
			CachedHTTPResource httpResource = (CachedHTTPResource) dcmgr.getFromCache(DiachronCacheManager.HTTP_RESOURCE_CACHE, uri);			
			if (httpResource.getStatusLines() == null) {
				this.notFetchedQueue.add(uri);
			} else {
				if (this.isDereferenceable(httpResource)) this.dereferencedURI++;
				this.totalURI++;

				if (httpResource.getDereferencabilityStatusCode() == StatusCode.SC200)
					this.dereferencedURI = (this.is200AnRDF(httpResource)) ? this.dereferencedURI + 1 : this.dereferencedURI;
								
				logger.debug("{} - {} - {}", uri, httpResource.getStatusLines(), httpResource.getDereferencabilityStatusCode());
			}
		}
	}
	
	private boolean isDereferenceable(CachedHTTPResource httpResource){
		if (httpResource.getDereferencabilityStatusCode() == null){
			List<Integer> statusCode = this.getStatusCodes(httpResource.getStatusLines());
			
			if (httpResource.getUri().contains("#") && statusCode.contains(200)) httpResource.setDereferencabilityStatusCode(StatusCode.HASH);
			else if (statusCode.contains(200)){
				httpResource.setDereferencabilityStatusCode(StatusCode.SC200);
				if (statusCode.contains(303)) httpResource.setDereferencabilityStatusCode(StatusCode.SC303);
				else {
					if (statusCode.contains(301)) httpResource.setDereferencabilityStatusCode(StatusCode.SC301);
					if (statusCode.contains(302)) httpResource.setDereferencabilityStatusCode(StatusCode.SC302);
					if (statusCode.contains(307)) httpResource.setDereferencabilityStatusCode(StatusCode.SC303);
				}
			}
			
			if (has4xxCode(statusCode)) httpResource.setDereferencabilityStatusCode(StatusCode.SC4XX);
			if (has5xxCode(statusCode)) httpResource.setDereferencabilityStatusCode(StatusCode.SC5XX);
		} 			
		
		StatusCode scode = httpResource.getDereferencabilityStatusCode();
		return this.mapDerefStatusCode(scode);
		
	}
	
	private List<Integer> getStatusCodes(List<StatusLine> statusLines){
		ArrayList<Integer> codes = new ArrayList<Integer>();
		for(StatusLine s : statusLines){
			codes.add(s.getStatusCode());
		}
		
		return codes;
	}
	
	private boolean mapDerefStatusCode(StatusCode statusCode){
		switch(statusCode){
			case SC303 : case HASH : return true;
			default : return false;
		}
	}
	
	private boolean is200AnRDF(CachedHTTPResource resource){
		for (HttpResponse response : resource.getResponses()){
			if (response.getEntity().getContentType().getValue().equals("application/rdf+xml"))
				return true;
		}
		return false;
	}
	
	private boolean has4xxCode(List<Integer> statusCode){
		for (int i : statusCode){
			if ((i >= 400) && (i < 499))  return true; else continue;
		}
		return false;
	}
	
	private boolean has5xxCode(List<Integer> statusCode){
		for (int i : statusCode){
			if ((i >= 500) && (i < 599))  return true; else continue;
		}
		return false;
	}
}
