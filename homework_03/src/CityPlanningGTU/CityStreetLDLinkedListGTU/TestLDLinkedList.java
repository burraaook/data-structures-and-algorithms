package CityStreetLDLinkedListGTU;
import CityBuildingGTU.*;
import java.util.*;

/**
 * Class for testing LDLinkedList class.
 * @author Burak Kocausta
 */ 
public class TestLDLinkedList {


	/**
	 * method tests LDLLinked class.
	 */ 
	public static void testLDLinkedList ( ) {
		System.out.println( "___TESTING LDLinkedList class___\n" );

		System.out.println( "Creating and adding elements." );
		LDLinkedList<String> list = new LDLinkedList<String>();
		list.add("elma");
		list.add("armut");
		list.add("kavun");
		list.add(2,"kahve");
		ListIterator<String> itr = list.listIterator();

		System.out.println( "size() returns = " + list.size());
		while ( itr.hasNext() ) {
			String tool = itr.next();
			System.out.println(tool);
		}
		System.out.println( "\nadd methods of LDLinkedList and hasNext(), next() methods of custom iterator are tested." );
		System.out.println( "Removing some elements with iterators remove() method and lists remove methods.\n" );
		list.remove(0);
		list.remove("kahve");
		itr = list.listIterator();
		itr.next();
		itr.remove();

		itr = list.listIterator();
		System.out.println( "size() returns = " + list.size());
		while ( itr.hasNext() ) {
			String tool = itr.next();
			System.out.println(tool);
		}

		System.out.println( "\nSome Elements removed successfully.\nAdding elements using other methods.\n" );

		itr = list.listIterator();
		itr.add("elma");
		itr.add("armut");
		list.addLast("muz");
		list.addFirst("elma2");
	
		System.out.print("\n");
		System.out.println( "size() returns = " + list.size());
		itr = list.listIterator();
		while ( itr.hasNext() ) {
			String tool = itr.next();
			System.out.println(tool);
		}
		System.out.println( "add method of custom iterator, addLast, addFirst methods of LDLinkedList are tested.\n" );
		System.out.println( "list will be printed backwards using previous, hasprevious." );

		itr = list.listIterator ( list.size() );
		System.out.println( "size() returns = " + list.size());
		while ( itr.hasPrevious() ) {
			String tool = itr.previous();
			System.out.println(tool);
		}
		System.out.println( "previous, hasprevious methods are tested.\n\nset methods will be used." );

		list.set(2, "armut2");
		itr = list.listIterator();
		itr.next();
		itr.set("karpuz");
		for( String obj : list ) 
			System.out.println ( obj );

		System.out.println( "set methods are tested and range based for loop worked fine.\n" );


		System.out.println( "list.getLast() = " + list.getLast() + "\nlist.getFirst() = " + list.getFirst() );
		System.out.println( "\nlist.get(2) = " + list.get(2) + "\nlist.contains(\"karpuz\") = " + list.contains("karpuz") + "\n" );


		Iterator<String> itr2 = list.iterator();
		while ( itr2.hasNext() ) {
			String tool = itr2.next();
			System.out.println(tool);
		}

		System.out.println( "\nCalling list.clear()" );
		list.clear();
		System.out.println( "list.size() = " + list.size() );

		System.out.println( "getters, iterator(), clear() methods are tested.\n" );

		System.out.println( "Testing with different class which is Integer class.\n" );

		LDLinkedList<Integer> list2 = new LDLinkedList<Integer>();

		ListIterator<Integer> itr3 = list2.listIterator();
		
		System.out.println( "\nAdd some elements using iterators." );

		itr3.add (Integer.valueOf(3));
		itr3.add (Integer.valueOf(8));
		itr3.add (Integer.valueOf(11));
		System.out.println( "size() returns = " + list2.size());
		for ( Integer obj : list2 )
			System.out.print(obj + " ");
		
		System.out.print("\n");

		System.out.println( "\nAdd more numbers, and delete some using different scenarios." );
		for ( int i = 0; i < 33; ++i )				
			itr3.add(Integer.valueOf(i*3));

		itr3 = list2.listIterator();
		System.out.println( "\nBefore removing: size() returns = " + list2.size());
		while (itr3.hasNext()) {
			Integer val = itr3.next();
			System.out.print( val + " " );
		}

		System.out.print("\n");

		itr3 = list2.listIterator(list2.size());
		itr3.previous();
		itr3.next();
		itr3.remove();

		itr3 = list2.listIterator();

		itr3.next();
		itr3.remove();

		itr3 = list2.listIterator(5);

		itr3.next();
		itr3.remove();

		itr3.previous();
		itr3.remove();


		itr3 = list2.listIterator();
		System.out.println( "\nAfter Removing: size() returns = " + list2.size());
		while (itr3.hasNext()) {
			Integer val = itr3.next();
			System.out.print( val + " " );
		}

		System.out.println( "\n\n--All functionalities of LDLinkedList class are tested.\n" );
	}	
}