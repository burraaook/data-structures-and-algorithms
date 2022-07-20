package DijkstraAlgorithmGTU;

import DynamicGraphGTU.*;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;

/**
 * 
 * A Dijkstra Algorithm implementation which uses boosting feature.
 * @author Burak Kocausta
 * @version 1.0 29.05.2022
 */ 
public class BoostedDijkstra {

	/**
	 * A Dijkstra Algorithm which uses boosting feature.
	 * pre: It is assumed that start id is in the graph.
	 * pre: ID's are positive integer
	 * @param graph MyGraph type graph
	 * @param start Start id
	 * @param pred Predecessors for every id
	 * @param dist Distances (id, distance) pair
	 */ 
	public static void boostedDijkstra ( MyGraph graph, int start,
								 HashMap<Integer, Integer> pred, HashMap<Integer, Double> dist ) {
		HashSet<Integer> vMinusS = new HashSet<Integer>();

		// getting vertices
		Iterator<Vertex> itrV = graph.vertexIterator();
		while ( itrV.hasNext() ) {
			Vertex val = itrV.next();
			if ( val.getID() != start )
				vMinusS.add(val.getID());
		}

		// filling pred and dist
		for ( int key : vMinusS ) {
			pred.put(key, start);
			Edge edge = graph.getEdge(start, key);
			if ( edge == null )
				dist.put(key, Double.POSITIVE_INFINITY);
			else
				dist.put(key, edge.getWeight());
		}

		// main loop
		while ( vMinusS.size() != 0 ) {
			double minDist = Double.POSITIVE_INFINITY;
			int u = -1;

			// choose minimum distance edge
			for ( int v : vMinusS ) {
				double val = dist.get(v);
				double boost = 0;

				// check for boost
				String value = graph.getVertex(v).getPair("boosting");
				if( value != null ) {
					try {
						boost = Double.parseDouble(value);
					}
					catch (Exception e) {
						boost = 0;
					}
				}				
				if ( val - boost < minDist ) {
					minDist = val - boost;
					u = v;
				}
			}

			if ( u == -1 )
				u = vMinusS.iterator().next();
			vMinusS.remove(u);

			Iterator<Edge> itrE = graph.edgeIterator(u);
			while ( itrE.hasNext() ) {
				Edge edge = itrE.next();
				int v = edge.getDest();

				// check if it is processed or not
				if( vMinusS.contains(Integer.valueOf(v)) ) {
					double weight = edge.getWeight();
					double weightU = dist.get(u);
					double boost = 0;

					// check for boost
					String value = graph.getVertex(u).getPair("boosting");
					if( value != null ) {
						try {
							boost = Double.parseDouble(value);
						}
						catch (Exception e) {
							boost = 0;
						}
					}
					// if distance is less
					if ( (weightU + weight - boost) < dist.get(v) ) {
						// it might be negative make it 1
						if ( weightU + weight - boost <= 0 )
							dist.put(v, 1.0);
						else
							dist.put(v, weightU + weight - boost);
						pred.put(v, u);
					}
				}
			} // end while
		} // end outer loop
	} // end method
}