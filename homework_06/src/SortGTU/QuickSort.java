package SortGTU;
/**
 * 
 * Implementation of Quick Sort algorithm. It makes Insertion Sort for array sizes smaller than 10.
 * @version 1.0 25.04.2022
 */ 
public class QuickSort {

    /**
     * Quick Sort implementation.
     * @param arr a comparable array.
     * @param <E> type of array which is comparable
     */ 
    public static <E extends Comparable<E>> void sort ( E[] arr ) {
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     * Private recursive helper method for quick sort implementation.
     * @param arr a comparable array
     * @param first first index
     * @param last last index
     * @param <E> type of array which is comparable
     */ 
    private static <E extends Comparable<E>> void quickSort ( E[] arr, int first, int last ) {
        if ( first < last ) {        
            // make partition and find pivot.
            int pivot = partition(arr, first, last);
            
            // sort pivot's left side.
            quickSort(arr, first, pivot - 1);
            
            // sort pivot's right side.
            quickSort(arr, pivot + 1, last);
        }
    }

    /**
     * Private helper method for partition.
     * @param arr a comparable array
     * @param first first index
     * @param last last index
     * @param <E> type of array which is comparable
     * @return pivot index.
     */     
    private static <E extends Comparable<E>> int partition ( E[] arr, int first, int last ) {
        // sort the first, last and medium.
        sortFirstLastMiddle(arr, first, last);

        // make median pivot.
        swap(arr, first, (first + last) / 2);
        
        // select the first item as the pivot value.
        E pivotVal = arr[first];
        int up = first;
        int down = last;

        do {
            // Invariant : all items in arr[first . . . up - 1] <= pivotVal
            // Invariant : all items in arr[down + 1 . . . last] > pivotVal
            // go right till condition does not check.
            while ( (up < last) && (pivotVal.compareTo(arr[up]) >= 0) )
                up++; 
            
            // assert: up equals last or arr[up] > pivotVal.
            // go left till condition does not check.
            while ( pivotVal.compareTo(arr[down]) < 0 ) 
                down--;

            // assert: down equals first or arr[down] <= pivotVal.
            if ( up < down )
                swap(arr, up, down);
        } while ( up < down );
        
        // exchange pivotVal and arr[down].
        swap(arr, first, down);
        return down;
    }

    /**
     * Private recursive helper method for quick sort implementation.
     * @param arr a comparable array
     * @param first first index
     * @param last last index
     * @param <E> type of array which is comparable
     */ 
    private static <E extends Comparable<E>> void sortFirstLastMiddle ( E[] arr, int first, int last ) {
        int middle = (first + last) / 2;

        if (arr[middle].compareTo(arr[first]) < 0) 
            swap(arr, first, middle);
        if (arr[last].compareTo(arr[middle]) < 0)
            swap(arr, middle, last);
        if (arr[middle].compareTo(arr[first]) < 0) 
            swap(arr, first, middle);
        
        // assert: arr[first] <= arr[middle] <= arr[last].
    }

    /**
     * A simple swap operation.
     * @param arr a comparable array
     * @param index1 first index
     * @param index2 last index
     * @param <E> type of array which is comparable
     */ 
    private static <E extends Comparable<E>> void swap(E[] arr, int index1, int index2) {
        E temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }    
}