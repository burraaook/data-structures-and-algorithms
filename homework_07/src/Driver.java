import BinarySearchTreeGTU.*;
import CustomSkipListGTU.*;

public class Driver {


	public static void main ( String args[] ) {
		testConvertToSearchTree();
		testConvertToAVLTree();
	
		testCustomSkipList();

	}

	public static void testConvertToSearchTree ( ) {
		
		System.out.println("\n____Testing convertToSearchTree()____\n");
		System.out.println("\nBinary Tree:");
		BinaryTree<Integer> bTree1 = new BinaryTree<Integer>(15
															, new BinaryTree<Integer>(23
																, new BinaryTree<Integer>(45)
																, new BinaryTree<Integer>(11))
															, new BinaryTree<Integer>(51
																, new BinaryTree<Integer>(12), new BinaryTree<Integer>(13
																	, new BinaryTree<Integer>(16, new BinaryTree<Integer>(12), null)
															, new BinaryTree<Integer>(11)))
															);
	
		System.out.println(bTree1);

		System.out.print("array: ");
		Integer[] arr1 = new Integer[]{3,7,1,21,15,2,6,8,93,72};
		for ( int i = 0; i < arr1.length; ++i ) 
			System.out.print(arr1[i] + " ");
		System.out.print("\n");
		BinarySearchTree<Integer> bst1 = BinaryTree.convertToSearchTree(bTree1, arr1);

		System.out.println("\nBinary Search Tree: \n" + bst1 + "--------------");

		System.out.println("\nBinary Tree:");
		BinaryTree<Integer> bTree2 = new BinaryTree<Integer>(15
															, new BinaryTree<Integer>(3
																, new BinaryTree<Integer>(4, new BinaryTree<Integer>(1), null)
																, new BinaryTree<Integer>(11, null, new BinaryTree<Integer>(5)))
															, new BinaryTree<Integer>(1
																, new BinaryTree<Integer>(12, 
																	new BinaryTree<Integer>(1, new BinaryTree<Integer>(4), 
																		new BinaryTree<Integer>(1)), null)
																, new BinaryTree<Integer>(12, new BinaryTree<Integer>(6, 
																	new BinaryTree<Integer>(2), null)
															, new BinaryTree<Integer>(1)))
															);
	
		System.out.println(bTree2);

		System.out.print("array: ");
		Integer[] arr2 = new Integer[]{8, 4, 11, 5, 2, 9, 16, 21, 31, 3, 95, 51, 99, 71, 64};
		for ( int i = 0; i < arr2.length; ++i ) 
			System.out.print(arr2[i] + " ");
		System.out.print("\n");
		BinarySearchTree<Integer> bst2 = BinaryTree.convertToSearchTree(bTree2, arr2);

		System.out.println("\nBinary Search Tree: \n" + bst2 + "--------------");		

	}

	public static void testConvertToAVLTree ( ) {

		System.out.println("\n____Testing convertToAVLTree()____\n");
		BinarySearchTree<Integer> bst1 = new BinarySearchTree<Integer>();

		for ( int i = 0; i < 4; ++i ) {
			bst1.add(i+1);
		}

		System.out.println( "Binary Search Tree:\n" + bst1);

		BinarySearchTree.convertToAVLTree(bst1);

		System.out.println( "After converting to AVL Tree:\n" + bst1 + "------------");


		BinarySearchTree<Integer> bst2 = new BinarySearchTree<Integer>();

		for ( int i = 0; i < 10; ++i ) {
			bst2.add((i+1)*3);
		}

		System.out.println( "Binary Search Tree:\n" + bst2);

		BinarySearchTree.convertToAVLTree(bst2);

		System.out.println( "After converting to AVL Tree:\n" + bst2 + "------------");

		BinarySearchTree<Integer> bst3 = new BinarySearchTree<Integer>();

		for ( int i = 10; i > 0; --i ) {
			bst3.add((i+1)*3);
		}

		System.out.println( "Binary Search Tree:\n" + bst3);

		BinarySearchTree.convertToAVLTree(bst3);

		System.out.println( "After converting to AVL Tree:\n" + bst3 + "------------");

		BinarySearchTree<Integer> bst4 = new BinarySearchTree<Integer>();
		bst4.add(10);
		bst4.add(7);
		bst4.add(5);
		bst4.add(1);
		bst4.add(15);
		bst4.add(25);
		bst4.add(31);

		System.out.println( "Binary Search Tree:\n" + bst4);

		BinarySearchTree.convertToAVLTree(bst4);

		System.out.println( "After converting to AVL Tree:\n" + bst4 + "------------");		
	}

	public static void testCustomSkipList() {

		System.out.println("\n____Testing CustomSkipList____\n");
		CustomSkipList<Integer> skip1 = new CustomSkipList<Integer>();

		System.out.println("Creating an empty skip list, and inserting elements one by one\n" + skip1 + "\n------------\n");

		skip1.add(7);
		System.out.println(skip1 + "\n------------\n");

		skip1.add(3);
		System.out.println(skip1 + "\n------------\n");
		
		skip1.add(15);
		System.out.println(skip1 + "\n------------\n");	

		skip1.add(11);
		System.out.println(skip1 + "\n------------\n");	

		skip1.add(81);
		System.out.println(skip1 + "\n------------\n");	

		skip1.add(54);
		System.out.println(skip1 + "\n------------\n");	

		skip1.add(37);
		System.out.println(skip1 + "\n------------\n");	

		skip1.add(19);
		System.out.println(skip1 + "\n------------\n");					

		skip1.add(74);
		System.out.println(skip1 + "\n------------\n");

		skip1.add(13);
		System.out.println(skip1 + "\n------------\n");				

		skip1.add(14);
		System.out.println(skip1 + "\n------------\n");

		skip1.add(63);
		System.out.println( "maximum level increased, tall item's are appended one level upper list.\n" + skip1 + "\n------------\n");

		skip1.add(71);
		System.out.println(skip1 + "\n------------\n");	

		skip1.add(68);
		System.out.println(skip1 + "\n------------\n");	

		skip1.add(82);
		System.out.println(skip1 + "\n------------\n");	

		skip1.add(1);
		System.out.println(skip1 + "\n------------\n");	

		skip1.add(4);
		System.out.println(skip1 + "\n------------\n");	

		skip1.add(8);
		System.out.println(skip1 + "\n------------\n");	

		skip1.add(12);
		System.out.println(skip1 + "\n------------\n");	

		skip1.add(99);
		System.out.println(skip1 + "\n------------\n");

		skip1.add(101);
		System.out.println(skip1 + "\n------------\n");	

		skip1.add(69);
		System.out.println( "maximum level increased, tall item's are appended one level upper list.\n" + skip1 + "\n------------\n");	

		skip1.add(58);
		System.out.println(skip1 + "\n------------\n");	

		skip1.add(16);
		System.out.println(skip1 + "\n------------\n");


		System.out.println("skip1.find(99) = " + skip1.find(99));
		System.out.println("skip1.find(11) = " + skip1.find(11));
		System.out.println("skip1.find(4) = " + skip1.find(4));
		System.out.println("(not exist)skip1.find(2) = " + skip1.find(2));
		System.out.println("skip1.find(15) = " + skip1.find(15));
		System.out.println("(not exist)skip1.find(24) = " + skip1.find(24));
		System.out.println("(not exist)skip1.find(66) = " + skip1.find(66));
	}
}