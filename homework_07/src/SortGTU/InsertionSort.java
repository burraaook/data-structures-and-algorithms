package SortGTU;

/**
 * 
 * Insertion Sort implementation.
 * @version 1.0 25.04.2022 
 */ 
public class InsertionSort {

    /**
     * Implements the insertion sort.
     * @param arr a comparable array.
     * @param <E> type of array which is comparable
     */ 
    public static <E extends Comparable<E>> void sort ( E[] arr ) {
        for (int nextPos = 1; nextPos < arr.length; nextPos++) {
            // Invariant: arr[ 0 ... nextPos - 1] is ordered
            insert(arr, nextPos);
        }
    }
    
    /**
     * Private helper for insertion sort algorithm.
     * @param arr a comparable array.
     * @param nextPos position of the target(will be inserted) element.
     * @param <E> type of array which is comparable
     */ 
    private static <E extends Comparable<E>> void insert ( E[] arr, int nextPos ) {
        E nextVal = arr[nextPos]; 
        
        // iterate till nextPos reaches the beginning of the array.
        while (nextPos > 0 && nextVal.compareTo(arr[nextPos - 1]) < 0) {
            arr[nextPos] = arr[nextPos - 1]; 
            nextPos--;
        }
        arr[nextPos] = nextVal;
    }
}