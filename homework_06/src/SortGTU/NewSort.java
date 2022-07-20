package SortGTU;

/**
 * 
 * A New Sort Algorithm implementation, which sorts the array from outward to inward.
 * @author Burak Kocausta 
 * @version 1.0 25.04.2022
 */ 
public class NewSort {

	/**
	 * A New Sort Algorithm, which sorts the array from outward to inward.
	 * @param arr a comparable array
	 * @param <E> type of array which is comparable
	 */ 
	public static <E extends Comparable<E>> void sort ( E[] arr ) {
		if ( arr.length <= 1 )
			return;
		new_sort( arr, 0, arr.length - 1 );
	}

	/**
	 * A private helper method for new sort.
	 * @param arr a comparable array
	 * @param head head index of array
	 * @param tail tail index of array
	 * @param <E> type of array which is comparable
	 */  
	private static <E extends Comparable<E>> void new_sort ( E[] arr, int head, int tail ) {	
		if ( head > tail )
			return;

		else {
			int[] minMax = new int[2];

			minMax = minMaxFinder(arr, head, tail);
			swap(arr, head, minMax[0]);
			if ( minMax[1] == head ) {
				// assert : maximum is at minimum's previous place. 
				swap(arr, tail, minMax[0] );
			}
			else
				swap(arr, tail, minMax[1]);

			// Invariant : arr[0, ..., head] and arr[tail, .., arr.length] are sorted. 
			new_sort(arr, head + 1, tail - 1);
		}
	}

	/**
	 * A private helper method for new sort.
	 * @param arr a comparable array
	 * @param head head index of array
	 * @param tail tail index of array
	 * @param <E> type of array which is comparable
	 * @return 2 sized integer array (min index, max index)
	 */  
	private static <E extends Comparable<E>> int[] minMaxFinder ( E[] arr, int head, int tail ) {
		if ( tail - head <= 1 ) {
			int[] minMax = new int[2];

			if ( arr[tail].compareTo(arr[head]) > 0 ) {
				minMax[0] = head;
				minMax[1] = tail;
				return minMax;
			}

			// assert : arr[head] >= arr[tail]
			minMax[1] = head;
			minMax[0] = tail;
			return minMax;
		}

		int[] minMax1 = new int[2];
		int[] minMax2 = new int[2];
		int medium = (head + tail) / 2;

		// go left and right
		minMax1 = minMaxFinder(arr, head, medium);
		minMax2 = minMaxFinder(arr, medium + 1, tail);

		// modify minMax1 only
		if ( arr[minMax1[0]].compareTo(arr[minMax2[0]]) > 0 )
			minMax1[0] = minMax2[0];

		if ( arr[minMax2[1]].compareTo(arr[minMax1[1]]) > 0 )
			minMax1[1] = minMax2[1];		

		return minMax1;
	}

    /**
     * A simple swap operation.
     * @param arr a comparable array
     * @param index1 first index
     * @param index2 last index
     * @param <E> type of array which is comparable
     */ 
    private static <E extends Comparable<E>> void swap ( E[] arr, int index1, int index2 ) {
        E temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }    
}