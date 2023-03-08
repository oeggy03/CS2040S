import java.util.Arrays;

public class insertionSort {

    private int[] targetArr;
    private int arrLength;

    public insertionSort (int n, int[] array) {
        targetArr = new int[n];
        targetArr = array;
        arrLength = n;
    }

    //n is the length of the array.
    private void sortArray (int n) {
        if (n > 1) {
            // Recurse to sort first n-1 elements
            sortArray(n-1);

            // Insert last element (the one to sort) at its correct position in sorted array.
            int elementToSort = targetArr[n-1];
            int j = n-2; //element no. n-1

            // While loop to help place the target element in its correct place
            //Relies on the loop invariant that the first n-1 elements are already sorted.
            while (j >= 0 && targetArr[j] > elementToSort)
            {
                targetArr[j+1] = targetArr[j];
                j--;
            }
            targetArr[j+1] = elementToSort;
        }

    }

    public static void main(String[] args) {
        int[] inputArr = {13, 22, 3, 4, 1, 341, 324, 900};
        int n = inputArr.length;
        insertionSort testSort = new insertionSort(n, inputArr);
        testSort.sortArray(testSort.arrLength);
        System.out.println(Arrays.toString(testSort.targetArr));
    }
}
