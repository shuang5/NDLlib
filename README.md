# NDLlib
Reads ndl rdf/owl ORCA Manifest file and converts it to blueprints Graph.

E.g.

com.tinkerpop.blueprints.Graph graph=new com.tinkerpop.blueprints.impls.neo4j2.Neo4j2Graph(...);

String rdffile="manifest.rdf";

ManifestPropertygraphImpl.convertManifestNDL(rdffile, graph);
