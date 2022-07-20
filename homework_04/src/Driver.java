import RecursionGTU.*;

/**
 * 
 * Driver class to test RecursionGTU package.
 * @author Burak Kocausta
 * @version 1.0
 */ 
public class Driver {

	public static void main ( String args[] ) {
		
		testQueryStringRecursive();
		testFindNumBetweensRecursive();
		testFindContiguousSubArrRecursive();
		testCalculatePossibleConfigArrayRecursive();
	}
	
	public static void testQueryStringRecursive( ) {

		System.out.println("\n\n___Testing QueryStringRecursive___");

		System.out.print( "\nmain string = appleelmaappleelmaee\n" + "query string = " + "ee\n" +
							"occurrence = 2\n"+ "returns = " );
		System.out.println( QueryStringRecursive.returnQueryStringIndex("appleelmaappleelmaee", "ee", 2) );

		System.out.print( "\nmain string = aaeexxyyaaoo\n" + "query string = aa\n" +
							"occurrence = 1\n"+ "returns = " );
		System.out.println( QueryStringRecursive.returnQueryStringIndex("aaeexxyyaaoo", "aa", 1) );

		System.out.print( "\nmain string = abc\n" + "query string = abc\n" +
							"occurrence = 1\n"+ "returns = " );
		System.out.println( QueryStringRecursive.returnQueryStringIndex("abc", "abc", 1) );		

		System.out.print( "\nmain string = nkaabfeageaabex\n" + "query string = " + "aab\n" +
							"occurrence = 2\n"+ "returns = " );
		System.out.println( QueryStringRecursive.returnQueryStringIndex("nkaabfeageaabex", "aab", 2) );

		System.out.print( "\nmain string = abcxyzneabxynekmxabnenkenemm\n" + "query string = " + "ne\n" +
							"occurrence = 3\n"+ "returns = " );
		System.out.println( QueryStringRecursive.returnQueryStringIndex("abcxyzneabxynekmxabnenkenemm", "ne", 3) );		

		System.out.print( "\nmain string = efaeeaffefa\n" + "query string = " + "xk\n" +
							"occurrence = 2\n"+ "returns = " );
		System.out.println( QueryStringRecursive.returnQueryStringIndex("efaeeaffefa", "xk" , 2) );

		System.out.print( "\nmain string = efaeeaffexk\n" + "query string = " + "xk\n" +
							"occurrence = 1\n"+ "returns = " );
		System.out.println( QueryStringRecursive.returnQueryStringIndex("efaeeaffexk", "xk", 1) );

		System.out.print( "\nmain string = xyz\n" + "query string = " + "apple\n" +
							"occurrence = 1\n"+ "returns = " );
		System.out.println( QueryStringRecursive.returnQueryStringIndex("xyz", "apple", 1) );

		System.out.print( "\nmain string = xxxxxx\n" + "query string = " + "xx\n" +
							"occurrence = 3\n"+ "returns = " );
		System.out.println( QueryStringRecursive.returnQueryStringIndex("xxxx", "xx", 3) );
	}

	public static void testFindNumBetweensRecursive ( ) {

		System.out.println("\n\n___Testing FindNumBetweensRecursive___");

		int[] arr1 = { 1,2,3,4,5,6,7,8,9,10 };

		System.out.print("arr1 = ");
		for ( int i = 0; i < arr1.length; ++i ) System.out.print( arr1[i] + " " );

		int retVal = FindNumBetweensRecursive.findNumBetweensRecursively( arr1, 2, 6 );
		System.out.print( "\n\nbetween = 2 and 6" + "\nreturned value = " + retVal + "\n");

	 	retVal = FindNumBetweensRecursive.findNumBetweensRecursively( arr1, 1, 1 );
		System.out.print( "\nbetween = 1 and 1" + "\nreturned value = " + retVal + "\n");

	 	retVal = FindNumBetweensRecursive.findNumBetweensRecursively( arr1, -2, 3 );
		System.out.print( "\nbetween = -2 and 3" + "\nreturned value = " + retVal + "\n");

	 	retVal = FindNumBetweensRecursive.findNumBetweensRecursively( arr1, 3, 15 );
		System.out.print( "\nbetween = 3 and 15" + "\nreturned value = " + retVal + "\n");				

	 	retVal = FindNumBetweensRecursive.findNumBetweensRecursively( arr1, 13, 15 );
		System.out.print( "\nbetween = 13 and 15" + "\nreturned value = " + retVal + "\n");	

	 	retVal = FindNumBetweensRecursive.findNumBetweensRecursively( arr1, 7, 10 );
		System.out.print( "\nbetween = 7 and 10" + "\nreturned value = " + retVal + "\n");

	 	retVal = FindNumBetweensRecursive.findNumBetweensRecursively( arr1, 9, 3 );
		System.out.print( "\nbetween = 9 and 3(algorithm handles if inputs are opposite)" + "\nreturned value = " + retVal + "\n");

	 	retVal = FindNumBetweensRecursive.findNumBetweensRecursively( null, 9, 3 );
		System.out.print( "\nif array is null" + "\nreturned value = " + retVal + "\n");		
		System.out.println("-----------------------------");

		int[] arr2 = { -51,-40,-31,-27,-23,-16,-10,-8,-5,-3,0,5,17,21,26,33,42 };
		System.out.print("arr2 = ");		
		for ( int i = 0; i < arr2.length; ++i ) System.out.print( arr2[i] + " " );		

	 	retVal = FindNumBetweensRecursive.findNumBetweensRecursively( arr2, -42, -30 );
		System.out.print( "\n\nbetween = -42 and -30" + "\nreturned value = " + retVal + "\n");

	 	retVal = FindNumBetweensRecursive.findNumBetweensRecursively( arr2, -10, 15 );
		System.out.print( "\nbetween = -10 and 15" + "\nreturned value = " + retVal + "\n");

	 	retVal = FindNumBetweensRecursive.findNumBetweensRecursively( arr2, 22, 25 );
		System.out.print( "\nbetween = 22 and 25" + "\nreturned value = " + retVal + "\n");

	 	retVal = FindNumBetweensRecursive.findNumBetweensRecursively( arr2, -22, -22 );
		System.out.print( "\nbetween = -22 and -22" + "\nreturned value = " + retVal + "\n");

	 	retVal = FindNumBetweensRecursive.findNumBetweensRecursively( arr2, -50, 30 );
		System.out.print( "\nbetween = -50 and 30" + "\nreturned value = " + retVal + "\n");		
	}

	public static void testFindContiguousSubArrRecursive ( ) {
		System.out.println("\n\n___Testing FindContiguousSubArrRecursive___");

		int[] arr1 = { 3,2,1,6,5,1 };
		System.out.print("\narr1 = ");
		for ( int i = 0; i < arr1.length; ++i ) System.out.print(arr1[i] + " ");
		System.out.println("\n\nsum = 6\nresult");
		FindContiguousSubArrRecursive.findContSubArrRecursively( arr1, 6 );

		System.out.println("\n\nsum = -5\nresult");
		FindContiguousSubArrRecursive.findContSubArrRecursively( arr1, -5 );

		System.out.println("\nsum = 5\nresult");
		FindContiguousSubArrRecursive.findContSubArrRecursively( arr1, 5 );

		System.out.println("\nsending null array\nresult");
		FindContiguousSubArrRecursive.findContSubArrRecursively( null, 5 );	

		int[] arr2 = { -7,10,3,0,8,-5,-4,7,5,-2,5,1,1,1,2 };
		System.out.print("\narr2 = ");
		for ( int i = 0; i < arr2.length; ++i ) System.out.print(arr2[i] + " ");
		System.out.println("\n\nsum = 3\nresult");
		FindContiguousSubArrRecursive.findContSubArrRecursively( arr2, 3 );	

		System.out.println("\nsum = 5\nresult");
		FindContiguousSubArrRecursive.findContSubArrRecursively( arr2, 5 );								
	}

	public static void testCalculatePossibleConfigArrayRecursive ( ) {
		System.out.println("\n\n___Testing CalculatePossibleConfigArrayRecursive___");
		for( int i = 3; i <= 9; ++i ) {
			System.out.print("\nFor array size " + i + " result is\n\n" );
			int[] arr = new int[i];
			CalculatePossibleConfigArrayRecursive.calcPossibleConfigArrRecursively(arr);
			System.out.print("-----------\n");
		}
	}
}