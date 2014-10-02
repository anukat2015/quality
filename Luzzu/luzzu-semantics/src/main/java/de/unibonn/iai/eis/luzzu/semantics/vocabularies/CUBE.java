/* CVS $Id: $ */
package de.unibonn.iai.eis.luzzu.semantics.vocabularies; 
import com.hp.hpl.jena.rdf.model.*;
 
/**
 * Vocabulary definitions from src/main/resources/vocabularies/cube/cube.ttl 
 * @author Auto-generated by schemagen on 11 Aug 2014 18:41 
 */
public class CUBE {
    /** <p>The RDF model that holds the vocabulary terms</p> */
    private static Model m_model = ModelFactory.createDefaultModel();
    
    /** <p>The namespace of the vocabulary as a string</p> */
    public static final String NS = "http://purl.org/linked-data/cube#";
    
    /** <p>The namespace of the vocabulary as a string</p>
     *  @see #NS */
    public static String getURI() {return NS;}
    
    /** <p>The namespace of the vocabulary as a resource</p> */
    public static final Resource NAMESPACE = m_model.createResource( NS );
    
    /** <p>The ontology's owl:versionInfo as a string</p> */
    public static final String VERSION_INFO = "0.2";
    
    /** <p>An alternative to qb:componentProperty which makes explicit that the component 
     *  is a attribute</p>
     */
    public static final Property attribute = m_model.createProperty( "http://purl.org/linked-data/cube#attribute" );
    
    /** <p>gives the code list associated with a CodedProperty</p> */
    public static final Property codeList = m_model.createProperty( "http://purl.org/linked-data/cube#codeList" );
    
    /** <p>indicates a component specification which is included in the structure of 
     *  the dataset</p>
     */
    public static final Property component = m_model.createProperty( "http://purl.org/linked-data/cube#component" );
    
    /** <p>Indicates the level at which the component property should be attached, this 
     *  might an qb:DataSet, qb:Slice or qb:Observation, or a qb:MeasureProperty.</p>
     */
    public static final Property componentAttachment = m_model.createProperty( "http://purl.org/linked-data/cube#componentAttachment" );
    
    /** <p>indicates a ComponentProperty (i.e. attribute/dimension) expected on a DataSet, 
     *  or a dimension fixed in a SliceKey</p>
     */
    public static final Property componentProperty = m_model.createProperty( "http://purl.org/linked-data/cube#componentProperty" );
    
    /** <p>Indicates whether a component property is required (true) or optional (false) 
     *  in the context of a DSD. Only applicable to components correspond to an attribute. 
     *  Defaults to false (optional).</p>
     */
    public static final Property componentRequired = m_model.createProperty( "http://purl.org/linked-data/cube#componentRequired" );
    
    /** <p>gives the concept which is being measured or indicated by a ComponentProperty</p> */
    public static final Property concept = m_model.createProperty( "http://purl.org/linked-data/cube#concept" );
    
    /** <p>indicates the data set of which this observation is a part</p> */
    public static final Property dataSet = m_model.createProperty( "http://purl.org/linked-data/cube#dataSet" );
    
    /** <p>An alternative to qb:componentProperty which makes explicit that the component 
     *  is a dimension</p>
     */
    public static final Property dimension = m_model.createProperty( "http://purl.org/linked-data/cube#dimension" );
    
    /** <p>Specifies a root of the hierarchy. A hierarchy may have multiple roots but 
     *  must have at least one.</p>
     */
    public static final Property hierarchyRoot = m_model.createProperty( "http://purl.org/linked-data/cube#hierarchyRoot" );
    
    /** <p>An alternative to qb:componentProperty which makes explicit that the component 
     *  is a measure</p>
     */
    public static final Property measure = m_model.createProperty( "http://purl.org/linked-data/cube#measure" );
    
    /** <p>An alternative to qb:componentProperty which makes explicit that the component 
     *  is a measure dimension</p>
     */
    public static final Property measureDimension = m_model.createProperty( "http://purl.org/linked-data/cube#measureDimension" );
    
    /** <p>Generic measure dimension, the value of this dimension indicates which measure 
     *  (from the set of measures in the DSD) is being given by the obsValue (or other 
     *  primary measure)</p>
     */
    public static final Property measureType = m_model.createProperty( "http://purl.org/linked-data/cube#measureType" );
    
    /** <p>indicates a observation contained within this slice of the data set</p> */
    public static final Property observation = m_model.createProperty( "http://purl.org/linked-data/cube#observation" );
    
    /** <p>Indicates a group of observations. The domain of this property is left open 
     *  so that a group may be attached to different resources and need not be restricted 
     *  to a single DataSet</p>
     */
    public static final Property observationGroup = m_model.createProperty( "http://purl.org/linked-data/cube#observationGroup" );
    
    /** <p>indicates a priority order for the components of sets with this structure, 
     *  used to guide presentations - lower order numbers come before higher numbers, 
     *  un-numbered components come last</p>
     */
    public static final Property order = m_model.createProperty( "http://purl.org/linked-data/cube#order" );
    
    /** <p>Specifies a property which relates a parent concept in the hierarchy to a 
     *  child concept.</p>
     */
    public static final Property parentChildProperty = m_model.createProperty( "http://purl.org/linked-data/cube#parentChildProperty" );
    
    /** <p>Indicates a subset of a DataSet defined by fixing a subset of the dimensional 
     *  values</p>
     */
    public static final Property slice = m_model.createProperty( "http://purl.org/linked-data/cube#slice" );
    
    /** <p>indicates a slice key which is used for slices in this dataset</p> */
    public static final Property sliceKey = m_model.createProperty( "http://purl.org/linked-data/cube#sliceKey" );
    
    /** <p>indicates the sub-key corresponding to this slice</p> */
    public static final Property sliceStructure = m_model.createProperty( "http://purl.org/linked-data/cube#sliceStructure" );
    
    /** <p>indicates the structure to which this data set conforms</p> */
    public static final Property structure = m_model.createProperty( "http://purl.org/linked-data/cube#structure" );
    
    /** <p>Abstract superclass for everything that can have attributes and dimensions</p> */
    public static final Resource Attachable = m_model.createResource( "http://purl.org/linked-data/cube#Attachable" );
    
    /** <p>The class of components which represent attributes of observations in the 
     *  cube, e.g. unit of measurement</p>
     */
    public static final Resource AttributeProperty = m_model.createResource( "http://purl.org/linked-data/cube#AttributeProperty" );
    
    /** <p>Superclass of all coded ComponentProperties</p> */
    public static final Resource CodedProperty = m_model.createResource( "http://purl.org/linked-data/cube#CodedProperty" );
    
    /** <p>Abstract super-property of all properties representing dimensions, attributes 
     *  or measures</p>
     */
    public static final Resource ComponentProperty = m_model.createResource( "http://purl.org/linked-data/cube#ComponentProperty" );
    
    /** <p>Abstract class of things which reference one or more ComponentProperties</p> */
    public static final Resource ComponentSet = m_model.createResource( "http://purl.org/linked-data/cube#ComponentSet" );
    
    /** <p>Used to define properties of a component (attribute, dimension etc) which 
     *  are specific to its usage in a DSD.</p>
     */
    public static final Resource ComponentSpecification = m_model.createResource( "http://purl.org/linked-data/cube#ComponentSpecification" );
    
    /** <p>Represents a collection of observations, possibly organized into various slices, 
     *  conforming to some common dimensional structure.</p>
     */
    public static final Resource DataSet = m_model.createResource( "http://purl.org/linked-data/cube#DataSet" );
    
    /** <p>Defines the structure of a DataSet or slice</p> */
    public static final Resource DataStructureDefinition = m_model.createResource( "http://purl.org/linked-data/cube#DataStructureDefinition" );
    
    /** <p>The class of components which represent the dimensions of the cube</p> */
    public static final Resource DimensionProperty = m_model.createResource( "http://purl.org/linked-data/cube#DimensionProperty" );
    
    /** <p>Represents a generalized hierarchy of concepts which can be used for coding. 
     *  The hierarchy is defined by one or more roots together with a property which 
     *  relates concepts in the hierarchy to thier child concept . The same concepts 
     *  may be members of multiple hierarchies provided that different qb:parentChildProperty 
     *  values are used for each hierarchy.</p>
     */
    public static final Resource HierarchicalCodeList = m_model.createResource( "http://purl.org/linked-data/cube#HierarchicalCodeList" );
    
    /** <p>The class of components which represent the measured value of the phenomenon 
     *  being observed</p>
     */
    public static final Resource MeasureProperty = m_model.createResource( "http://purl.org/linked-data/cube#MeasureProperty" );
    
    /** <p>A single observation in the cube, may have one or more associated measured 
     *  values</p>
     */
    public static final Resource Observation = m_model.createResource( "http://purl.org/linked-data/cube#Observation" );
    
    /** <p>A, possibly arbitrary, group of observations.</p> */
    public static final Resource ObservationGroup = m_model.createResource( "http://purl.org/linked-data/cube#ObservationGroup" );
    
    /** <p>Denotes a subset of a DataSet defined by fixing a subset of the dimensional 
     *  values, component properties on the Slice</p>
     */
    public static final Resource Slice = m_model.createResource( "http://purl.org/linked-data/cube#Slice" );
    
    /** <p>Denotes a subset of the component properties of a DataSet which are fixed 
     *  in the corresponding slices</p>
     */
    public static final Resource SliceKey = m_model.createResource( "http://purl.org/linked-data/cube#SliceKey" );
    
}
