import DynamicGraphGTU.*;
import DijkstraAlgorithmGTU.*;
import GraphTraverseGTU.*;

import java.util.Iterator;
import java.util.HashMap;

/**
 * 
 * Driver class for testing DynamicGraphGTU, DijkstraAlgorithmGTU, GraphTraverseGTU packages.
 * @author Burak Kocausta
 * @version 1.0 30.05.2022
 */ 
public class Driver {

	/**
	 * Run the tests.
	 * @param args argument lists
	 */ 
	public static void main ( String[] args ) {

		testMyGraph();
		testTraverseGraph();
		testDijkstra();
		
	}

	/**
	 * Test for MyGraph class
	 */ 
	public static void testMyGraph ( ) {
		System.out.println("____Testing MyGraph Class____\n");

		System.out.println("Create empty undirected graph\n");

		MyGraph graph1 = new MyGraph(false);
		System.out.println(graph1 + "-----------------size = " + graph1.getNumV() + "\n");

		System.out.println("Insert a vertex to that graph\n");	
		graph1.addVertex(new Vertex(0, "v0", 52.3));
		System.out.println(graph1 + "-----------------size = " + graph1.getNumV() + "\n");		

		System.out.println("Insert more vertex to that graph");
		graph1.addVertex(new Vertex(1, "v1", 12.9));
		graph1.newVertex("v2", 0.9);
		graph1.addVertex(new Vertex(3, "v3", 67.0));
		graph1.addVertex(new Vertex(4, "v4", 11.31));
		graph1.addVertex(new Vertex(5, "v5", 22.34));
		graph1.addVertex(new Vertex(6, "v6", 12.13));
		graph1.addVertex(new Vertex(7, "v7", 71.3));
		graph1.addVertex(new Vertex(8, "v8", 62.3));
		graph1.newVertex("v9", 15.9);
		graph1.newVertex("v10", 22.39);
		graph1.printGraph();

		System.out.println("\nPrint vertices of graph\n");
		graph1.printVertices();
		
		System.out.println("\nAdd an edge to graph\n");
		graph1.addEdge(4,6, 3.1);

		graph1.printGraph();

		System.out.println("\nAdd more edges to the graph\n");
		graph1.addEdge(0,10, 22.1);
		graph1.addEdge(2,6, 3.6);
		graph1.addEdge(8,7, 15.9);
		graph1.addEdge(3,8, 31.52);
		graph1.addEdge(1,3, 15.9);
		graph1.addEdge(3,2, 35.20);
		graph1.insert(new Edge(1,5));
		graph1.insert(new Edge(0,9));
		graph1.printGraph();

		System.out.println("\nExport matrix of current graph\n");
		System.out.println(graph1.exportMatrix());

		System.out.println("\nRemove a vertex from graph with id 6\n");
		graph1.removeVertex(6);
		graph1.printGraph();
		graph1.printVertices();
		
		System.out.println("\nRemove a vertex from graph with label v10\n");
		graph1.removeVertex("v10");
		graph1.printGraph();
		
		System.out.println("\nTry to remove unexisted vertex\n");
		graph1.removeVertex(30);
		graph1.printGraph();		

		System.out.println("\nExport matrix of current graph\n");
		System.out.println(graph1.exportMatrix());

		System.out.println("\nRemove a vertex from graph with id 8\n");
		graph1.removeVertex(8);
		System.out.println(graph1 + "-----------------size = " + graph1.getNumV() + "\n");
		
		System.out.println("\nRemove an edge between 1 and 5\n");
		graph1.removeEdge(1, 5);
		System.out.println(graph1 + "-----------------size = " + graph1.getNumV() + "\n");
		
		System.out.println("\nRemove an edge between 1 and 3\n");
		graph1.removeEdge(1, 3);
		graph1.printGraph();

		System.out.println("\nRemove an edge between 2 and 3\n");
		graph1.removeEdge(2, 3);
		graph1.printGraph();

		System.out.println("\nRemove an edge between 0 and 9\n");
		graph1.removeEdge(0, 9);
		graph1.printGraph();

		System.out.println("\nTry to remove unexisted edge\n");
		graph1.removeEdge(1, 5);
		graph1.printGraph();

	
		System.out.println("\nInsert arbitrary id vertices\n");

		graph1.addVertex(new Vertex(19, "v13", 12.9));
		graph1.newVertex("v12", 0.9);
		graph1.addVertex(new Vertex(16, "v14", 67.0));
		graph1.addVertex(new Vertex(31, "v15", 11.31));
		graph1.addVertex(new Vertex(24, "v16", 22.34));
		graph1.addVertex(new Vertex(22, "v17", 12.13));
		graph1.addVertex(new Vertex(21, "v18", 71.3));
		graph1.addVertex(new Vertex(37, "v19", 62.3));
		graph1.newVertex("v19", 15.9);
		graph1.newVertex("v19", 22.39);
		System.out.println(graph1 + "-----------------size = " + graph1.getNumV() + "\n");
		graph1.printVertices();
		
		System.out.println("\nAdd edges to the graph\n");		

		graph1.addEdge(37, 22, 10.6);
		graph1.addEdge(39, 37, 24.12);
		graph1.addEdge(16, 37, 41.3);
		graph1.addEdge(0, 16, 6.5);
		graph1.addEdge(3, 9, 7.31);
		graph1.addEdge(20, 24, 8.16);
		graph1.addEdge(14, 5, 5.42);
		graph1.addEdge(3, 14, 12.31);
		
		graph1.printGraph();


		System.out.println("\nRemove vertices which have label v19\n");
		graph1.removeVertex("v19");
		graph1.printGraph();
		graph1.printVertices();

		System.out.println("\nAdd more edges to the graph\n");
		graph1.addEdge(9, 19, 4.21);
		graph1.addEdge(16, 24, 2.11);
		graph1.addEdge(22, 31, 11.5);
		graph1.addEdge(1, 21, 70.81);
		graph1.addEdge(2, 1, 131.62);		
		graph1.printGraph();

		System.out.println("\nAdd Pairs to the vertices using iterator\n");
		Iterator<Vertex> itr = graph1.vertexIterator();
		while ( itr.hasNext() ) {
			Vertex val = itr.next();
			if (val.getID() % 2 == 0) 
				val.addPair("length", "5");
			else
				val.addPair("length", Integer.valueOf(val.getID()*2).toString());

			val.addPair("boosting", Integer.valueOf(val.getID()*3).toString());

			if ( val.getID() % 4 == 0)
				val.addPair("color", "blue");
			else if ( val.getID() % 3 == 0 )
				val.addPair("color", "red");
			else if ( val.getID() % 5 == 0 )
				val.addPair("color", "green");
			else
				val.addPair("color", "pink");
		}

		graph1.printVertices();
		System.out.print("\n");
		graph1.printGraph();

		System.out.println("\nTesting filter method");
		System.out.println("\nFiltered Graph(color, blue):");
		DynamicGraph filtered1 = graph1.filterVertices("color", "blue");
		filtered1.printGraph();


		System.out.println("\nFiltered Graph(length, 5)");
		DynamicGraph filtered2 = graph1.filterVertices("length", "5");
		filtered2.printGraph();		
		
		System.out.println("\nTesting other methods\n");

		graph1.printGraph();
		System.out.println("graph1.getVertex(9) returns: " + graph1.getVertex(9));
		System.out.println("\ngraph1.getVertex(30)(not exists) returns:\n" + graph1.getVertex(30));
		System.out.println("\ngraph1.isEdge(9,19) = " + graph1.isEdge(9, 19));
		System.out.println("\ngraph1.isEdge(5,16)(not exists) = " + graph1.isEdge(5, 16));
		System.out.println("\ngraph1.getEdge(24,16) = " + graph1.getEdge(24, 16));
		System.out.println("\ngraph1.getEdge(4,7)(not exists) = " + graph1.getEdge(4, 7));


		System.out.println("\nTest a directed graph\n");

		MyGraph graph2 = new MyGraph(true);
		graph2.addVertex(new Vertex(0, "v0", 6.0).addPair("color", "purple"));
		graph2.addVertex(new Vertex(1, "v1", 13.31).addPair("color", "purple"));
		graph2.addVertex(new Vertex(2, "v2", 21.34).addPair("color", "purple"));
		graph2.addVertex(new Vertex(3, "v3", 12.13).addPair("color", "purple"));
		graph2.addVertex(new Vertex(4, "v4", 76.3).addPair("color", "purple"));
		graph2.addVertex(new Vertex(5, "v5", 7.3).addPair("color", "purple"));		
		graph2.addVertex(new Vertex(6, "v6", 7.0).addPair("boosting", "6"));
		graph2.addVertex(new Vertex(7, "v7", 1.31).addPair("boosting", "6"));
		graph2.addVertex(new Vertex(8, "v8", 25.34));
		graph2.addVertex(new Vertex(9, "v9", 11.43).addPair("color", "green"));
		graph2.addVertex(new Vertex(10, "v10", 72.4).addPair("boosting", "6"));
		graph2.addVertex(new Vertex(11, "v11", 65.3).addPair("boosting", "9"));

		System.out.println("Inserting vertices to directed graph\n");
		graph2.printVertices();
		System.out.println("\ngraph2.isDirected() = " + graph2.isDirected());

		System.out.println("\nInserting edges to directed graph\n");
		graph2.addEdge(0, 11, 12.6);
		graph2.addEdge(1, 5, 25.12);
		graph2.addEdge(2, 9, 46.3);
		graph2.addEdge(5, 1, 16.5);
		graph2.addEdge(3, 1, 12.31);
		graph2.addEdge(4, 9, 51.16);
		graph2.addEdge(9, 6, 56.42);
		graph2.addEdge(3, 9, 11.31);
		graph2.addEdge(8, 6, 10.6);
		graph2.addEdge(6, 4, 24.12);
		graph2.addEdge(7, 3, 41.3);
		graph2.addEdge(0, 2, 6.5);
		graph2.addEdge(2, 10, 7.31);				
		graph2.insert(new Edge(10,1, 5.3));
		graph2.insert(new Edge(11,6));

		graph2.printGraph();

		System.out.println("\nExport matrix of current graph\n");
		System.out.println(graph2.exportMatrix());

		graph2.printVertices();
		System.out.println("\nFiltered Graph(color, purple):");
		graph2.filterVertices("color", "purple").printGraph();
		
		System.out.println("\nAdjancecy Matrix of Filtered Graph(color, purple):");
		System.out.println(graph2.filterVertices("color", "purple").exportMatrix());

		graph2.printGraph();
		System.out.println("\nRemove some vertices from graph\n");

		graph2.removeVertex(4);
		graph2.removeVertex(1);
		graph2.removeVertex("v10");

		graph2.printGraph();

		System.out.println("\nRemove some edges from graph\n");

		graph2.removeEdge(0, 2);
		graph2.removeEdge(11, 6);
		graph2.removeEdge(9, 6);
		graph2.printGraph();

		System.out.println("\n__Test Results for MyGraph and DynamicGraph__");
		System.out.println("1- Insertion of edges and vertices are tested.");
		System.out.println("2- Removal of edges and vertices are tested.");
		System.out.println("3- Export Matrix and Filter methods are tested.");
		System.out.println("4- printGraph() and toString are tested.");		
		System.out.println("5- Graph is tested after deletion and insertion.");
		System.out.println("6- Graph is tested for nonconsecutive ID's.");
		System.out.println("7- Iterator's are tested, and both directed, undirected graphs are tested.");
		System.out.println("8- Other DynamicGraph, and MyGraph methods are tested.\n");
	}

	/**
	 * Test for TraverseGraph class.
	 */ 
	public static void testTraverseGraph ( ) {
		System.out.println("____Testing TraverseGraph Class(calculating difference)____\n");

		MyGraph graph1 = new MyGraph(false);
		graph1.addVertex(new Vertex(0, "v0", 5.0));
		graph1.addVertex(new Vertex(1, "v1", 15.0));
		graph1.addVertex(new Vertex(2, "v2", 2.5));
		graph1.addVertex(new Vertex(3, "v3", 3.0));
		graph1.addVertex(new Vertex(4, "v4", 4.0));
		graph1.addVertex(new Vertex(5, "v5", 10.3));
		graph1.addVertex(new Vertex(6, "v6", 63.0));
		graph1.addVertex(new Vertex(7, "v7", 40.0));
		graph1.addVertex(new Vertex(8, "v8", 15.9));		

		graph1.addEdge(0, 1, 10.0);
		graph1.addEdge(0, 2, 15.0);
		graph1.addEdge(0, 6, 70.0);		
		graph1.addEdge(1, 3, 20.0);
		graph1.addEdge(1, 8, 70.0);
		graph1.addEdge(2, 3, 30.0);
		graph1.addEdge(2, 5, 15.0);
		graph1.addEdge(3, 4, 65.0);
		graph1.addEdge(3, 7, 10.0);
		graph1.addEdge(5, 6, 50.0);
		graph1.addEdge(5, 7, 25.0);		

		graph1.printVertices();
		System.out.print("\n");
		graph1.printGraph();

		System.out.println("Start id is 0 for traversals\n");
		System.out.println( "Distance(BFS) - Distance(DFS) = " + TraverseGraph.differenceOfBFSAndDFS(graph1, 0) + "\n");

		System.out.println("\nTest for another graph\n");
		MyGraph graph2 = new MyGraph(false);
		graph2.addVertex(new Vertex(0, "v0", 15.0));
		graph2.addVertex(new Vertex(1, "v1", 25.0));
		graph2.addVertex(new Vertex(2, "v2", 2.3));
		graph2.addVertex(new Vertex(3, "v3", 4.0));
		graph2.addVertex(new Vertex(4, "v4", 9.0));
		graph2.addVertex(new Vertex(5, "v5", 10.13));
		graph2.addVertex(new Vertex(6, "v6", 23.0));
		graph2.addVertex(new Vertex(7, "v7", 50.0));
		graph2.addVertex(new Vertex(8, "v8", 25.9));		
		graph2.addVertex(new Vertex(9, "v9", 135.9));

		graph2.addEdge(0, 1, 10.0);
		graph2.addEdge(0, 5, 45.0);
		graph2.addEdge(0, 6, 25.0);		
		graph2.addEdge(1, 2, 20.0);
		graph2.addEdge(1, 4, 35.0);
		graph2.addEdge(2, 4, 30.0);
		graph2.addEdge(2, 6, 9.0);
		graph2.addEdge(3, 4, 20.0);
		graph2.addEdge(3, 9, 30.0);
		graph2.addEdge(3, 8, 10.0);
		graph2.addEdge(3, 5, 15.0);
		graph2.addEdge(5, 7, 70.0);
		graph2.addEdge(7, 8, 50.0);
		graph2.addEdge(8, 9, 15.0);
	
		graph2.printVertices();
		System.out.print("\n");
		graph2.printGraph();

		System.out.println("Start id is 4 for traversals\n");
		System.out.println( "Distance(BFS) - Distance(DFS) = " + TraverseGraph.differenceOfBFSAndDFS(graph2, 4) + "\n");		
	}

	/**
	 * Test for BoostedDijkstra class.
	 */ 
	public static void testDijkstra ( ) {
		System.out.println("\n____Testing BoostedDijkstra Class____\n");
		MyGraph graph1 = new MyGraph(true);
		graph1.addVertex(new Vertex(0, "v0", 17.0));
		graph1.addVertex(new Vertex(1, "v1", 11.21).addPair("boosting", "35.0"));
		graph1.addVertex(new Vertex(2, "v2", 42.34).addPair("boosting", "20.0"));
		graph1.addVertex(new Vertex(3, "v3", 13.33).addPair("boosting", "5.0"));
		graph1.addVertex(new Vertex(4, "v4", 16.5));


		graph1.addEdge(0, 1, 50.0);
		graph1.addEdge(0, 2, 10.0);		
		graph1.addEdge(0, 3, 20.0);
		graph1.addEdge(1, 4, 10.0);
		graph1.addEdge(1, 2, 10.0);
		graph1.addEdge(2, 3, 25.0);
		graph1.addEdge(3, 4, 30.0);
		graph1.printVertices();
		System.out.print("\n");
		graph1.printGraph();

		System.out.println("\nStarting id is 0\n");

		HashMap<Integer, Integer> pred1 = new HashMap<Integer, Integer>();
		HashMap<Integer, Double> dist1 = new HashMap<Integer, Double>();

		BoostedDijkstra.boostedDijkstra(graph1, 0, pred1, dist1);

		System.out.println("Predecessors for each id = " + pred1);
		System.out.println("Distances to each id = " + dist1);	

		System.out.println("\nTest for another graph\n");			

		MyGraph graph2 = new MyGraph(true);
		graph2.addVertex(new Vertex(0, "v0", 7.0));
		graph2.addVertex(new Vertex(1, "v1", 1.31).addPair("boosting", "10.0"));
		graph2.addVertex(new Vertex(2, "v2", 2.34).addPair("boosting", "20.0"));
		graph2.addVertex(new Vertex(3, "v3", 12.13).addPair("boosting", "5.0"));
		graph2.addVertex(new Vertex(4, "v4", 7.3).addPair("boosting", "5.0"));
		graph2.addVertex(new Vertex(5, "v5", 11.1).addPair("boosting", "30.0"));
		graph2.addVertex(new Vertex(6, "v6", 27.34).addPair("boosting", "10.0"));
		graph2.addVertex(new Vertex(7, "v7", 2.13).addPair("boosting", "30.0"));
		graph2.addVertex(new Vertex(8, "v8", 72.3));


		graph2.addEdge(0, 1, 20.0);
		graph2.addEdge(0, 2, 20.0);		
		graph2.addEdge(0, 3, 10.0);
		graph2.addEdge(0, 5, 15.0);
		graph2.addEdge(1, 2, 15.0);
		graph2.addEdge(1, 6, 30.0);
		graph2.addEdge(1, 4, 50.0);
		graph2.addEdge(2, 8, 90.0);
		graph2.addEdge(3, 8, 40.0);								
		graph2.addEdge(3, 5, 25.0);
		graph2.addEdge(3, 4, 45.0);
		graph2.addEdge(4, 5, 60.0);
		graph2.addEdge(4, 7, 10.0);		
		graph2.addEdge(5, 6, 10.0);								
		graph2.addEdge(6, 2, 30.0);
		graph2.addEdge(7, 8, 80.0);


		graph2.printVertices();
		System.out.print("\n");
		graph2.printGraph();

		System.out.println("\nStarting id is 0\n");

		HashMap<Integer, Integer> pred2 = new HashMap<Integer, Integer>();
		HashMap<Integer, Double> dist2 = new HashMap<Integer, Double>();

		BoostedDijkstra.boostedDijkstra(graph2, 0, pred2, dist2);

		System.out.println("Predecessors for each id = " + pred2);
		System.out.println("Distances to each id = " + dist2);							
	}
}