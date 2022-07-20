package GraphTraverseGTU;

import DynamicGraphGTU.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Queue;
import java.util.LinkedList;

/**
 * 
 * A traversal algorithms which calculates distance between specialized breadth first search and depth first search on given graph.
 * @author Burak Kocausta
 * @version 1.0 29.05.2022
 */  
public class TraverseGraph {

	/**
	 * Makes bfs and dfs traversals on given graph, returns distance(bfs) - distance(dfs)
	 * @param graph graph to be traversed
	 * @param start starting id of given graph
	 * @return distance(bfs) - distance(dfs)
	 */  
	public static double differenceOfBFSAndDFS ( MyGraph graph, int start ) {

		return breadthFirstSearch(graph, start) - depthFirstSearch(graph, start);
	}

	/**
	 * Makes Depth First Search, which choses shortest path on each decision, and returns the total distance of accessing each vertex.
	 * @param graph graph to be traversed
	 * @param start starting index for depth first search
	 * @return distance(dfs)
	 */ 
	public static double depthFirstSearch ( MyGraph graph, int start ) {
		Map<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
		double distance = 0;

		Iterator<Vertex> itrV = graph.vertexIterator();
		while ( itrV.hasNext() ) {
			Vertex vertex = itrV.next();
			visited.put(vertex.getID(), false);
		}
		
		distance = depthFirstSearch(graph, start, visited);

		// it might be unconnected graph
		for( Map.Entry<Integer, Boolean> entry: visited.entrySet() ) {
			if ( !(entry.getValue()) )
				distance += depthFirstSearch(graph, entry.getKey(), visited);
		}
		return distance;
	}

	/**
	 * Private recursive helper method for depthFirstSearch method.
	 * @param graph graph to be traversed
	 * @param current current id for depth first search
	 * @param visited boolean hashmap for indicating vertex is visited or not.
	 * @return total distance(dfs)
	 */ 
	private static double depthFirstSearch ( MyGraph graph, int current, Map<Integer, Boolean> visited ) {
		visited.put(current, true);
		Iterator<Edge> itr2 = graph.edgeIterator(current);
		double distance = 0;

		while ( itr2.hasNext() ) {
			double min = Double.POSITIVE_INFINITY;
			int neighbor = -1;
			itr2.next();

			// find minimum distance
			Iterator<Edge> itr = graph.edgeIterator(current);
			while ( itr.hasNext() ) {
				Edge edge = itr.next();
				if ( edge.getWeight() < min && !visited.get(edge.getDest()) ) {
					neighbor = edge.getDest();
					min = edge.getWeight();
				}
			}
			if ( neighbor != -1 ) {
				distance += min;
				distance += depthFirstSearch(graph, neighbor, visited);			
			}					
		}//end while
		return distance;
	}

	/**
	 * Makes Breadth First Search, which choses shortest access for a vertex.
	 * @param graph graph to be traversed
	 * @param start starting index for depth first search
	 * @return total distance(bfs)
	 */ 	
	public static double breadthFirstSearch ( MyGraph graph, int start ) {
		HashMap<Integer, Boolean> identified = new HashMap<Integer, Boolean>();
		Queue<Integer> verticeQueue = new LinkedList<Integer>();

		double distance = 0;

		Iterator<Vertex> itrV = graph.vertexIterator();		
		while ( itrV.hasNext() ) {
			Vertex val = itrV.next();
			if ( val.getID() == start )
				identified.put(val.getID(), true);
			else
				identified.put(val.getID(), false);
		}

		verticeQueue.offer(start);
		
		while ( !verticeQueue.isEmpty() ) {

			int current = verticeQueue.remove();

			Iterator<Edge> itr = graph.edgeIterator(current);
			while ( itr.hasNext() ) {
				Edge edge = itr.next();
				int neighbor = edge.getDest();
				double min = Double.POSITIVE_INFINITY;

				// find shortest distance
				Iterator<Edge> itr2 = graph.edgeIterator(current);
				while ( itr2.hasNext() ) {
					Edge edge2 = itr2.next();
					if ( edge2.getWeight() < min && !identified.get(edge2.getDest()) ) {
						neighbor = edge2.getDest();
						min = edge2.getWeight();
					}
				}

				if ( !identified.get(neighbor) ) {
					distance += min;
					identified.put(neighbor, true);
					verticeQueue.offer(neighbor);
				}
			}
			//Finished visiting current
		}
		return distance;
	}
}