import java.util.Arrays;

class ArrayMergerNoHash {
    public static int[] mergeArraysNoHash(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        int i = 0, j = 0, k = 0;
        int[] C = new int[A.length + B.length];
        while (i < A.length && j < B.length) {
            if (A[i] < B[j]) {
                C[k++] = A[i++];
            } else if (B[j] < A[i]) {
                C[k++] = B[j++];
            } else {
                C[k++] = A[i++];
                j++;
            }
        }
        while (i < A.length) {
            C[k++] = A[i++];
        }
        while (j < B.length) {
            C[k++] = B[j++];
        }
        return Arrays.copyOf(C, k);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(mergeArraysNoHash(new int[]{1, 2, 3, 4, 5}, new int[]{2, 3, 5, 6, 7})));
    }
}