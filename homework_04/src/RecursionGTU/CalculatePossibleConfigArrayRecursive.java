package RecursionGTU;

/**
 * 
 * This class provides a static recursive implementation for printing possible configuration of an array.
 * @author Burak Kocausta
 * @version 1.0 29.03.2022
 */ 
public class CalculatePossibleConfigArrayRecursive {

	/**
	 * 
	 * Method prints different configurations of given array.
	 * @param arr an integer array.
	 */ 
	public static void calcPossibleConfigArrRecursively ( int[] arr ) {
		if ( arr == null ) {
			System.out.println( "Array is null!" );
			return;
		}

		if ( arr.length < 3 ) {
			System.out.println( "Array size must be minimum 3!" );
			return;
		}

		calcPossibleConfigArrRecursively ( arr, 0, arr.length , 3 );
	}


	/**
	 * 
	 * Helper private static method for using more parameters.
	 * @param arr an integer array.
	 * @param pos position
	 * @param bound rightmost bound, can be think as an inner size
	 * @param blockLen length of block
	 */ 
	private static void calcPossibleConfigArrRecursively ( int[] arr, int pos, int bound, int blockLen ) {
		if ( blockLen > arr.length )
			return;
		else if ( pos + blockLen > bound ) {
			if ( bound - blockLen <= 3) {
				fillArr(arr, 0, arr.length, 0);
				calcPossibleConfigArrRecursively( arr, 0, arr.length, blockLen + 1 );
			}
			else
				calcPossibleConfigArrRecursively( arr, 0, bound - blockLen - 1, blockLen );
		}

		else {
			fillArr(arr,pos,pos+blockLen,1);
			printArr(arr,0);
			if ( !(pos+1 + blockLen > bound) )
				fillArr(arr,pos,pos+blockLen,0);
			calcPossibleConfigArrRecursively( arr, pos+1, bound, blockLen );
		}
 	}

 	/**
 	 * 
 	 * Helper private method to fill array between given bounds.
	 * @param arr an integer array.
	 * @param pos position
	 * @param bound rightmost bound, can be think as an inner size
	 * @param val value to be filled
 	 */  
	private static void fillArr( int[] arr, int pos, int bound, int val ) {
		if ( pos >= bound )
			return;

		arr[pos] = val;

		fillArr(arr, pos + 1, bound, val);
	}

	/**
	 * 
	 * Helper private method to print the array.
	 * @param arr an integer array.
	 * @param pos position
	 */ 
	private static void printArr( int[] arr, int pos ) {
		if ( pos >= arr.length ) {
			System.out.print("\n\n");
			return;
		}

		if ( arr[pos] == 0 ) System.out.print("|_|");
		else if ( arr[pos] == 1 ) System.out.print("|*|");

		printArr( arr, pos + 1 );
	}

}