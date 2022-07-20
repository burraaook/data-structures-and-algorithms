package SortGTU;
/**
 * 
 * Merge Sort implementation. Insertion sort is made for smaller 10 sized arrays.
 * @version 1.0 25.04.2022
 */ 
public class MergeSort {

	/**
	 * A merge sort implementation.
	 * @param arr comparable array
	 * @param <E> type of array which is comparable
	 */
	@SuppressWarnings("unchecked")
	public static <E extends Comparable<E>> void sort ( E[] arr ) {
		// insertion sort is faster with small size elements.
		if ( arr.length < 10 ) {
			InsertionSort.sort(arr);
			return;
		}
		
		int half = arr.length / 2;

		E[] leftArr = (E[]) new Comparable[half];
		E[] rightArr = (E[]) new Comparable[arr.length - half];

		System.arraycopy(arr, 0, leftArr, 0, half);
		System.arraycopy(arr, half, rightArr, 0, arr.length - half);
		
		// sort left.
		sort(leftArr);

		// sort right
		sort(rightArr);

		// Merge the halves.
		merge(arr, leftArr, rightArr);
	}

	/**
	 * Merge's two array into one output array with ascending order.
	 * @param output output array
	 * @param left left array
	 * @param right right array
	 * @param <E> type of array which is comparable
	 */ 
	private static <E extends Comparable<E>> void merge ( E[] output, E[] left, E[] right ) {
		int leftIndex = 0; 
		int rightIndex = 0; 
		int outIndex = 0; 

		// iterates till one of them finishes.
		while ( leftIndex < left.length && rightIndex < right.length ) {
			if ( left[leftIndex].compareTo(right[rightIndex]) < 0 )
				output[outIndex++] = left[leftIndex++];		 
			else 
				output[outIndex++] = right[rightIndex++];
		}

		// assert: one of the arrays has more items to copy.
		while ( leftIndex < left.length ) 
			output[outIndex++] = left[leftIndex++];
		while ( rightIndex < right.length ) 
			output[outIndex++] = right[rightIndex++];	
	}
}