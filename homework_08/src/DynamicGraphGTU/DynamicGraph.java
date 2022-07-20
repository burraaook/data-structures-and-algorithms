package DynamicGraphGTU;

/**
 * 
 * DynamicGraph interface which provides dynamically growth and shrink option for Graph. It also requires an General Vertex class.
 * @author Burak Kocausta
 * @version 1.0 27.05.2022
 */ 
public interface DynamicGraph extends Graph {

	/**
	 * Creates a new vertex and adds to the graph.
	 * @param label label of the vertex
	 * @param weight weight of the vertex
	 * @return Vertex that created.
	 */ 
	Vertex newVertex ( String label, double weight );

	/**
	 * Inserts given vertex to the graph, if it is exist(id) overrides it.
	 * @param newVertex new vertex
	 */ 
	void addVertex ( Vertex newVertex );

	/**
	 * Inserts an edge to the graph.
	 * @param vertexID1 source
	 * @param vertexID2 destination
	 * @param weight weight of edge
	 */ 
	void addEdge ( int vertexID1, int vertexID2, double weight );

	/**
	 * Removes edge from graph.
	 * @param vertexID1 source
	 * @param vertexID2 destination
	 * @return true if it successfully removed, false otherwise.
	 */  
	boolean removeEdge ( int vertexID1, int vertexID2 );

	/**
	 * Removes vertex from graph according to the id.
	 * @param vertexID id of the vertex
	 * @return true if it removed, false otherwise.
	 */ 
	boolean removeVertex ( int vertexID );

	/**
	 * Removes vertex according to the label, it can remove more than one vertex.
	 * @param label label of the vertex
	 * @return true if it removed, false otherwise. 
	 */ 
	boolean removeVertex ( String label );

	/**
	 * Takes a pair for vertex, and returns subgraph of vertices which has this pair.
	 * @param key key
	 * @param filter filter
	 * @return DynamicGraph that created.
	 */ 
	DynamicGraph filterVertices ( String key, String filter );

	/**
	 * Creates a adjacency matrix version of current graph, and returns.
	 * @return MatrixGraph
	 */ 
	MatrixGraph exportMatrix ( );

	/**
	 * Prints the graph.
	 */ 
	void printGraph ( );
}