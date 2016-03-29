/* CVS $Id: $ */
package de.unibonn.iai.eis.diachron.semantics; 
import com.hp.hpl.jena.rdf.model.*;
 
/**
 * Vocabulary definitions from src/main/resources/vocabularies/dqm/dqm-prob.ttl 
 * @author Auto-generated by schemagen on 29 Mar 2016 18:53 
 */
public class DQMPROB {
    /** <p>The RDF model that holds the vocabulary terms</p> */
    private static Model m_model = ModelFactory.createDefaultModel();
    
    /** <p>The namespace of the vocabulary as a string</p> */
    public static final String NS = "http://www.diachron-fp7.eu/dqm-prob#";
    
    /** <p>The namespace of the vocabulary as a string</p>
     *  @see #NS */
    public static String getURI() {return NS;}
    
    /** <p>The namespace of the vocabulary as a resource</p> */
    public static final Resource NAMESPACE = m_model.createResource( NS );
    
    public static final Property actualContentType = m_model.createProperty( "http://www.diachron-fp7.eu/dqm-prob#actualContentType" );
    
    public static final Property expectedContentType = m_model.createProperty( "http://www.diachron-fp7.eu/dqm-prob#expectedContentType" );
    
    public static final Property forCodedProperty = m_model.createProperty( "http://www.diachron-fp7.eu/dqm-prob#forCodedProperty" );
    
    public static final Property hasMisplacedClass = m_model.createProperty( "http://www.diachron-fp7.eu/dqm-prob#hasMisplacedClass" );
    
    public static final Property hasMisplacedProperty = m_model.createProperty( "http://www.diachron-fp7.eu/dqm-prob#hasMisplacedProperty" );
    
    public static final Property hasMisusedDatatypeProperty = m_model.createProperty( "http://www.diachron-fp7.eu/dqm-prob#hasMisusedDatatypeProperty" );
    
    public static final Property hasMisusedObjectProperty = m_model.createProperty( "http://www.diachron-fp7.eu/dqm-prob#hasMisusedObjectProperty" );
    
    public static final Property hasNoBackLink = m_model.createProperty( "http://www.diachron-fp7.eu/dqm-prob#hasNoBackLink" );
    
    public static final Property hasViolatingTriple = m_model.createProperty( "http://www.diachron-fp7.eu/dqm-prob#hasViolatingTriple" );
    
    public static final Property hijackedTripleStatement = m_model.createProperty( "http://www.diachron-fp7.eu/dqm-prob#hijackedTripleStatement" );
    
    public static final Property missingPopulationCoverageList = m_model.createProperty( "http://www.diachron-fp7.eu/dqm-prob#missingPopulationCoverageList" );
    
    public static final Property problematicTriple = m_model.createProperty( "http://www.diachron-fp7.eu/dqm-prob#problematicTriple" );
    
    public static final Property violatedObject = m_model.createProperty( "http://www.diachron-fp7.eu/dqm-prob#violatedObject" );
    
    public static final Property violatedPredicate = m_model.createProperty( "http://www.diachron-fp7.eu/dqm-prob#violatedPredicate" );
    
    public static final Property violatingDisjoinedClass = m_model.createProperty( "http://www.diachron-fp7.eu/dqm-prob#violatingDisjoinedClass" );
    
    public static final Property violatingSubjects = m_model.createProperty( "http://www.diachron-fp7.eu/dqm-prob#violatingSubjects" );
    
    /** <p>The class used is deprecated in the respective schema</p> */
    public static final Resource DeprecatedClass = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#DeprecatedClass" );
    
    /** <p>The property used is deprecated in its respective schema.</p> */
    public static final Resource DeprecatedProperty = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#DeprecatedProperty" );
    
    /** <p>An Incorrect URI style used for dataset. For datasets with more than 500K 
     *  triples Slash URIs are suggested, whilst for less than 500K triples Hash URIs 
     *  should be used</p>
     */
    public static final Resource IncorrectCoolURIUsage = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#IncorrectCoolURIUsage" );
    
    /** <p>The property should not be used within a resource of this type.</p> */
    public static final Resource IncorrectDomain = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#IncorrectDomain" );
    
    /** <p>The defined format using the void:feature predicate is unknown. Check http://www.w3.org/ns/formats/.</p> */
    public static final Resource IncorrectFormatDefined = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#IncorrectFormatDefined" );
    
    /** <p>The property's value has an incorrect data type</p> */
    public static final Resource IncorrectRange = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#IncorrectRange" );
    
    /** <p>The described URI is not a valid RDF data dump</p> */
    public static final Resource InvalidDataDumpURI = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#InvalidDataDumpURI" );
    
    /** <p>The described SPARQL endpoint is either invalid or not responding</p> */
    public static final Resource InvalidSPARQLEndPoint = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#InvalidSPARQLEndPoint" );
    
    /** <p>The referred predicate and objects are used in multiple subject resources 
     *  which violates the IFP metric.</p>
     */
    public static final Resource InverseFunctionalPropertyViolation = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#InverseFunctionalPropertyViolation" );
    
    /** <p>According to W3C URI standards, a URI should not be longer than 80 characters 
     *  including the schema. Long URIs are not recommended. Refer to CoolURIs (http://www.linkeddata.org/how-to-publish)</p>
     */
    public static final Resource LongURI = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#LongURI" );
    
    /** <p>The External Link provide a low number of resources that provide RDF</p> */
    public static final Resource LowPercentageOfValidPLDResources = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#LowPercentageOfValidPLDResources" );
    
    /** <p>The referred resource, used a defined class instead of a property, in the 
     *  property's position.</p>
     */
    public static final Resource MisplacedClass = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#MisplacedClass" );
    
    /** <p>The referred resource, used a defined property instead of a class, in an rdf:type 
     *  statement object's position.</p>
     */
    public static final Resource MisplacedProperty = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#MisplacedProperty" );
    
    /** <p>The described Resource has a misreported content type</p> */
    public static final Resource MisreportedTypeException = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#MisreportedTypeException" );
    
    /** <p>The referred dataset have missing metadata properties thus cannot calculate 
     *  currency.</p>
     */
    public static final Resource MissingMetadataForCurrency = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#MissingMetadataForCurrency" );
    
    /** <p>The referred dataset have missing metadata properties thus cannot calculate 
     *  data freshness.</p>
     */
    public static final Resource MissingMetadataForFreshness = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#MissingMetadataForFreshness" );
    
    /** <p>The referred dataset have missing metadata properties thus cannot calculate 
     *  data timeliness.</p>
     */
    public static final Resource MissingMetadataForTimelinessOfResource = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#MissingMetadataForTimelinessOfResource" );
    
    /** <p>The referred list of objects are not covered in the dataset.</p> */
    public static final Resource MissingPopulationCoverage = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#MissingPopulationCoverage" );
    
    /** <p>The referred resource used a datatype property instead of an Object Property.</p> */
    public static final Resource MisusedDatatypeProperty = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#MisusedDatatypeProperty" );
    
    /** <p>The referred resource used an object property instead of a datatype property.</p> */
    public static final Resource MisusedObjectProperty = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#MisusedObjectProperty" );
    
    /** <p>The referred resource is multi-typed with disjoined classes.</p> */
    public static final Resource MultiTypedResourceWithDisjointedClasses = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#MultiTypedResourceWithDisjointedClasses" );
    
    /** <p>The described resource does not provide a valid back link</p> */
    public static final Resource NoBackLink = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#NoBackLink" );
    
    /** <p>The dataset has no valid SPARQL Endpoints defined with the void:sparqlEndpoint 
     *  predicate</p>
     */
    public static final Resource NoEndPointAccessibility = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#NoEndPointAccessibility" );
    
    /** <p>Entities in this list have no Human Readable Label.</p> */
    public static final Resource NoHumanReadableLabel = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#NoHumanReadableLabel" );
    
    /** <p>The dataset must have more than one serialisation format defined using the 
     *  void:feature predicate.</p>
     */
    public static final Resource NoMultipleFormatDefined = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#NoMultipleFormatDefined" );
    
    /** <p>The dataset has no valid RDF Dump defined with the void:dataDump predicate</p> */
    public static final Resource NoRDFAccessibility = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#NoRDFAccessibility" );
    
    /** <p>No regular expression in the voID description.</p> */
    public static final Resource NoURIRegExPresence = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#NoURIRegExPresence" );
    
    /** <p>The described RDF Document/Dataset has no valid licence in the voID description 
     *  or in its metadata</p>
     */
    public static final Resource NoValidLicenceInDataset = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#NoValidLicenceInDataset" );
    
    /** <p>The described RDF Document/Dataset has no valid licence in the in its descriptive 
     *  metadata</p>
     */
    public static final Resource NoValidLicenceInDatasetForHumans = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#NoValidLicenceInDatasetForHumans" );
    
    /** <p>The External Link provided does not give valid RDF</p> */
    public static final Resource NoValidRDFDataForExternalLink = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#NoValidRDFDataForExternalLink" );
    
    /** <p>Vocabularies in this list are not indicated by a respective voID description.</p> */
    public static final Resource NoVocabularyIndication = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#NoVocabularyIndication" );
    
    /** <p>The described resource has a dereferenceable code as recommended by the LOD 
     *  Principles (i.e. 303 See Other or Hash URI), but no meaningful data</p>
     */
    public static final Resource NotMeaningful = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#NotMeaningful" );
    
    /** <p>The described RDF Document/Dataset has a valid licence that is not recommended 
     *  according to the voID W3C Standards</p>
     */
    public static final Resource NotRecommendedLicenceInDataset = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#NotRecommendedLicenceInDataset" );
    
    /** <p>The described RDF Document/Dataset has a valid licence that is not recommended 
     *  according to the voID W3C Standards</p>
     */
    public static final Resource NotRecommendedLicenceInDatasetForHumans = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#NotRecommendedLicenceInDatasetForHumans" );
    
    /** <p>The described resource do not resolve in a valid Dereferenced Link</p> */
    public static final Resource NotValidDereferenceableBackLink = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#NotValidDereferenceableBackLink" );
    
    /** <p>The described resource do not resolve in a valid Dereferenced Forward-Link</p> */
    public static final Resource NotValidForwardLink = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#NotValidForwardLink" );
    
    /** <p>The referred resource hijacked an external authorative concept.</p> */
    public static final Resource OntologyHijackingException = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#OntologyHijackingException" );
    
    /** <p>The resource URI includes a parameterised string.</p> */
    public static final Resource ParametarisedURI = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#ParametarisedURI" );
    
    /** <p>The referred resource has a replica resource with a different id.</p> */
    public static final Resource ResourceReplica = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#ResourceReplica" );
    
    /** <p>The described resource has a dereferenceability Status Code of 200 but returns 
     *  an semantic content type</p>
     */
    public static final Resource SC200WithRDF = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#SC200WithRDF" );
    
    /** <p>The described resource has a dereferenceability Status Code of 200 but does 
     *  not return a semantic content type</p>
     */
    public static final Resource SC200WithoutRDF = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#SC200WithoutRDF" );
    
    /** <p>The described resource has a dereferenceability Status Code of 301 Moved Permanently</p> */
    public static final Resource SC301MovedPermanently = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#SC301MovedPermanently" );
    
    /** <p>The described resource has a dereferenceability Status Code of 302 Found</p> */
    public static final Resource SC302Found = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#SC302Found" );
    
    /** <p>The described resource has a dereferenceability Status Code of 307 Temporary 
     *  Redirected</p>
     */
    public static final Resource SC307TemporaryRedirectory = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#SC307TemporaryRedirectory" );
    
    /** <p>The described resource has a dereferenceability Status Code of a 3XX Redirection 
     *  but not the recommended 303 See Other</p>
     */
    public static final Resource SC3XXRedirection = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#SC3XXRedirection" );
    
    /** <p>The described resource has a dereferenceability Status Code of 4XX Client 
     *  Error</p>
     */
    public static final Resource SC4XXClientError = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#SC4XXClientError" );
    
    /** <p>The described resource has a dereferenceability Status Code of 5XX Server 
     *  Error</p>
     */
    public static final Resource SC5XXServerError = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#SC5XXServerError" );
    
    /** <p>The class used is undefined in the respective schema</p> */
    public static final Resource UndefinedClass = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#UndefinedClass" );
    
    /** <p>The property used is undefined in its respective schema.</p> */
    public static final Resource UndefinedProperty = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#UndefinedProperty" );
    
    /** <p>The resource type is unknown.</p> */
    public static final Resource UnknownType = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#UnknownType" );
    
    /** <p>The dataset did not use terms from the suggested vocabulary.</p> */
    public static final Resource UnusedSuggestedVocabulary = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#UnusedSuggestedVocabulary" );
    
    /** <p>The resource is using RDF collections feature. The use of rdf:Alt, rdf:Bag, 
     *  rdf:List, or any other prolix feature is strongly discouraged.</p>
     */
    public static final Resource UsageOfCollections = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#UsageOfCollections" );
    
    /** <p>The resource is using RDF containers feature (type - rdf:Container). The use 
     *  of rdf:Container or any other prolix feature is strongly discouraged.</p>
     */
    public static final Resource UsageOfContainers = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#UsageOfContainers" );
    
    /** <p>The resource is using RDF reification feature (type - rdf:Statement). The 
     *  use of rdf:Statement or any other prolix feature is strongly discouraged.</p>
     */
    public static final Resource UsageOfReification = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#UsageOfReification" );
    
    /** <p>The described resource has a violating forward link dereferencable triple</p> */
    public static final Resource ViolatingTriple = m_model.createResource( "http://www.diachron-fp7.eu/dqm-prob#ViolatingTriple" );
    
}
