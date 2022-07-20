import BinaryHeapGTU.*;
import BinarySearchTreeGTU.*;

/**
 * 
 * Driver class for testing.
 * @author Burak Kocausta
 * @version 1.0 11.04.2022
 */ 
public class Driver {

	/**
	 * Call tests from here.
	 */ 
	public static void main ( String args[] ) {
		testLinkedBinaryHeap();
		testBinarySearchTree();
	}

	/**
	 * 
	 * Tests LinkedBinaryHeap class and BinaryTree class.
	 */ 
	public static void testLinkedBinaryHeap ( ) {

		System.out.println ( "___Testing LinkedBinaryHeap_________________________" );
		System.out.println( "create LinkedBinaryHeap\n" );
		LinkedBinaryHeap<Integer> lbh1 = new LinkedBinaryHeap<Integer>();
		System.out.println(lbh1 + "---------- size = " + lbh1.size() + "\n");

		System.out.println ( "\nadd some Integer's to lbh1" );
		
		lbh1.add(Integer.valueOf(22));
		System.out.println(lbh1 + "---------- size = " + lbh1.size() + "\n");

		System.out.println("lbh1.isLeaf() returns = " + lbh1.isLeaf());

		lbh1.add(Integer.valueOf(15));
		System.out.println(lbh1 + "---------- size = " + lbh1.size() + "\n");

		System.out.println("lbh1.isLeaf() returns = " + lbh1.isLeaf());

		lbh1.add(Integer.valueOf(25));
		System.out.println(lbh1 + "---------- size = " + lbh1.size() + "\n");
		lbh1.offer(Integer.valueOf(47));
		System.out.println(lbh1 + "---------- size = " + lbh1.size() + "\n");
		lbh1.add(Integer.valueOf(11));
		System.out.println(lbh1 + "---------- size = " + lbh1.size() + "\n");
		lbh1.offer(Integer.valueOf(52));
		System.out.println(lbh1 + "---------- size = " + lbh1.size() + "\n");

		System.out.println( "\nusing poll(remove) 3 times" );
		lbh1.poll();
		System.out.println(lbh1 + "---------- size = " + lbh1.size() + "\n");
		lbh1.poll();
		System.out.println(lbh1 + "---------- size = " + lbh1.size() + "\n");		
		lbh1.poll();
		System.out.println(lbh1 + "---------- size = " + lbh1.size() + "\n");

		System.out.println( "\nadd much more values" );
		lbh1.add(Integer.valueOf(44));
		lbh1.add(Integer.valueOf(5));
		lbh1.add(Integer.valueOf(44));
		lbh1.add(Integer.valueOf(24));
		lbh1.add(Integer.valueOf(19));
		lbh1.add(Integer.valueOf(17));
		lbh1.add(Integer.valueOf(31));
		lbh1.add(Integer.valueOf(2));
		lbh1.add(Integer.valueOf(65));

		System.out.println(lbh1 + "---------- size = " + lbh1.size() + "\n");
		System.out.println( "\ncontinue adding" );

		lbh1.add(Integer.valueOf(16));
		lbh1.add(Integer.valueOf(42));
		lbh1.add(Integer.valueOf(123));
		lbh1.add(Integer.valueOf(6));
		lbh1.add(Integer.valueOf(22));				

		lbh1.add(Integer.valueOf(13));
		lbh1.add(Integer.valueOf(21));
		lbh1.add(Integer.valueOf(56));
		lbh1.add(Integer.valueOf(35));
		lbh1.add(Integer.valueOf(1));				
	
		System.out.println(lbh1 + "---------- size = " + lbh1.size() + "\n");					
		
		
		System.out.println("Getting left sub tree");
		BinaryTree<Integer> lbh2 = lbh1.getLeftSubtree();
		System.out.println("\nlbh2(left sub tree of lbh1)\n" + lbh2 + "----------\n");
		System.out.println("right sub tree of lbh1\n" + lbh1.getRightSubtree() + "----------\n");

		System.out.println( "Remove all nodes from lbh1(after removing all try remove more for error handling)\n" );
		
		int treeSize = lbh1.size();
		for(int i = 0; i < treeSize; ++i) 
			lbh1.poll();
		lbh1.poll();
		lbh1.poll();
		System.out.println(lbh1 + "---------- size = " + lbh1.size() + "\n");		

		System.out.println( "Add new nodes to lbh1" );

		lbh1.add(Integer.valueOf(25));
		lbh1.add(Integer.valueOf(47));
		lbh1.add(Integer.valueOf(11));
		lbh1.add(Integer.valueOf(52));
		lbh1.add(Integer.valueOf(44));
		lbh1.add(Integer.valueOf(1));
		lbh1.add(Integer.valueOf(18));
		lbh1.add(Integer.valueOf(152));
		lbh1.add(Integer.valueOf(4));
		lbh1.add(Integer.valueOf(61));
		lbh1.add(Integer.valueOf(23));

		System.out.println(lbh1 + "---------- size = " + lbh1.size() + "\n");

		System.out.println("\nCreate lbh3 and add some nodes");
		LinkedBinaryHeap<Integer> lbh3 = new LinkedBinaryHeap<Integer>();
		lbh3.add(Integer.valueOf(7));
		lbh3.add(Integer.valueOf(9));
		lbh3.add(Integer.valueOf(15));
		lbh3.add(Integer.valueOf(2));
		lbh3.add(Integer.valueOf(31));
		lbh3.add(Integer.valueOf(8));

		System.out.println("\nlbh3\n" + lbh3 + "--------- size = " + lbh3.size() + "\n");

		System.out.println( "Merge lbh1 and lbh3 using lbh1.merge(lbh3)\n" );
		lbh1.merge(lbh3);
		System.out.println("lbh1(after merging)\n" + lbh1 + "--------- size = " + lbh1.size() + "\n");

		System.out.println("\nAdd some values to merged tree");
		lbh1.add(Integer.valueOf(41));
		lbh1.add(Integer.valueOf(68));
		lbh1.add(Integer.valueOf(54));
		lbh1.add(Integer.valueOf(33));
		lbh1.add(Integer.valueOf(23));
		lbh1.add(Integer.valueOf(5));
	
		System.out.println("lbh1\n" + lbh1 + "--------- size = " + lbh1.size() + "\n");
		
		System.out.println("\nlbh3\n" + lbh3 + "---------");
		System.out.println("\nIncrement 7 with 14");
		lbh3.increment(Integer.valueOf(7), Integer.valueOf(14));
		System.out.println("lbh3\n" + lbh3 + "---------");		

		try {
			System.out.println("lbh3.peek() returns = " + lbh3.peek());
			System.out.println("lbh3.element() returns = " + lbh3.element() + "\n");
		}
		catch(Exception e) {
			System.out.println(e);
		}

		System.out.println("Check LinkedBinaryHeap with Strings");
		LinkedBinaryHeap<String> lbh4 = new LinkedBinaryHeap<String>();

		System.out.println("add some strings to lbh4\n");
		lbh4.add("car");
		System.out.println(lbh4 + "---------- size = " + lbh4.size() + "\n");			
		lbh4.add("aisle");
		System.out.println(lbh4 + "---------- size = " + lbh4.size() + "\n");	
		lbh4.add("chrome");
		System.out.println(lbh4 + "---------- size = " + lbh4.size() + "\n");			
		lbh4.add("burak");
		System.out.println(lbh4 + "---------- size = " + lbh4.size() + "\n");			
		lbh4.add("zone");
		System.out.println(lbh4 + "---------- size = " + lbh4.size() + "\n");

		System.out.println("poll(remove) from lbh4");
		lbh4.poll();
		System.out.println(lbh4 + "---------- size = " + lbh4.size() + "\n");
		System.out.println("Add more values to lbh4");

		lbh4.offer("run");
		System.out.println(lbh4 + "---------- size = " + lbh4.size() + "\n");		
		lbh4.add("anchor");
		System.out.println(lbh4 + "---------- size = " + lbh4.size() + "\n");		
		lbh4.add("time");
		System.out.println(lbh4 + "---------- size = " + lbh4.size() + "\n");		
		lbh4.add("beat");
		System.out.println(lbh4 + "---------- size = " + lbh4.size() + "\n");
		
		System.out.println("\nIncrement burak with joy");
		lbh4.increment("burak", "joy");
		System.out.println("lbh4\n" + lbh4 + "---------");	
		
		System.out.println("Check if tree is perfect = " + lbh4.isPerfect());


		lbh4.poll();
		System.out.println("Poll from tree\n");
		System.out.println(lbh4 + "---------- size = " + lbh4.size() + "\n");
		System.out.println("Check if tree is perfect = " + lbh4.isPerfect());


		System.out.println("\n__Test Results for LinkedBinaryHeap and BinaryTree__");
		System.out.println("1- Insertion and removal are tested.");
		System.out.println("2- Hiearchy is tested.");
		System.out.println("3- Merge and increment operations are tested.");
		System.out.println("4- Other public methods and constructors are tested.");		
		System.out.println("5- Polimorphism and inheritance between these two classes are tested.");
		System.out.println("6- Error handlings are tested.");
		System.out.println("7- More than one class is used during test.");
		System.out.println("8- Tested with more than one different size.\n");		
	}

	/**
	 * 
	 * Tests BinarySearchTree class and SearchTree interface.
	 */ 
	public static void testBinarySearchTree ( ) {
		
		System.out.println ( "___Testing BinarySearchTree_________________________" );

		System.out.println ( "\nCreate new Integer BinarySearchTree and assign it to SearchTree reference.\n" );
		SearchTree<Integer> bst1 = new BinarySearchTree<Integer>();

		System.out.println("bst1\n" + bst1 + "----------\n");
		
		System.out.println( "Adding values to the bst1\n" );
		bst1.add(Integer.valueOf(25));
		System.out.println(bst1 + "----------\n");
		bst1.add(Integer.valueOf(22));
		System.out.println(bst1 + "----------\n");		
		bst1.add(Integer.valueOf(7));
		System.out.println(bst1 + "----------\n");
		bst1.add(Integer.valueOf(39));
		System.out.println(bst1 + "----------\n");				
		bst1.add(Integer.valueOf(18));
		System.out.println(bst1 + "----------\n");	

		System.out.println("Removing 18\n" + "after removing:\n");
		bst1.delete(Integer.valueOf(18));
		System.out.println(bst1 + "----------\n");	
		System.out.println("Removing 22\n" + "after removing:\n");
		bst1.delete(Integer.valueOf(22));
		System.out.println(bst1 + "----------\n");
		System.out.println("Removing 25\n" + "after removing:\n");
		bst1.delete(Integer.valueOf(25));
		System.out.println(bst1 + "----------\n");

		System.out.println("Removing 7 and 39 consecutively and remove 5 from empty tree(for testing error handling)\n");
		bst1.remove(Integer.valueOf(7));
		System.out.println(bst1 + "----------\n");

		bst1.remove(Integer.valueOf(39));
		bst1.remove(Integer.valueOf(5));		
		System.out.println(bst1 + "----------\n");
		

		System.out.println( "Adding values again to the bst1\n" );
		bst1.add(Integer.valueOf(25));
		bst1.add(Integer.valueOf(22));
		bst1.add(Integer.valueOf(7));
		bst1.add(Integer.valueOf(39));
		bst1.add(Integer.valueOf(18));
		System.out.println(bst1 + "----------\n");

		System.out.println( "Trying to add same value to bst1 (18)\n" );		
		bst1.add(Integer.valueOf(18));
		System.out.println(bst1 + "----------\n");

		System.out.println( "Trying to remove unexisted value from bst1 (3)\n" );		
		bst1.remove(Integer.valueOf(3));
		System.out.println(bst1 + "----------\n");

		System.out.println( "bst1.find(25) returns = " + bst1.find(25) + "\n"
							 + "bst1.find(3) returns = " + bst1.find(3) + "\n" );
		System.out.println( "bst1.contains(7) returns = " + bst1.contains(7) + "\n"
							 + "bst1.contains(1) returns = " + bst1.contains(1) + "\n" );

		System.out.println( "Adding more data to bst1\n" );
		bst1.add(Integer.valueOf(3));
		bst1.add(Integer.valueOf(33));
		bst1.add(Integer.valueOf(38));
		bst1.add(Integer.valueOf(35));
		bst1.add(Integer.valueOf(47));	
		System.out.println(bst1 + "----------\n");

		System.out.println("Removing 25\n" + "after removing:\n");
		bst1.delete(Integer.valueOf(25));
		System.out.println(bst1 + "----------\n");

		System.out.println("Removing 39\n" + "after removing:\n");
		bst1.delete(Integer.valueOf(39));
		System.out.println(bst1 + "----------\n");	


		System.out.println( "Adding more data to bst1\n" );

		bst1.add(Integer.valueOf(67));
		bst1.add(Integer.valueOf(40));
		System.out.println(bst1 + "----------\n");	

		System.out.println("Removing 22\n" + "after removing:\n");
		bst1.delete(Integer.valueOf(22));
		System.out.println(bst1 + "----------\n");

		System.out.println("Removing 35\n" + "after removing:\n");
		bst1.delete(Integer.valueOf(35));
		System.out.println(bst1 + "----------\n");	

		System.out.println( "Adding more data to bst1\n" );

		bst1.add(Integer.valueOf(12));
		bst1.add(Integer.valueOf(5));
		bst1.add(Integer.valueOf(17));		
		bst1.add(Integer.valueOf(52));
		bst1.add(Integer.valueOf(25));	
		System.out.println(bst1 + "----------\n");	


		System.out.println("Removing 67\n" + "after removing:\n");
		bst1.remove(Integer.valueOf(67));
		System.out.println(bst1 + "----------\n");

		System.out.println("Removing 40\n" + "after removing:\n");
		bst1.remove(Integer.valueOf(40));
		System.out.println(bst1 + "----------\n");


		System.out.println( "Adding more data to bst1\n" );
		bst1.add(Integer.valueOf(1));
		bst1.add(Integer.valueOf(36));
		bst1.add(Integer.valueOf(75));
		bst1.add(Integer.valueOf(48));
		bst1.add(Integer.valueOf(8));
		System.out.println(bst1 + "----------\n");

		System.out.println("Removing 33\n" + "after removing:\n");
		bst1.remove(Integer.valueOf(33));
		System.out.println(bst1 + "----------\n");

		System.out.println("Removing 7\n" + "after removing:\n");
		bst1.remove(Integer.valueOf(7));
		System.out.println(bst1 + "----------\n");				


		System.out.println( "bst1.find(75) returns = " + bst1.find(75) + "\n"
							 + "bst1.find(1) returns = " + bst1.find(1) + "\n" );
		System.out.println( "bst1.contains(47) returns = " + bst1.contains(47) + "\n"
							 + "bst1.contains(36) returns = " + bst1.contains(36) + "\n" );

		System.out.println("Removing 18\n" + "after removing:\n");
		bst1.remove(Integer.valueOf(18));
		System.out.println(bst1 + "----------\n");

		System.out.println("Removing 25\n" + "after removing:\n");
		bst1.remove(Integer.valueOf(25));
		System.out.println(bst1 + "----------\n");	

		System.out.println("Removing 38\n" + "after removing:\n");
		bst1.remove(Integer.valueOf(38));
		System.out.println(bst1 + "----------\n");

		System.out.println ( "\nCreate new String BinarySearchTree and assign it to SearchTree reference.\n" );
		SearchTree<String> bst2 = new BinarySearchTree<String>("laughter");

		System.out.println("bst2\n" + bst2 + "----------\n");

		System.out.println("Add some datas to bst2\n");

		bst2.add("game");
		bst2.add("power");
		bst2.add("climax");
		bst2.add("motivation");
		bst2.add("joy");		
		bst2.add("inside");
		bst2.add("day");
		bst2.add("bark");
		bst2.add("shore");		
		bst2.add("throw");
		bst2.add("possession");
		bst2.add("titane");
		bst2.add("assure");		
		bst2.add("architecture");
		bst2.add("assemble");
		bst2.add("cocteau");
		bst2.add("feasible");		
		bst2.add("inline");
		bst2.add("danger");
		bst2.add("soaked");
		bst2.add("kind");		
		bst2.add("foggy");
		bst2.add("law");
		bst2.add("queen");
		bst2.add("xymox");		
		System.out.println(bst2 + "----------\n");

		System.out.println( "bst2.find(\"law\") returns = " + bst2.find("law") + "\n"
							 + "bst2.find(\"danger\") returns = " + bst2.find("danger") + "\n" );
		System.out.println( "bst2.contains(\"shore\") returns = " + bst2.contains("shore") + "\n"
							 + "bst2.contains(\"emily\") returns = " + bst2.contains("emily") + "\n" );

		System.out.println("Removing titane\n" + "after removing:\n");
		bst2.remove("titane");
		System.out.println(bst2 + "----------\n");

		System.out.println("Removing bark\n" + "after removing:\n");
		bst2.remove("bark");
		System.out.println(bst2 + "----------\n");

		System.out.println("Removing laughter\n" + "after removing:\n");
		bst2.remove("laughter");
		System.out.println(bst2 + "----------\n");


		System.out.println("\n__Test Results for BinarySearchTree__");
		System.out.println("1- Insertion and removal are tested.");
		System.out.println("2- Hiearchy is tested.");
		System.out.println("3- All interface operations are tested.");
		System.out.println("4- Constructors are tested.");		
		System.out.println("5- Polimorphism and inheritance are tested.");
		System.out.println("6- Error handlings are tested.");
		System.out.println("7- More than one class type are used during test.");
		System.out.println("8- Tested with more than one different size.\n");	

	}
}