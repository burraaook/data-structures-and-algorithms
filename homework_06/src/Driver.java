import HashTableGTU.*;
import BinarySearchTreeGTU.*;
import SortGTU.*;
import java.util.Random;
import java.util.ArrayList;

/**
 * 
 * Driver class for HashTableGTU and SortGTU.
 */ 
public class Driver {

	public static void main ( String args[] ) {

		testBSTHashTableChain();
		testHashTableHybrid();
		testMergeSort();
		testQuickSort();
		testNewSort();
		//	testBinarySearchTree();	
		//	performHashTables(100);
		//	performSortAlgorithms(1000);
	}

	/**
	 * 
	 * Tests BSTHashTableChain class and KWHashMap interface.
	 */ 	
	public static void testBSTHashTableChain ( ) {
		System.out.println("________Testing BSTHashTableChain________\n\n");

		System.out.println("Creating an empty BSTHashTableChain\n");
		KWHashMap<Integer, String> table1 = new BSTHashTableChain<Integer, String>();
		System.out.println(table1 + "--------------------------- size = " + table1.size() + "\n");

		System.out.println("table1.isEmpty() = " + table1.isEmpty() + "\n");

		System.out.println("put (6, burak)");
		table1.put(6, "burak");
		System.out.println(table1 + "--------------------------- size = " + table1.size() + "\n");

		System.out.println("put (126, james)");
		table1.put(126, "james");
		System.out.println(table1 + "--------------------------- size = " + table1.size() + "\n");

		System.out.println("put (11, elif)");
		table1.put(11, "elif");
		System.out.println(table1 + "--------------------------- size = " + table1.size() + "\n");

		System.out.println("put (16, claire)");
		table1.put(16, "claire");
		System.out.println(table1 + "--------------------------- size = " + table1.size() + "\n");

		System.out.println("put (421, josh)");
		table1.put(421, "josh");
		System.out.println(table1 + "--------------------------- size = " + table1.size() + "\n");

		System.out.println("put (6, john) (trying to modify key 6)");
		table1.put(6, "john");
		System.out.println(table1 + "--------------------------- size = " + table1.size() + "\n");

		System.out.println("put (421, cassie) (trying to modify key 421)");
		table1.put(421, "cassie");
		System.out.println(table1 + "--------------------------- size = " + table1.size() + "\n");

		System.out.println("after removing key 16");
		table1.remove(16);
		System.out.println(table1 + "--------------------------- size = " + table1.size() + "\n");	
		
		System.out.println("after removing key 11");
		table1.remove(11);
		System.out.println(table1 + "--------------------------- size = " + table1.size() + "\n");	
		
		System.out.println("after removing key 421");
		table1.remove(421);
		System.out.println(table1 + "--------------------------- size = " + table1.size() + "\n");

		System.out.println("after removing key 19(for testing error)");
		table1.remove(19);
		System.out.println(table1 + "--------------------------- size = " + table1.size() + "\n");	

		System.out.println("after removing key 126");
		table1.remove(126);
		System.out.println(table1 + "--------------------------- size = " + table1.size() + "\n");
		
		System.out.println("after removing key 6");
		table1.remove(6);
		System.out.println(table1 + "--------------------------- size = " + table1.size() + "\n");

		System.out.println("after removing key 15(for testing error)");
		table1.remove(15);
		System.out.println(table1 + "--------------------------- size = " + table1.size() + "\n");
		System.out.println("table1.isEmpty() = " + table1.isEmpty() + "\n");

		System.out.println("put each element again and add aditional elements.");	
		table1.put(82, "lain");			
		table1.put(6, "burak");
		table1.put(126, "james");
		table1.put(119, "john");		
		table1.put(11, "elif");
		table1.put(16, "claire");
		table1.put(421, "josh");
		table1.put(47, "osman");
		table1.put(731, "mike");
		table1.put(522, "ryan");
		table1.put(413, "michelle");
		table1.put(234, "ayse");
		table1.put(632, "ahmet");
		table1.put(13, "donald");
		table1.put(539, "will");			
		System.out.println(table1 + "--------------------------- size = " + table1.size() + "\n");

	
		table1.put(512, "maisie");
		table1.put(411, "hulya");
		table1.put(231, "chris");
		table1.put(852, "jada");
		table1.put(609, "emin");
		table1.put(382, "esra");
		table1.put(786, "kerem");		
		table1.put(742, "tony");
		table1.put(191, "shiv");
		table1.put(5, "suzan");
		table1.put(31, "owen");
		table1.put(75, "kim");
		table1.put(832, "ayca");
		table1.put(741, "bora");
		table1.put(235, "bob");
		table1.put(919, "caine");				
		table1.put(213, "sevgi");
		table1.put(561, "mert");
		table1.put(901, "roy");
		table1.put(1, "asli");
		System.out.println(table1 + "--------------------------- size = " + table1.size() + "\n");
		System.out.println("Rehashing happened, and size of the table is increased.\n");

		System.out.println("table1.get(75) = " + table1.get(75));
		System.out.println("table1.get(5) = " + table1.get(5));
		System.out.println("table1.get(16) = " + table1.get(16));
		System.out.println("table1.get(2)(does not exist) = " + table1.get(2));
		System.out.println("table1.get(3)(does not exist) = " + table1.get(3));	
		System.out.println("table1.get(11) = " + table1.get(11) + "\n");

		System.out.println("put (539, jack) (trying to modify key 539)");
		table1.put(539, "jack");
		System.out.println(table1 + "--------------------------- size = " + table1.size() + "\n");

		System.out.println("\n__Test Results for BSTHashTableChain__");
		System.out.println("1- put and remove with different scenarios are tested.");
		System.out.println("2- get, size and isEmpty tested.");
		System.out.println("3- rehashing tested.");
		System.out.println("4- toString and some error handlings are tested.");
		System.out.println("5- Polymorphism is tested.");
		System.out.println("6- Iterator's of BinarySearchTree is tested.\n");		
	}

	/**
	 * 
	 * Tests HashTableHybrid class and KWHashMap interface.
	 */ 
	public static void testHashTableHybrid ( ) {
		System.out.println("________Testing HashTableHybrid________\n\n");

		System.out.println("Creating an empty HashTableHybrid\n");
		KWHashMap<Integer, String> hybrid1 = new HashTableHybrid<Integer, String>();
		System.out.println(hybrid1 + "--------------------------- size = " + hybrid1.size() + "\n");

		System.out.println("hybrid1.isEmpty() = " + hybrid1.isEmpty() + "\n");

		System.out.println("put (6, burak)");
		hybrid1.put(6, "burak");
		System.out.println(hybrid1 + "--------------------------- size = " + hybrid1.size() + "\n");

		System.out.println("put (126, james)");
		hybrid1.put(126, "james");
		System.out.println(hybrid1 + "--------------------------- size = " + hybrid1.size() + "\n");

		System.out.println("put (11, elif)");
		hybrid1.put(11, "elif");
		System.out.println(hybrid1 + "--------------------------- size = " + hybrid1.size() + "\n");

		System.out.println("put (742, tony)");
		hybrid1.put(742, "tony");
		System.out.println(hybrid1 + "--------------------------- size = " + hybrid1.size() + "\n");

		System.out.println("after removing key 126");
		hybrid1.remove(126);
		System.out.println(hybrid1 + "--------------------------- size = " + hybrid1.size() + "\n");

		System.out.println("put (203, claire)");
		hybrid1.put(203, "claire");
		System.out.println(hybrid1 + "--------------------------- size = " + hybrid1.size() + "\n");

		System.out.println("put (126, james)");
		hybrid1.put(126, "james");
		System.out.println(hybrid1 + "--------------------------- size = " + hybrid1.size() + "\n");

		System.out.println("after removing key 742");
		hybrid1.remove(742);
		System.out.println(hybrid1 + "--------------------------- size = " + hybrid1.size() + "\n");

		System.out.println("put (421, josh)");
		hybrid1.put(421, "josh");
		System.out.println(hybrid1 + "--------------------------- size = " + hybrid1.size() + "\n");

		System.out.println("put (75, kim)");
		hybrid1.put(75, "kim");
		System.out.println(hybrid1 + "--------------------------- size = " + hybrid1.size() + "\n");

		System.out.println("put (235, bob)");
		hybrid1.put(235, "bob");
		System.out.println(hybrid1 + "--------------------------- size = " + hybrid1.size() + "\n");
		
		System.out.println("after removing key 235");
		hybrid1.remove(235);
		System.out.println(hybrid1 + "--------------------------- size = " + hybrid1.size() + "\n");		

		System.out.println("put (852, jada)");
		hybrid1.put(852, "jada");
		System.out.println(hybrid1 + "--------------------------- size = " + hybrid1.size() + "\n");

		System.out.println("put (5, susan)");
		hybrid1.put(5, "susan");
		System.out.println(hybrid1 + "--------------------------- size = " + hybrid1.size() + "\n");

		System.out.println("after removing key 852");
		hybrid1.remove(852);
		System.out.println(hybrid1 + "--------------------------- size = " + hybrid1.size() + "\n");	

		System.out.println("put (852, jada)");
		hybrid1.put(852, "jada");
		System.out.println(hybrid1 + "--------------------------- size = " + hybrid1.size() + "\n");

		System.out.println("put (539, will)");
		hybrid1.put(539, "will");	
		System.out.println(hybrid1 + "--------------------------- size = " + hybrid1.size() + "\n");
		
		System.out.println("put (235, bob)");
		hybrid1.put(235, "bob");
		System.out.println(hybrid1 + "--------------------------- size = " + hybrid1.size() + "\n");
						
		System.out.println("put (6, john) (trying to modify key 6)");
		hybrid1.put(6, "john");
		System.out.println(hybrid1 + "--------------------------- size = " + hybrid1.size() + "\n");

		System.out.println("put (421, cassie) (trying to modify key 421)");
		hybrid1.put(421, "cassie");
		System.out.println(hybrid1 + "--------------------------- size = " + hybrid1.size() + "\n");

		System.out.println("after removing key 6");
		hybrid1.remove(6);
		System.out.println(hybrid1 + "--------------------------- size = " + hybrid1.size() + "\n");	
		
		System.out.println("after removing key 11");
		hybrid1.remove(11);
		System.out.println(hybrid1 + "--------------------------- size = " + hybrid1.size() + "\n");	
		
		System.out.println("after removing key 421");
		hybrid1.remove(421);
		System.out.println(hybrid1 + "--------------------------- size = " + hybrid1.size() + "\n");

		System.out.println("after removing key 19(for testing error)");
		hybrid1.remove(19);
		System.out.println(hybrid1 + "--------------------------- size = " + hybrid1.size() + "\n");	
		
		System.out.println("hybrid1.isEmpty() = " + hybrid1.isEmpty() + "\n");

		System.out.println("put additional elements.");	
		hybrid1.put(82, "lain");			
		hybrid1.put(6, "burak");	
		hybrid1.put(126, "james");
		hybrid1.put(119, "john");		
		hybrid1.put(11, "elif");
		hybrid1.put(16, "claire");
		hybrid1.put(421, "josh");
		hybrid1.put(47, "osman");
		hybrid1.put(731, "mike");
		hybrid1.put(522, "ryan");
		hybrid1.put(413, "michelle");
		hybrid1.put(234, "ayse");
		hybrid1.put(632, "ahmet");
		hybrid1.put(13, "donald");
		
		System.out.println(hybrid1 + "--------------------------- size = " + hybrid1.size() + "\n");

		System.out.println("Put more elements\n");
	
		hybrid1.put(512, "maisie");
		hybrid1.put(411, "hulya");
		hybrid1.put(231, "chris");
		hybrid1.put(609, "emin");
		hybrid1.put(382, "esra");
		hybrid1.put(786, "kerem");		
		hybrid1.put(191, "shiv");
		hybrid1.put(31, "owen");
		hybrid1.put(832, "ayca");
		hybrid1.put(741, "bora");
		hybrid1.put(919, "caine");				
		hybrid1.put(213, "sevgi");
		hybrid1.put(561, "mert");
		hybrid1.put(901, "roy");
		hybrid1.put(1, "asli");
		System.out.println(hybrid1 + "--------------------------- size = " + hybrid1.size() + "\n");
		System.out.println("Rehashing happened, and size of the table is increased.\n");

		System.out.println("hybrid1.get(75) = " + hybrid1.get(75));
		System.out.println("hybrid1.get(5) = " + hybrid1.get(5));
		System.out.println("hybrid1.get(16) = " + hybrid1.get(16));
		System.out.println("hybrid1.get(2)(does not exist) = " + hybrid1.get(2));
		System.out.println("hybrid1.get(3)(does not exist) = " + hybrid1.get(3));	
		System.out.println("hybrid1.get(11) = " + hybrid1.get(11) + "\n");

		System.out.println("put (539, jack) (trying to modify key 539)");
		hybrid1.put(539, "jack");
		System.out.println(hybrid1 + "--------------------------- size = " + hybrid1.size() + "\n");

		System.out.println("after removing key 13");
		hybrid1.remove(13);
		System.out.println(hybrid1 + "--------------------------- size = " + hybrid1.size() + "\n");

		System.out.println("hybrid1.get(512) = " + hybrid1.get(512));
		System.out.println("hybrid1.get(539) = " + hybrid1.get(539));
		System.out.println("hybrid1.get(213) = " + hybrid1.get(213));
		System.out.println("hybrid1.get(13)(does not exist) = " + hybrid1.get(13));	
		System.out.println("hybrid1.get(901) = " + hybrid1.get(901) + "\n");

		System.out.println("\n__Test Results for HashTableHybrid__");
		System.out.println("1- put and remove with different scenarios are tested.");
		System.out.println("2- get, size and isEmpty tested.");
		System.out.println("3- rehashing tested.");
		System.out.println("4- toString and some error handlings are tested.");
		System.out.println("5- Polymorphism is tested.");
	}

	/**
	 * 
	 * Tests MergeSort class.
	 */ 
	public static void testMergeSort ( ) {

		System.out.println("\n___Testing MergeSort___");

		System.out.println("\nTest for 50 sized array.(randomly generated)\n");
		Integer[] arr1 = new Integer[50];
		Random rand = new Random();
		for ( int i = 0; i < 50; ++i ) {
			arr1[i] = rand.nextInt(100) + 1;
		}

		System.out.println("Before Sorting");
		for ( int i = 0; i < arr1.length; ++i ) {
			System.out.print(arr1[i] + " ");
		}
		System.out.print("\n");

		MergeSort.sort(arr1);
		System.out.println("After Sorting");
		for ( int i = 0; i < arr1.length; ++i ) {
			System.out.print(arr1[i] + " ");
		}
		System.out.print("\n");

		System.out.println("\nTest for 25 sized array.(randomly generated)\n");
		Integer[] arr2 = new Integer[25];
		for ( int i = 0; i < 25; ++i ) {
			arr2[i] = rand.nextInt(100) + 1;
		}

		System.out.println("Before Sorting");
		for ( int i = 0; i < arr2.length; ++i ) {
			System.out.print(arr2[i] + " ");
		}
		System.out.print("\n");

		MergeSort.sort(arr2);
		System.out.println("After Sorting");
		for ( int i = 0; i < arr2.length; ++i ) {
			System.out.print(arr2[i] + " ");
		}
		System.out.print("\n");

		System.out.println("\nTest for 5 sized array.(randomly generated)\n");
		Integer[] arr3 = new Integer[5];
		for ( int i = 0; i < 5; ++i ) {
			arr3[i] = rand.nextInt(100) + 1;
		}

		System.out.println("Before Sorting");
		for ( int i = 0; i < arr3.length; ++i ) {
			System.out.print(arr3[i] + " ");
		}
		System.out.print("\n");

		MergeSort.sort(arr3);
		System.out.println("After Sorting");
		for ( int i = 0; i < arr3.length; ++i ) {
			System.out.print(arr3[i] + " ");
		}
		System.out.print("\n");

		System.out.println("\nTest for 100 sized array.(randomly generated)\n");
		Integer[] arr4 = new Integer[100];
		for ( int i = 0; i < 100; ++i ) {
			arr4[i] = rand.nextInt(100) + 1;
		}

		System.out.println("Before Sorting");
		for ( int i = 0; i < arr4.length; ++i ) {
			System.out.print(arr4[i] + " ");
		}
		System.out.print("\n");

		MergeSort.sort(arr4);
		System.out.println("After Sorting");
		for ( int i = 0; i < arr4.length; ++i ) {
			System.out.print(arr4[i] + " ");
		}
		System.out.print("\n");
		System.out.println("\nTest for 1 sized array.\n");
		Integer[] arr5 = new Integer[1];
		arr5[0] = 3;

		System.out.println("Before Sorting");
		for ( int i = 0; i < arr5.length; ++i ) {
			System.out.print(arr5[i] + " ");
		}
		System.out.print("\n");

		MergeSort.sort(arr5);

		System.out.println("After Sorting");
		for ( int i = 0; i < arr5.length; ++i ) {
			System.out.print(arr5[i] + " ");
		}
		System.out.print("\n");

		System.out.println("\nTest for sorted array.\n");
		Integer[] arr6 = new Integer[13];
		for ( int i = 0; i < 13; ++i )
			arr6[i] = i + 1;
		
		System.out.println("Before Sorting");
		for ( int i = 0; i < arr6.length; ++i ) {
			System.out.print(arr6[i] + " ");
		}
		System.out.print("\n");

		MergeSort.sort(arr6);

		System.out.println("After Sorting");
		for ( int i = 0; i < arr6.length; ++i ) {
			System.out.print(arr6[i] + " ");
		}
		System.out.print("\n");		

		System.out.println("\nTest for inversely sorted array.\n");
		Integer[] arr7 = new Integer[10];
		for ( int i = 0; i < arr7.length; ++i )
			arr7[i] = arr7.length - i;
		
		System.out.println("Before Sorting");
		for ( int i = 0; i < arr7.length; ++i ) {
			System.out.print(arr7[i] + " ");
		}
		System.out.print("\n");

		MergeSort.sort(arr7);

		System.out.println("After Sorting");
		for ( int i = 0; i < arr7.length; ++i ) {
			System.out.print(arr7[i] + " ");
		}
		System.out.print("\n");		

		System.out.println("\nTest for an array which has same elements.");
		Integer[] arr8 = new Integer[10];
		for ( int i = 0; i < 10; i++ )
			arr8[i] = 5;
		
		System.out.println("Before Sorting");
		for ( int i = 0; i < arr8.length; ++i ) {
			System.out.print(arr8[i] + " ");
		}
		System.out.print("\n");

		MergeSort.sort(arr8);

		System.out.println("After Sorting");
		for ( int i = 0; i < arr8.length; ++i ) {
			System.out.print(arr8[i] + " ");
		}
		System.out.print("\n");			
	}

	/**
	 * 
	 * Tests QuickSort class.
	 */ 
	public static void testQuickSort ( ) {
		System.out.println("\n___Testing QuickSort___");

		System.out.println("\nTest for 50 sized array.(randomly generated)\n");
		Integer[] arr1 = new Integer[50];
		Random rand = new Random();
		for ( int i = 0; i < 50; ++i ) {
			arr1[i] = rand.nextInt(100) + 1;
		}


		System.out.println("Before Sorting");
		for ( int i = 0; i < arr1.length; ++i ) {
			System.out.print(arr1[i] + " ");
		}
		System.out.print("\n");

		QuickSort.sort(arr1);
		System.out.println("After Sorting");
		for ( int i = 0; i < arr1.length; ++i ) {
			System.out.print(arr1[i] + " ");
		}
		System.out.print("\n");

		System.out.println("\nTest for 25 sized arrays(randomly generated).\n");
		Integer[] arr2 = new Integer[25];
		for ( int i = 0; i < 25; ++i ) {
			arr2[i] = rand.nextInt(100) + 1;
		}

		System.out.println("Before Sorting");
		for ( int i = 0; i < arr2.length; ++i ) {
			System.out.print(arr2[i] + " ");
		}
		System.out.print("\n");

		QuickSort.sort(arr2);
		System.out.println("After Sorting");
		for ( int i = 0; i < arr2.length; ++i ) {
			System.out.print(arr2[i] + " ");
		}
		System.out.print("\n");

		System.out.println("\nTest for 5 sized array.(randomly generated)\n");
		Integer[] arr3 = new Integer[5];
		for ( int i = 0; i < 5; ++i ) {
			arr3[i] = rand.nextInt(100) + 1;
		}

		System.out.println("Before Sorting");
		for ( int i = 0; i < arr3.length; ++i ) {
			System.out.print(arr3[i] + " ");
		}
		System.out.print("\n");

		QuickSort.sort(arr3);
		System.out.println("After Sorting");
		for ( int i = 0; i < arr3.length; ++i ) {
			System.out.print(arr3[i] + " ");
		}
		System.out.print("\n");

		System.out.println("\nTest for 100 sized array.(randomly generated)\n");
		Integer[] arr4 = new Integer[100];
		for ( int i = 0; i < 100; ++i ) {
			arr4[i] = rand.nextInt(100) + 1;
		}

		System.out.println("Before Sorting");
		for ( int i = 0; i < arr4.length; ++i ) {
			System.out.print(arr4[i] + " ");
		}
		System.out.print("\n");

		QuickSort.sort(arr4);
		System.out.println("After Sorting");
		for ( int i = 0; i < arr4.length; ++i ) {
			System.out.print(arr4[i] + " ");
		}
		System.out.print("\n");

		System.out.println("\nTest for 1 sized array.\n");
		Integer[] arr5 = new Integer[1];
		arr5[0] = 3;

		System.out.println("Before Sorting");
		for ( int i = 0; i < arr5.length; ++i ) {
			System.out.print(arr5[i] + " ");
		}
		System.out.print("\n");

		QuickSort.sort(arr5);

		System.out.println("After Sorting");
		for ( int i = 0; i < arr5.length; ++i ) {
			System.out.print(arr5[i] + " ");
		}
		System.out.print("\n");

		System.out.println("\nTest for sorted array.\n");
		Integer[] arr6 = new Integer[13];
		for ( int i = 0; i < 13; ++i )
			arr6[i] = i + 1;
		
		System.out.println("Before Sorting");
		for ( int i = 0; i < arr6.length; ++i ) {
			System.out.print(arr6[i] + " ");
		}
		System.out.print("\n");

		QuickSort.sort(arr6);

		System.out.println("After Sorting");
		for ( int i = 0; i < arr6.length; ++i ) {
			System.out.print(arr6[i] + " ");
		}
		System.out.print("\n");		

		System.out.println("\nTest for inversely sorted array.\n");
		Integer[] arr7 = new Integer[10];
		for ( int i = 0; i < arr7.length; ++i )
			arr7[i] = arr7.length - i;
		
		System.out.println("Before Sorting");
		for ( int i = 0; i < arr7.length; ++i ) {
			System.out.print(arr7[i] + " ");
		}
		System.out.print("\n");

		QuickSort.sort(arr7);

		System.out.println("After Sorting");
		for ( int i = 0; i < arr7.length; ++i ) {
			System.out.print(arr7[i] + " ");
		}
		System.out.print("\n");		

		System.out.println("\nTest for an array which has same elements.");
		Integer[] arr8 = new Integer[10];
		for ( int i = 0; i < 10; i++ )
			arr8[i] = 5;
		
		System.out.println("Before Sorting");
		for ( int i = 0; i < arr8.length; ++i ) {
			System.out.print(arr8[i] + " ");
		}
		System.out.print("\n");

		QuickSort.sort(arr8);

		System.out.println("After Sorting");
		for ( int i = 0; i < arr8.length; ++i ) {
			System.out.print(arr8[i] + " ");
		}
		System.out.print("\n");					
	}

	/**
	 * 
	 * Tests NewSort class.
	 */ 
	public static void testNewSort ( ) {

		System.out.println("\n___Testing NewSort___");

		System.out.println("\nTest for 50 sized array.(randomly generated)\n");
		Integer[] arr1 = new Integer[50];
		Random rand = new Random();
		for ( int i = 0; i < 50; ++i ) {
			arr1[i] = rand.nextInt(100) + 1;
		}


		System.out.println("Before Sorting");
		for ( int i = 0; i < arr1.length; ++i ) {
			System.out.print(arr1[i] + " ");
		}
		System.out.print("\n");

		NewSort.sort(arr1);
		System.out.println("After Sorting");
		for ( int i = 0; i < arr1.length; ++i ) {
			System.out.print(arr1[i] + " ");
		}
		System.out.print("\n");

		System.out.println("\nTest for 25 sized array.(randomly generated)\n");
		Integer[] arr2 = new Integer[25];
		for ( int i = 0; i < 25; ++i ) {
			arr2[i] = rand.nextInt(100) + 1;
		}

		System.out.println("Before Sorting");
		for ( int i = 0; i < arr2.length; ++i ) {
			System.out.print(arr2[i] + " ");
		}
		System.out.print("\n");

		NewSort.sort(arr2);
		System.out.println("After Sorting");
		for ( int i = 0; i < arr2.length; ++i ) {
			System.out.print(arr2[i] + " ");
		}
		System.out.print("\n");

		System.out.println("\nTest for 5 sized array.(randomly generated)\n");
		Integer[] arr3 = new Integer[5];
		for ( int i = 0; i < 5; ++i ) {
			arr3[i] = rand.nextInt(100) + 1;
		}

		System.out.println("Before Sorting");
		for ( int i = 0; i < arr3.length; ++i ) {
			System.out.print(arr3[i] + " ");
		}
		System.out.print("\n");

		NewSort.sort(arr3);
		System.out.println("After Sorting");
		for ( int i = 0; i < arr3.length; ++i ) {
			System.out.print(arr3[i] + " ");
		}
		System.out.print("\n");

		System.out.println("\nTest for 100 sized array.(randomly generated)\n");
		Integer[] arr4 = new Integer[100];
		for ( int i = 0; i < 100; ++i ) {
			arr4[i] = rand.nextInt(100) + 1;
		}

		System.out.println("Before Sorting");
		for ( int i = 0; i < arr4.length; ++i ) {
			System.out.print(arr4[i] + " ");
		}
		System.out.print("\n");

		NewSort.sort(arr4);
		System.out.println("After Sorting");
		for ( int i = 0; i < arr4.length; ++i ) {
			System.out.print(arr4[i] + " ");
		}
		System.out.print("\n");

		System.out.println("\nTest for 1 sized array.\n");
		Integer[] arr5 = new Integer[1];
		arr5[0] = 3;

		System.out.println("Before Sorting");
		for ( int i = 0; i < arr5.length; ++i ) {
			System.out.print(arr5[i] + " ");
		}
		System.out.print("\n");

		NewSort.sort(arr5);

		System.out.println("After Sorting");
		for ( int i = 0; i < arr5.length; ++i ) {
			System.out.print(arr5[i] + " ");
		}
		System.out.print("\n");

		System.out.println("\nTest for sorted array.\n");
		Integer[] arr6 = new Integer[13];
		for ( int i = 0; i < 13; ++i )
			arr6[i] = i + 1;
		
		System.out.println("Before Sorting");
		for ( int i = 0; i < arr6.length; ++i ) {
			System.out.print(arr6[i] + " ");
		}
		System.out.print("\n");

		NewSort.sort(arr6);

		System.out.println("After Sorting");
		for ( int i = 0; i < arr6.length; ++i ) {
			System.out.print(arr6[i] + " ");
		}
		System.out.print("\n");		

		System.out.println("\nTest for inversely sorted array.\n");
		Integer[] arr7 = new Integer[10];
		for ( int i = 0; i < arr7.length; ++i )
			arr7[i] = arr7.length - i;
		
		System.out.println("Before Sorting");
		for ( int i = 0; i < arr7.length; ++i ) {
			System.out.print(arr7[i] + " ");
		}
		System.out.print("\n");

		NewSort.sort(arr7);

		System.out.println("After Sorting");
		for ( int i = 0; i < arr7.length; ++i ) {
			System.out.print(arr7[i] + " ");
		}
		System.out.print("\n");		

		System.out.println("\nTest for an array which has same elements.");
		Integer[] arr8 = new Integer[10];
		for ( int i = 0; i < 10; i++ )
			arr8[i] = 5;
		
		System.out.println("Before Sorting");
		for ( int i = 0; i < arr8.length; ++i ) {
			System.out.print(arr8[i] + " ");
		}
		System.out.print("\n");

		NewSort.sort(arr8);

		System.out.println("After Sorting");
		for ( int i = 0; i < arr8.length; ++i ) {
			System.out.print(arr8[i] + " ");
		}
		System.out.print("\n");			
	}

	/**
	 * 
	 * Performs hash table test given times, and prints results.
	 * @param num number of the test.
	 */ 
	public static void performHashTables ( long num ) {
		System.out.println("___HashTables Performance Test___\n");
		System.out.print(num + " tests for 100 1000 10000 random datasets(same for each).\nProcessing");
		ArrayList<Long> small = new ArrayList<Long>();
		ArrayList<Long> medium = new ArrayList<Long>();
		ArrayList<Long> large = new ArrayList<Long>();

		for(int i = 0; i < 10; ++i) {
			small.add(Long.valueOf(0));
			medium.add(Long.valueOf(0));
			large.add(Long.valueOf(0));
		}
		ArrayList<Long> dummy;				
		for ( long i = 0; i < num; ++i ) {
			dummy = performanceTestHashTables(100);
			for ( int j = 0; j < 10; ++j )
				small.set(j, small.get(j) + dummy.get(j));
			dummy = performanceTestHashTables(1000);
			for ( int j = 0; j < 10; ++j )
				medium.set(j, medium.get(j) + dummy.get(j));
			dummy = performanceTestHashTables(10000);
			for ( int j = 0; j < 10; ++j )
				large.set(j, large.get(j) + dummy.get(j));
			System.out.print(".");				
		}
		
		System.out.println( "\nHashTableHybrid (size = 100)\nput() = " + small.get(0) / num
							+ "\nget() = " + small.get(1) / num + "\nremove() = "
							+ small.get(2) / num + "\nsize() = " + small.get(3) / num
							+ "\nisEmpty() = " + small.get(4) / num + "\n" );

		System.out.println( "BSTHashTableChain (size = 100)\nput() = " + small.get(5) / num
							+ "\nget() = " + small.get(6) / num + "\nremove() = " 
							+ small.get(7) / num + "\nsize() = " + small.get(8) / num
							+ "\nisEmpty() = " + small.get(9) / num + "\n" );

		System.out.println( "HashTableHybrid (size = 1000)\nput() = " + medium.get(0) / num
							+ "\nget() = " + medium.get(1) / num + "\nremove() = "
							+ medium.get(2) / num + "\nsize() = " + medium.get(3) / num
							+ "\nisEmpty() = " + medium.get(4) / num + "\n" );

		System.out.println( "BSTHashTableChain (size = 1000)\nput() = " + medium.get(5) / num
							+ "\nget() = " + medium.get(6) / num + "\nremove() = " 
							+ medium.get(7) / num + "\nsize() = " + medium.get(8) / num
							+ "\nisEmpty() = " + medium.get(9) / num + "\n" );

		System.out.println( "HashTableHybrid (size = 10000)\nput() = " + large.get(0) / num
							+ "\nget() = " + large.get(1) / num + "\nremove() = "
							+ large.get(2) / num + "\nsize() = " + large.get(3) / num
							+ "\nisEmpty() = " + large.get(4) / num + "\n" );

		System.out.println( "BSTHashTableChain (size = 10000)\nput() = " + large.get(5) / num
							+ "\nget() = " + large.get(6) / num + "\nremove() = " 
							+ large.get(7) / num + "\nsize() = " + large.get(8) / num
							+ "\nisEmpty() = " + large.get(9) / num + "\n" );

	}

	/**
	 * 
	 * Performance test for HashTables.
	 * @param size size of datasets.
	 * @return times for each class.
	 */ 
	public static ArrayList<Long> performanceTestHashTables ( int size ) {

		ArrayList<Long> calculations = new ArrayList<Long>();
		Random rand = new Random();
		int max = 32000;

		int[] keys = new int[size];

		int[] values = new int[size];

		int[] randNums = new int[size];

		for ( int i = 0; i < size; ++i ) {
			keys[i] = rand.nextInt(max) + 1;
			values[i] = rand.nextInt(max) + 1;
			randNums[i] = rand.nextInt(max) + 1;			
		}

		long[] hybridTimes = new long[5];
		long[] bstchainTimes = new long[5];

		long startTime, endTime;

		KWHashMap<Integer, Integer> hybrid1 = new HashTableHybrid<Integer, Integer>();
		startTime = System.nanoTime();
		for ( int i = 0; i < size; ++i )
			hybrid1.put(keys[i], values[i]);
		endTime = System.nanoTime();
		hybridTimes[0] = endTime - startTime;

		startTime = System.nanoTime();
		for ( int i = 0; i < size; ++i )
			hybrid1.get(randNums[i]);	
		endTime = System.nanoTime();
		hybridTimes[1] = endTime - startTime;

		startTime = System.nanoTime();
		for ( int i = 0; i < size; ++i )
			hybrid1.remove(randNums[i]);
		endTime = System.nanoTime();
		hybridTimes[2] = endTime - startTime;

		startTime = System.nanoTime();
		for ( int i = 0; i < size; ++i )
			hybrid1.size();
		endTime = System.nanoTime();
		hybridTimes[3] = endTime - startTime;

		startTime = System.nanoTime();
		for ( int i = 0; i < size; ++i )
			hybrid1.isEmpty();
		endTime = System.nanoTime();
		hybridTimes[4] = endTime - startTime;				
		
		for ( int i = 0; i < 5; ++i )
			calculations.add(hybridTimes[i] / size);
		
		KWHashMap<Integer, Integer> bstchain1 = new BSTHashTableChain<Integer, Integer>();
		startTime = System.nanoTime();
		for ( int i = 0; i < size; ++i )
			bstchain1.put(keys[i], values[i]);
		endTime = System.nanoTime();
		bstchainTimes[0] = endTime - startTime;

		startTime = System.nanoTime();
		for ( int i = 0; i < size; ++i )
			bstchain1.get(randNums[i]);	
		endTime = System.nanoTime();
		bstchainTimes[1] = endTime - startTime;

		startTime = System.nanoTime();
		for ( int i = 0; i < size; ++i )
			bstchain1.remove(randNums[i]);
		endTime = System.nanoTime();
		bstchainTimes[2] = endTime - startTime;

		startTime = System.nanoTime();
		for ( int i = 0; i < size; ++i )
			bstchain1.size();
		endTime = System.nanoTime();
		bstchainTimes[3] = endTime - startTime;

		startTime = System.nanoTime();
		for ( int i = 0; i < size; ++i )
			bstchain1.isEmpty();
		endTime = System.nanoTime();
		bstchainTimes[4] = endTime - startTime;				
		
		for ( int i = 0; i < 5; ++i )
			calculations.add(bstchainTimes[i] / size);
		return calculations;															
	}

	/**
	 * 
	 * Performs tests for sort algorithms in given times, and prints results.
	 * @param num number of the test.
	 */ 
	public static void performSortAlgorithms ( long num ) {
		System.out.println("___Sort Algorithms Performance Test___\n");
		System.out.print(num + " tests for 100 1000 10000 random datasets(same for each).\nProcessing");

		ArrayList<Long> small = new ArrayList<Long>();
		ArrayList<Long> medium = new ArrayList<Long>();
		ArrayList<Long> large = new ArrayList<Long>();

		for(int i = 0; i < 3; ++i) {
			small.add(Long.valueOf(0));
			medium.add(Long.valueOf(0));
			large.add(Long.valueOf(0));
		}

		ArrayList<Long> dummy;				
		for ( long i = 0; i < num; ++i ) {
			dummy = performanceTestSortAlgorithms(100);
			for ( int j = 0; j < 3; ++j )
				small.set(j, small.get(j) + dummy.get(j));
			dummy = performanceTestSortAlgorithms(1000);
			for ( int j = 0; j < 3; ++j )
				medium.set(j, medium.get(j) + dummy.get(j));
			dummy = performanceTestSortAlgorithms(10000);
			for ( int j = 0; j < 3; ++j )
				large.set(j, large.get(j) + dummy.get(j));
			System.out.print(".");				
		}

		System.out.println ( "\nsize = 100\nMerge Sort = " + (small.get(0) / num)
		 					 + "\nQuick Sort = " + (small.get(1) / num) + "\nNew Sort = " + (small.get(2) / num) + "\n" );

		System.out.println ( "\nsize = 1000\nMerge Sort = " + (medium.get(0) / num)
		 					 + "\nQuick Sort = " + (medium.get(1) / num) + "\nNew Sort = " + (medium.get(2) / num) + "\n" );

		System.out.println ( "\nsize = 10000\nMerge Sort = " + (large.get(0) / num)
		 					 + "\nQuick Sort = " + (large.get(1) / num) + "\nNew Sort = " + (large.get(2) / num) + "\n" );		 					 		
	}

	/**
	 * 
	 * Performance test for sort algorithms.
	 * @param size size of datasets.
	 * @return times for each class.
	 */ 
	public static ArrayList<Long> performanceTestSortAlgorithms ( int size ) {
		Integer[] arr1 = new Integer[size];
		Integer[] arr2 = new Integer[size];
		Integer[] arr3 = new Integer[size];

		Random rand = new Random();

		ArrayList<Long> calculations = new ArrayList<Long>();
		for ( int i = 0; i < size; ++i ) {
			arr1[i] = rand.nextInt();
		}
		for ( int i = 0; i < size; ++i ) {
			arr2[i] = arr1[i];
		}
		for ( int i = 0; i < size; ++i ) {
			arr3[i] = arr1[i];
		}

		long startTime, endTime;

		startTime = System.nanoTime();
		MergeSort.sort(arr1);
		endTime = System.nanoTime();
		calculations.add(endTime - startTime);

		startTime = System.nanoTime();
		QuickSort.sort(arr2);
		endTime = System.nanoTime();
		calculations.add(endTime - startTime);

		startTime = System.nanoTime();
		NewSort.sort(arr3);
		endTime = System.nanoTime();
		calculations.add(endTime - startTime);

		return calculations;				
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