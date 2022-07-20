package RecursionGTU;

/**
 * 
 * This class provides static recursive implementation for finding total number of integers between two border numbers included.
 * @version 1.0 24.03.2022
 * @author Burak Kocausta
 */
public class FindNumBetweensRecursive {

	/**
	 *
	 * Method finds total number of integers, which uses binary search, between two given borders. Array must be ordered.
	 * @param arr integer array which must be ordered.
	 * @param below indicates lower limit.
	 * @param above indicates upper limit.
	 * @return int the total number of integers between given numbers.
	 */ 
	public static int findNumBetweensRecursively ( int[] arr, int below, int above ) {

		if ( arr == null )
			return -1;

		if ( above < below )
			return findNumBetweensRecursively( arr, above, below, 0, arr.length - 1 );
		return findNumBetweensRecursively( arr, below, above, 0, arr.length - 1 );
	}

	/**
	 * 
	 * Helper method for public method.
	 * @param arr integer array which must be ordered.
	 * @param below indicates lower limit.
	 * @param above indicates upper limit.
	 * @param first indicates first index
	 * @param last indicates last index
	 */ 
	private static int findNumBetweensRecursively ( int[] arr, int below, int above, int first, int last ) {
		if ( first > last ) return 0;

		int retval = 0;
		int middle = ( first + last ) / 2;

		if ( arr[middle] <= above && arr[middle] >= below ) {
			retval = 1 + findNumBetweensRecursively( arr, below, above, first, middle - 1 ) 
			+ findNumBetweensRecursively( arr, below, above, middle + 1, last );			
		}
		else {
			if ( arr[middle] > above )	// go left
				retval = findNumBetweensRecursively( arr, below, above, first, middle - 1 );
			else 						// go right
				retval = findNumBetweensRecursively( arr, below, above, middle + 1, last ); 
		}

		return retval;
	}
}