import java.util.Arrays;

public class iterativeMergeSort {
    int[] targetArr;
    int n;

    public iterativeMergeSort (int[] targetArr){
        this.targetArr = targetArr;
        this.n = targetArr.length;
    }

    private void sort() {
        // for each size of sub-array
        for (int subArrSize = 1; subArrSize < n; subArrSize *= 2) { //*= 2 because it is merge sort
            // for each sub-array of size 'size'
            for (int begin = 0; begin < n - subArrSize; begin += subArrSize * 2) {
                // merge the sub-array from 'start' to 'start + size - 1'
                // with the sub-array from 'start + size' to 'Math.min(start + size * 2 - 1, n - 1)'
                merge(begin, begin + subArrSize - 1, Math.min(begin + subArrSize * 2 - 1, n - 1));
            }
        }
    }

    private void merge(int begin, int mid, int end) {
        int[] tempArr = new int[end - begin + 1];

        int i = begin, j = mid + 1, k = 0;
        while (i <= mid && j <= end) {
            if (targetArr[i] <= targetArr[j]) {
                tempArr[k++] = targetArr[i++];
            } else {
                tempArr[k++] = targetArr[j++];
            }
        }

        // copy the remaining elements of left sub-array
        while (i <= mid) {
            tempArr[k++] = targetArr[i++];
        }

        // copy the remaining elements of right sub-array
        while (j <= end) {
            tempArr[k++] = targetArr[j++];
        }

        // copy the sorted temp array to the original array
        for (int p = 0; p < tempArr.length; p++) {
            targetArr[begin + p] = tempArr[p];
        }
    }

    public static void main(String[] args) {
        int[] input = { 9, 8, 7, 6, 5, 4, 3, 2, 1 };

        iterativeMergeSort testSort = new iterativeMergeSort(input);
        testSort.sort();

        System.out.println(Arrays.toString(testSort.targetArr));
    }


}
