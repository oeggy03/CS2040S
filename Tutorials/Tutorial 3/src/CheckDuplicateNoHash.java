import java.util.Arrays;

public class CheckDuplicateNoHash {
    public static boolean hasDuplicates(int[] A) {
        Arrays.sort(A); // sort the array
        for (int i = 1; i < A.length; i++) {
            if (A[i] == A[i - 1]) { // check for duplicates
                return true;
            }
        }
        return false;
    }


}
