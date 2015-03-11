/* CVS $Id: $ */
package de.unibonn.iai.eis.diachron.semantics.knownvocabs; 
import com.hp.hpl.jena.rdf.model.*;
 
/**
 * Vocabulary definitions from http://rdfs.org/sioc/services.rdf 
 * @author Auto-generated by schemagen on 11 Mar 2015 16:54 
 */
public class SIOCSERVICES {
    /** <p>The RDF model that holds the vocabulary terms</p> */
    private static Model m_model = ModelFactory.createDefaultModel();
    
    /** <p>The namespace of the vocabulary as a string</p> */
    public static final String NS = "http://rdfs.org/sioc/services#";
    
    /** <p>The namespace of the vocabulary as a string</p>
     *  @see #NS */
    public static String getURI() {return NS;}
    
    /** <p>The namespace of the vocabulary as a resource</p> */
    public static final Resource NAMESPACE = m_model.createResource( NS );
    
    /** <p>A Service associated with this SIOC object.</p> */
    public static final Property has_service = m_model.createProperty( "http://rdfs.org/sioc/services#has_service" );
    
    /** <p>Maximum number of results results returned by a web service.</p> */
    public static final Property max_results = m_model.createProperty( "http://rdfs.org/sioc/services#max_results" );
    
    /** <p>Format of results returned by a web service.</p> */
    public static final Property results_format = m_model.createProperty( "http://rdfs.org/sioc/services#results_format" );
    
    /** <p>Links to a web service definition of this sioc:Service.</p> */
    public static final Property service_definition = m_model.createProperty( "http://rdfs.org/sioc/services#service_definition" );
    
    /** <p>URL of a web service endpoint.</p> */
    public static final Property service_endpoint = m_model.createProperty( "http://rdfs.org/sioc/services#service_endpoint" );
    
    /** <p>A SIOC object this Service is associated with.</p> */
    public static final Property service_of = m_model.createProperty( "http://rdfs.org/sioc/services#service_of" );
    
    /** <p>A protocol used by a web service. Possible protocol values include SOAP, REST, 
     *  SPARQL-QUERY, GData and OpenSearch. These will be added to this module later.</p>
     */
    public static final Property service_protocol = m_model.createProperty( "http://rdfs.org/sioc/services#service_protocol" );
    
    /** <p>A Service is web service associated with a Site or part of it.</p> */
    public static final Resource Service = m_model.createResource( "http://rdfs.org/sioc/services#Service" );
    
}
