/**
 * 
 */
package eu.diachron.qualitymetrics.utilities;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicStatusLine;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFLanguages;
import org.apache.jena.riot.WebContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.unibonn.iai.eis.diachron.datatypes.StatusCode;
import eu.diachron.qualitymetrics.cache.CachedHTTPResource;
import eu.diachron.qualitymetrics.cache.DiachronCacheManager;

/**
 * @author Jeremy Debattista
 * 
 * Retreives HTTP resources
 */
public class HTTPRetreiver {

	final static Logger logger = LoggerFactory.getLogger(HTTPRetreiver.class);

	//private ConcurrentLinkedQueue<String> httpQueue = new ConcurrentLinkedQueue<String>();
	private Set<String> httpQueue = Collections.synchronizedSet(new HashSet<String>());
	private ExecutorService executor = Executors.newSingleThreadExecutor();
	private CountDownLatch mainHTTPRetreiverLatch;
	
	
	public void addResourceToQueue(String resourceURI) {
		this.httpQueue.add(resourceURI);
	}

	public void addListOfResourceToQueue(List<String> resourceURIs) {
		this.httpQueue.addAll(resourceURIs);
	}
	

	public void start() throws InterruptedException{
		//TODO: check if httpQUEUE is not empty yet 
		mainHTTPRetreiverLatch = new CountDownLatch(httpQueue.size());
		
		Runnable retreiver = new Runnable() {
			public void run() {
				try {
					runHTTPAsyncRetreiver();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		executor.submit(retreiver);
		mainHTTPRetreiverLatch.await();
	}
	
	/**
	 * Stops the HTTPRetreiver Process
	 */
	public void stop() {
		executor.shutdown();
	}

	private void runHTTPAsyncRetreiver() throws InterruptedException {
		RequestConfig requestConfig = this.getRequestConfig(true);
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom()
				.setDefaultRequestConfig(requestConfig).build();
		final HttpClientContext localContext = HttpClientContext.create();
		
		httpclient.start();
		for(final String queuePeek : this.httpQueue){
			Thread.sleep(3000);
			if (DiachronCacheManager.getInstance().existsInCache(DiachronCacheManager.HTTP_RESOURCE_CACHE, queuePeek)) {
				continue;
			}

			final CachedHTTPResource newResource = new CachedHTTPResource();
			newResource.setUri(queuePeek);
			DiachronCacheManager.getInstance().addToCache(DiachronCacheManager.HTTP_RESOURCE_CACHE, queuePeek, newResource);
																										  
			final HttpGet request = new HttpGet(queuePeek);
			Header accept = new BasicHeader("Accept", "application/rdf+xml; text/html");
			request.addHeader(accept);
			
			httpclient.execute(request, localContext,
					new FutureCallback<HttpResponse>() {
						public void completed(final HttpResponse response) {
							newResource.addResponse(response);
							try {
								if (localContext.getRedirectLocations().size() >= 1) {
									List<URI> uriRoute = new ArrayList<URI>();
									uriRoute.add(request.getURI());
									uriRoute.addAll(localContext.getRedirectLocations());
									try {
										newResource.addAllResponses(followAsyncRedirection(uriRoute));
									} catch (IOException e) {
										e.printStackTrace();
									}
								} else {
									newResource.addStatusLines(response.getStatusLine());
								}
							} catch (Exception e) {
								logger.debug("Exception during the request for redirect locations whith the following exception : {}",e.getLocalizedMessage());
								newResource.addStatusLines(response.getStatusLine());
							}
							DiachronCacheManager.getInstance().addToCache(DiachronCacheManager.HTTP_RESOURCE_CACHE,queuePeek, newResource);
							mainHTTPRetreiverLatch.countDown();
						}

						public void failed(final Exception ex) {
							newResource.setDereferencabilityStatusCode(StatusCode.BAD);
							newResource.addStatusLines(new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), 0,"Request could not be processed"));
							DiachronCacheManager.getInstance().addToCache(DiachronCacheManager.HTTP_RESOURCE_CACHE,queuePeek, newResource);

							logger.debug("Failed in retreiving request : {}, with the following exception : {}",request.getURI().toString(),ex.getLocalizedMessage());
							mainHTTPRetreiverLatch.countDown();
						}

						public void cancelled() {
							logger.debug("The retreival for {} was cancelled.",request.getURI().toString());
							mainHTTPRetreiverLatch.countDown();
						}
					});
		}
	}
	
	protected List<HttpResponse> followAsyncRedirection(List<URI> uriRoute) throws IOException, InterruptedException {
		final List<HttpResponse> httpResponses = Collections.synchronizedList(new ArrayList<HttpResponse>());
		RequestConfig requestConfig = this.getRequestConfig(false);
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom().setDefaultRequestConfig(requestConfig).build();
		try {
			final HttpClientContext localContext = HttpClientContext.create();
			httpclient.start();
			final List<HttpGet> requests = this.toHttpGetList(uriRoute);

			final CountDownLatch redirectionLatch = new CountDownLatch(requests.size());
			for (final HttpGet request : requests) {
				httpclient.execute(request, localContext, new FutureCallback<HttpResponse>() {

							public void completed(final HttpResponse response) {
								redirectionLatch.countDown();
								httpResponses.add(response);
							}

							public void failed(final Exception ex) {
								logger.debug("Failed in retreiving follow redirection request : {}, with the following exception : {}",request.getURI().toString(),ex.getLocalizedMessage());
								redirectionLatch.countDown();
							}

							public void cancelled() {
								logger.debug("The retreival for {} was cancelled.",request.getURI().toString());
								redirectionLatch.countDown();
							}
						});
			}
			redirectionLatch.await();
		} finally {
			httpclient.close();
		}
		return httpResponses;
	}

	private List<HttpGet> toHttpGetList(List<URI> uriRoute) {
		List<HttpGet> requests = new ArrayList<HttpGet>();
		for (URI uri : uriRoute) {
			requests.add(new HttpGet(uri.toString()));
		}

		return requests;
	}

	private RequestConfig getRequestConfig(boolean followRedirects) {
		return RequestConfig.custom().
				setSocketTimeout(3000).
				setConnectTimeout(3000).
				setRedirectsEnabled(followRedirects).
				build();
	}

	public boolean isPossibleURL(String url) {
		// TODO: add more protocols
		return ((url.startsWith("http")) || (url.startsWith("https")));
	}


	public static void main(String [] args) throws InterruptedException{
		HTTPRetreiver httpRetreiver = new HTTPRetreiver();
	
		//String uri = "http://aksw.org/model/export/?m=http%3A%2F%2Faksw.org%2F&f=rdfxml";
		String uri = "http://aksw.org/MichaelMartin";
		httpRetreiver.addResourceToQueue(uri);
		httpRetreiver.start();
		Thread.sleep(5000);
	
		CachedHTTPResource httpResource = (CachedHTTPResource) DiachronCacheManager.getInstance().getFromCache(DiachronCacheManager.HTTP_RESOURCE_CACHE,uri);
		//System.out.println(httpResource.getResponses().get(0).getEntity().getContentType().getValue());
		//httpResource.getResponses().get(0).getHeaders("Content-Disposition");
		String filename = httpResource.getResponses().get(0).getHeaders("Content-Disposition")[0].getValue().replace("filename=\"", "").replace("\"", "");
		Lang language = RDFLanguages.filenameToLang(filename);
		System.out.println(WebContent.mapLangToContentType(language));
	}
}
	