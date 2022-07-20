package DynamicGraphGTU;

import java.util.Iterator;

/**
 * Interface to specify a Graph ADT which is taken from the book.
 */
public interface Graph {
	
	/**
	 * Return the number of vertices
	 * @return The number of vertices
	 */
	int getNumV ( );
	
	/**
	 * Determine whether this is a directed graph
	 * @return True if this is a directed graph
	 */
	boolean isDirected ( );
	
	/**
	 * Insert a new edge into the graph
	 * @param e The new edge
	 */
	void insert ( Edge e );
	
	/**
	 * Determine whether an edge exists
	 * @param source The source vertex
	 * @param dest The destination vertex
	 * @return true if there is an edge from source to dest
	 */
	boolean isEdge ( int source, int dest );
	
	/**
	 * Get edge between two vertices
	 * @param source The source vertex
	 * @param dest The destination vertex
	 * @return The Edge between these two vertices.
	 */
	Edge getEdge ( int source, int dest );
	
	/**
	 * Return an iterator to the edges connected to a given vertex
	 * @param source The source vertex
	 * @return An Iterator to the vertices connected to source.
	 */
	Iterator<Edge> edgeIterator ( int source );
}

