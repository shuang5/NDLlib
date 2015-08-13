# NDLlib
Reads ndl rdf/owl ORCA Manifest file and converts it to blueprints Graph.
E.g.
com.tinkerpop.blueprints.Graph graph;
String rdffile="manifest.rdf";
ManifestPropertygraphImpl.convertManifestNDL(rdffile, graph);
