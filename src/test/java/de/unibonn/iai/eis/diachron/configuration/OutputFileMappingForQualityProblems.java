package de.unibonn.iai.eis.diachron.configuration;

/**
 * This class is responsible for mapping the names of output file
 * for Quality problems
 * 
 * @author Muhammad Ali Qasmi
 * @date 19th May 2014
 */
public class OutputFileMappingForQualityProblems {
     /**
      * Common path prefix    
      */
     public static String pathPrefix = "C:/Users/Ali/workspace/rdf-refinement-extension/src/dataset/rdf/sample.dataset/";
     /**
      * For Malformed Datatype Literals Quality Problems   
      */
     public static String MalformedDatatypeLiterals = pathPrefix + "MalformedDatatypeLiterals.rdf";
     /**
      * For Incompatible Datatype Range Quality Problems
      */
     public static String IncompatibleDatatypeRange = pathPrefix + "IncompatibleDatatypeRange.rdf";
     /**
      * For Homogeneous Datatypes Quality Problems
      */
     public static String HomogeneousDatatypes = pathPrefix + "HomogeneousDatatypes.rdf";
     /**
      * For Misplaced Classes Or Properties Quality Problems
      */
     public static String MisplacedClassesOrProperties = pathPrefix + "MisplacedClassesOrProperties.rdf";
     /**
      * For Misuse Owl Datatype Or Object Properties Quality Problems
      */
     public static String MisuseOwlDatatypeOrObjectProperties = pathPrefix + "MisuseOwlDatatypeOrObjectProperties.rdf";
     /**
      * For Undefined Classes Or Properties Quality Problems 
      */
     public static String UndefinedClassesOrProperties = pathPrefix + "UndefinedClassesOrProperties.rdf";
}
