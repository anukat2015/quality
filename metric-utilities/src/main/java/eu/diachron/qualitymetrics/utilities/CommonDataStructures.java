package eu.diachron.qualitymetrics.utilities;

import java.util.HashSet;
import java.util.Set;

import org.apache.jena.riot.WebContent;

@Deprecated
public final class CommonDataStructures {
	
	private CommonDataStructures(){}
	
	// LD Content Types
	public static Set<String> ldContentTypes = new HashSet<String>() ;
    static {
    	//TODO: check if all these types are conformant to LD principles
    	ldContentTypes.add(WebContent.contentTypeN3);
    	ldContentTypes.add(WebContent.contentTypeN3Alt1);
    	ldContentTypes.add(WebContent.contentTypeN3Alt2);
    	ldContentTypes.add(WebContent.contentTypeTurtle);
    	ldContentTypes.add(WebContent.contentTypeTurtleAlt1);
    	ldContentTypes.add(WebContent.contentTypeTurtleAlt2);
    	ldContentTypes.add(WebContent.contentTypeRDFXML);
    	ldContentTypes.add(WebContent.contentTypeRDFJSON);
    	ldContentTypes.add(WebContent.contentTypeTextPlain);  // MIME type for N-triple is text/plain (!!!)
    	ldContentTypes.add(WebContent.contentTypeNTriples);
    	ldContentTypes.add(WebContent.contentTypeNTriplesAlt);
    	ldContentTypes.add(WebContent.contentTypeTriG);
    	ldContentTypes.add(WebContent.contentTypeNQuads);
    	ldContentTypes.add(WebContent.contentTypeTriGAlt1);
    	ldContentTypes.add(WebContent.contentTypeTriGAlt2);
    	ldContentTypes.add(WebContent.contentTypeNQuadsAlt1);
    	ldContentTypes.add(WebContent.contentTypeNQuadsAlt2);
    	ldContentTypes.add(WebContent.contentTypeRdfJson);		
    	ldContentTypes.add(WebContent.contentTypeResultsXML);
    	ldContentTypes.add(WebContent.contentTypeResultsJSON);
    	ldContentTypes.add(WebContent.contentTypeResultsBIO);
    	ldContentTypes.add(WebContent.contentTypeSPARQLQuery);
    	ldContentTypes.add(WebContent.contentTypeSPARQLUpdate);
    	
    	//the following are required since we might have misreported content types
    	ldContentTypes.add(WebContent.contentTypeXML);
    	ldContentTypes.add(WebContent.contentTypeXMLAlt);
    	ldContentTypes.add("application/xhtml+xml");
    	
    	//the following are required since the datadump might be zipped in tar files
    	ldContentTypes.add("application/x-compressed"); //.tgz
    	ldContentTypes.add("application/x-tar"); //.tar
    	ldContentTypes.add("application/x-gzip"); //.gz
//    	ldContentTypes.add("text/html");
    }
}