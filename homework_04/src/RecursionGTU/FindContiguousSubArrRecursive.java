package RecursionGTU;

/**
 * 
 * This class provides static recursive implementation for finding contiguous sub arrays of given sum inside another greater array.
 * @version 1.0 26.03.2022
 * @author Burak Kocausta
 */ 
public class FindContiguousSubArrRecursive {

	/**
	 * 
	 * Method prints contiguous sub arrays of given sum inside given array.
	 * @param arr integer array to search.
	 * @param sum integer sum value.
	 */ 
	public static void findContSubArrRecursively ( int[] arr, int sum ) {

		if ( arr == null )
			System.out.println("Array is null!");

		else if ( arr.length == 0 )
			System.out.println( "Array is empty!" );

		else 
			findContSubArrRecursively( arr, sum, 0, 0 );
	}

	/**
	 * 
	 * Helper method for recursive searching.
	 * @param arr integer array to search.
	 * @param sum integer sum value.
	 * @param startPos starting position index
	 * @param endPos ending position index
	 */ 
	private static void findContSubArrRecursively ( int[] arr, int sum, int startPos, int endPos ) {

		if ( startPos >= arr.length ){
			System.out.println("----------------");
			return;
		}

		if ( endPos >= arr.length ) {
			++startPos;
			findContSubArrRecursively ( arr, sum, startPos, startPos );
			return;
		}

		else if ( sum == findSumRecursively( arr, startPos, endPos ) ) {
			System.out.print("[");
			printSubArrRecursively( arr, startPos, endPos );
		}

		findContSubArrRecursively( arr, sum, startPos, endPos + 1 );
	}

	/**
	 * 
	 * Helper private method to calculate sum between given index in an array recursively.
	 * @param arr integer array to search.
	 * @param startPos starting position index
	 * @param endPos ending position index
	 * @return int returns sum
	 */ 
	private static int findSumRecursively ( int[] arr, int startPos, int endPos ) {
		if ( endPos < startPos ) 
			return 0;
		return arr[endPos] + findSumRecursively( arr, startPos, endPos - 1);
	}

	/**
	 * 
	 * Helper private method to print elements in given positions.
	 * @param arr integer array to search.
	 * @param startPos starting position index
	 * @param endPos ending position index
	 */ 
	private static void printSubArrRecursively ( int[] arr, int startPos, int endPos ) {
		if ( startPos > endPos ) {
			System.out.print("]\n");
			return;
		}
		System.out.print( arr[startPos] );
		if(startPos != endPos ) System.out.print(", ");
		printSubArrRecursively( arr, startPos + 1, endPos );
	}
}