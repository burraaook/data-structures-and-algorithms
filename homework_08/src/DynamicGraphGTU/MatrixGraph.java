package DynamicGraphGTU;

/**
 * Implementation of MatrixGraph
 */
public class MatrixGraph {
	/**
	 * Matrix representation of edges.
	 */
	private double[][] edges;

	/**
	 * Number of vertices.
	 */ 
	private int numV;

	/**
	 * Indicates graph is directed or not.
	 */ 
	private boolean directed;


	/**
	 * Construct a matrix graph with given parameters..
	 * @param numV number of vertices
	 * @param directed Indicates graph is directed or not
	 */
	public MatrixGraph ( int numV, boolean directed ) {
		this.numV = numV;
		this.directed = directed;
		
		edges = new double[numV][numV];
		
		for( int i=0; i<numV; ++i )
			for( int j=0; j<numV; ++j )
				edges[i][j] = Double.POSITIVE_INFINITY; 		
	}
	
	/**
	 * Returns number of vertices.
	 * @return number of vertices.
	 */	
	public int getNumV ( ) {
		return numV;
	}

	/**
	 * Returns if graph is directed or not.
	 * @return directed or not.
	 */
	public boolean isDirected ( ) {
		return directed;
	}

	/**
	 * Checks if given source and destination id's are edge.
	 * @param source source id
	 * @param dest destination id
	 * @return true if it is edge.
	 */
	public boolean isEdge ( int source, int dest ) {
		return edges[source][dest] != Double.POSITIVE_INFINITY;
	}
	
	/**
	 * Sets the driection of the graph.
	 * @param direction direction
	 */ 
	public void setDirection ( boolean direction ) {
		directed = direction;
	}

	/**
	 * Inserts an edge to graph.
	 * @param edge edge to be inserted
	 */
	public void insert(Edge edge){
		edges[edge.getSource()][edge.getDest()] = edge.getWeight();
		if( !isDirected() )
			edges[edge.getDest()][edge.getSource()] = edge.getWeight();
		
	}
	
	/**
	 * Returns the String representation of MatrixGraph. In matrix format.
	 * @return String representation of the MatrixGraph
	 */ 
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();

		for ( int i = 0; i < numV; ++i )
			sb.append(String.format("%6d ", i));
		sb.append("\n\n");

		for ( int i=0; i<numV; ++i ) {
			sb.append(String.format("%-4d ", i));
			for ( int j=0; j<numV; ++j ) {
				
				if(edges[i][j] == Double.POSITIVE_INFINITY){
					sb.append(String.format("%-6s ", "x"));
				} 
				else 
					sb.append( String.format("%-6.2f ", edges[i][j] ));
			}
			sb.append("\n\n");
		}
		return sb.toString();
	}
}
