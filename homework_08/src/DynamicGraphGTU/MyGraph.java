package DynamicGraphGTU;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.HashSet;

/**
 * 
 * An Adjancecy List graph implementation which uses Vertex class, it can grow and shrink dynamically.
 * @author Burak Kocausta
 * @version 1.0 27.05.2022
 */ 
public class MyGraph implements DynamicGraph {

	/**
	 * Number of vertices.
	 */ 
	private int numV = 0;

	/**
	 * Indicates directed or not.
	 */ 
	private boolean directed;

	/**
	 * A LinkedHashMap to hold edges, id's are used as key.
	 */ 
	private Map<Integer, LinkedList<Edge>> edgeMap;

	/**
	 * A LinkedHashMap to hold vertices, id's are key.
	 */ 
	private Map<Integer, Vertex> vertexMap;

	/**
	 * maxID for inserting automatically.
	 */ 
	private int maxID = numV;


	/**
	 * Constructs an empty graph.
	 * @param directed indicates directed or not.
	 */ 
	public MyGraph ( boolean directed ) {
		this.numV = 0;
		this.directed = directed;
		edgeMap = new LinkedHashMap<Integer, LinkedList<Edge>>();
		vertexMap = new LinkedHashMap<Integer, Vertex>();
	}

	/**
	 * Constructs a graph which has numV vertices.
	 * @param numV number of vertices.
	 * @param directed indicates directed or not.
	 */ 
	public MyGraph ( int numV, boolean directed ) {
		this.numV = numV;
		this.maxID = numV - 1;
		this.directed = directed;
		edgeMap = new LinkedHashMap<Integer, LinkedList<Edge>>();
		vertexMap = new LinkedHashMap<Integer, Vertex>();

		for ( int i = 0; i < numV; ++i ) {
			edgeMap.put(i, new LinkedList<Edge>());
			vertexMap.put(i, new Vertex(i, "none"));
		}
	}
	
	/**
	 * Returns number of vertices.
	 * @return number of vertices.
	 */
	@Override
	public int getNumV ( ) {
		return numV;
	}

	/**
	 * Returns if graph is directed or not.
	 * @return directed or not.
	 */
	@Override
	public boolean isDirected ( ) {
		return directed;
	}

	/**
	 * Checks if given source and destination id's are edge.
	 * @param source source id
	 * @param dest destination id
	 * @return true if it is edge.
	 */
	@Override
	public boolean isEdge ( int source, int dest ) {

		List<Edge> list = edgeMap.get(source);
		if ( list == null )
			return false;

		Edge target = new Edge(source, dest);
		for( Edge edge : list ) {
			if(edge.equals(target))
				return true;
		}
		return false;
	}
	
	/**
	 * Inserts an edge to graph.
	 * @param edge edge to be inserted
	 */
	@Override
	public void insert ( Edge edge ) {
		if ( vertexMap.get(edge.getSource()) == null || vertexMap.get(edge.getDest()) == null )
			return;

		edgeMap.get(edge.getSource()).add(edge);
		if( !isDirected() ) {
			edgeMap.get(edge.getDest()).add(new Edge(edge.getDest(), edge.getSource(), edge.getWeight()));
		}
	}
	
	/**
	 * Returns edge iterator.
	 * @param source source id
	 * @return iterator over edges.
	 */ 
	@Override
	public Iterator<Edge> edgeIterator ( int source ) {
		LinkedList<Edge> list = edgeMap.get(source);
		if ( list == null )
			return null;
		return list.iterator();
	}
	
	/**
	 * Returns vertex iterator.
	 * @return iterator over vertices.
	 */ 
	public Iterator<Vertex> vertexIterator( ) {
		return vertexMap.values().iterator();
	}


	/**
	 * Returns wanted edge.
	 * @param source source id
	 * @param dest destination id
	 * @return edge that found or null if it is not exists.
	 */
	@Override
	public Edge getEdge ( int source, int dest ) {
		Edge target = new Edge(source, dest);
		for( Edge edge : edgeMap.get(source) ) 
			if(edge.equals(target))
				return edge; 
		return null; 
	}
	
	/**
	 * Returns wanted vertex.
	 * @param id id of the vertex that wanted.
	 * @return vertex reference if it is found, null if not.
	 */ 
	public Vertex getVertex ( int id ) {
		return vertexMap.get(id);
	}

	/**
	 * Creates a new vertex and adds to the graph.
	 * @param label label of the vertex
	 * @param weight weight of the vertex
	 * @return Vertex that created.
	 */ 
	@Override
	public Vertex newVertex ( String label, double weight ) {
		Vertex vertex = new Vertex(++maxID, label, weight);
		vertexMap.put(vertex.getID(), vertex);
		edgeMap.put(vertex.getID(), new LinkedList<Edge>());
		maxID = numV;
		numV++;
		return vertex;
	}

	/**
	 * Inserts given vertex to the graph, if it is exist(id) overrides it.
	 * @param newVertex new vertex
	 */ 
	@Override
	public void addVertex ( Vertex newVertex ) {
		if( vertexMap.put(newVertex.getID(), newVertex) == null )
			numV++;
		edgeMap.put(newVertex.getID(), new LinkedList<Edge>());
		maxID = (newVertex.getID() > maxID) ? newVertex.getID() : maxID;
	}

	/**
	 * Inserts an edge to the graph.
	 * @param vertexID1 source
	 * @param vertexID2 destination
	 * @param weight weight of edge
	 */ 
	@Override
	public void addEdge ( int vertexID1, int vertexID2, double weight ) {
		this.insert(new Edge(vertexID1, vertexID2, weight));
	}

	/**
	 * Removes edge from graph.
	 * @param vertexID1 source
	 * @param vertexID2 destination
	 * @return true if it successfully removed, false otherwise.
	 */  
	@Override
	public boolean removeEdge ( int vertexID1, int vertexID2 ) {
		LinkedList<Edge> list1 = edgeMap.get(vertexID1);
		if ( list1 == null )
			return false;
		
		boolean result = false;
		Edge edge = new Edge(vertexID1, vertexID2);
		Iterator<Edge> itr = list1.iterator();
		while ( itr.hasNext() ) {
			Edge edge1 = itr.next();
			if ( edge1.equals(edge) ) {
				itr.remove();
				result = true;
				break;
			}
		}

		if ( !isDirected() && result ) {
			Edge edgeOp = new Edge(vertexID2, vertexID1);
			List<Edge> list2 = edgeMap.get(vertexID2);
			itr = list2.iterator();
			while ( itr.hasNext() ) {
				Edge edge2 = itr.next();
				if ( edge2.equals(edgeOp) ) {
					itr.remove();
					break;
				}
			}
		}
		return result;
	}

	/**
	 * Removes vertex from graph according to the id.
	 * @param vertexID id of the vertex
	 * @return true if it removed, false otherwise.
	 */ 
	@Override
	public boolean removeVertex ( int vertexID ) {

		// remove vertex
		if ( vertexMap.remove(vertexID) != null ) 
			numV--;
		else
			return false;

		// remove edges of this vertex
		edgeMap.remove(vertexID);
		for ( Map.Entry<Integer, LinkedList<Edge>> entry: edgeMap.entrySet() ) {
			List<Edge> list = entry.getValue();

			Iterator<Edge> itr = list.iterator();
			while ( itr.hasNext() ) {
				Edge val = itr.next();
				if ( val.getDest() == vertexID )
					itr.remove();			
			}
		}
		return true;
	}

	/**
	 * Removes vertex according to the label, it can remove more than one vertex.
	 * @param label label of the vertex
	 * @return true if it removed, false otherwise. 
	 */ 
	@Override
	public boolean removeVertex ( String label ) {
		int oldNum = numV;
		// hold deleted keys in tree set
		TreeSet<Integer> deletedKeys = new TreeSet<Integer>();

		Iterator<Map.Entry<Integer, Vertex>> itr1 = vertexMap.entrySet().iterator();
		while( itr1.hasNext() ) {
			Map.Entry<Integer, Vertex> entryVertex = itr1.next();
			if ( entryVertex.getValue().getLabel().equals(label) ) {
				deletedKeys.add(entryVertex.getKey());
				edgeMap.remove(entryVertex.getKey());
				itr1.remove();
				numV--;
			}			 
		}

		// check if any vertex is deleted
		if ( oldNum == numV )
			return false;

		// remove edges of this vertex
		for ( Map.Entry<Integer, LinkedList<Edge>> entryEdge: edgeMap.entrySet() ) {
			List<Edge> list = entryEdge.getValue();

			Iterator<Edge> itr = list.iterator();
			while ( itr.hasNext() ) {
				Edge val = itr.next();
				if ( deletedKeys.contains(Integer.valueOf(val.getDest())) )
					itr.remove();
			}
		}
		return true;
	}

	/**
	 * Takes a pair for vertex, and returns subgraph of vertices which has this pair.
	 * @param key key
	 * @param filter filter
	 * @return DynamicGraph that created.
	 */ 
	@Override
	public DynamicGraph filterVertices ( String key, String filter ) {
		MyGraph graph = new MyGraph(true);
		// hold filtered edge hash set to prevent duplication
		HashSet<Edge> edgeSet = new HashSet<Edge>();
		
		for ( Map.Entry<Integer, Vertex> entryVertex: vertexMap.entrySet() ) {
			if ( entryVertex.getValue().checkPair(key, filter) ) {
				graph.addVertex(entryVertex.getValue());
				LinkedList<Edge> list = edgeMap.get(entryVertex.getKey());
				for ( Edge edge: list )
					edgeSet.add(edge);
			}
		}

		for ( Edge edge: edgeSet )  
			graph.insert(edge);
		// set current graphs direction
		graph.directed = isDirected();
		return graph;
	}

	/**
	 * Creates a adjacency matrix version of current graph, and returns.
	 * @return MatrixGraph
	 */ 
	@Override
	public MatrixGraph exportMatrix ( ) {
		MatrixGraph graph = new MatrixGraph(numV, true);
		HashSet<Edge> edgeSet = new HashSet<Edge>();		
		LinkedHashMap<Integer, Integer> keyMap = new LinkedHashMap<Integer, Integer>();

		// set indexes for matrix graph
		int i = 0;
		for ( Integer key: vertexMap.keySet() ) {
			keyMap.put(key, i);
			++i;
		}

		for ( LinkedList<Edge> list: edgeMap.values() )
			for ( Edge edge: list ) 
				edgeSet.add(edge);

		for ( Edge edge: edgeSet )
			graph.insert(new Edge(keyMap.get(edge.getSource()), keyMap.get(edge.getDest()), edge.getWeight()));
		
		graph.setDirection(isDirected());
		return graph;
	}

	/**
	 * Prints the graph. Prints each vertices, and edges are beneath of each vertex.(destination edges)
	 */ 
	@Override
	public void printGraph ( ) {
		System.out.println(this.toString());
	}

	/**
	 * Prints vertices of graph detailed.
	 */ 
	public void printVertices ( ) {
		Iterator<Vertex> itrV = vertexIterator();

		System.out.println("Vertices:\n");
		while ( itrV.hasNext() ) {
			Vertex vertex = itrV.next();
			System.out.println(vertex);
		}		
	}
	/**
	 * Returns String representation of graph. Each vertices are shown, and their edges are shown on below of them.
	 * @return String representation of graph.
	 */ 
	@Override
	public String toString ( ) {
		StringBuilder sb = new StringBuilder();

		sb.append("Adjacency List\n");
		if ( numV == 0 ) {
			sb.append("empty graph\n");
			return sb.toString();
		}

		for ( Map.Entry<Integer, LinkedList<Edge>> entryEdge: edgeMap.entrySet() ) {
			sb.append("vertex (id = " + entryEdge.getKey() + ", label = " + vertexMap.get(entryEdge.getKey()).getLabel() + 
				", weight(vertex) = " + vertexMap.get(entryEdge.getKey()).getWeight() + ") -->\n");
			for( Edge e: entryEdge.getValue() ) {
				
				sb.append("\t" + e + "\n");
			}
		}
		return sb.toString();
	}
}