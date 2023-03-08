import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ArrayMerger {
    public static int[] mergeArrays(int[] A, int[] B) {
        Set<Integer> set = new HashSet<>();
        for (int i : A) set.add(i);
        for (int i : B) set.add(i);
        int[] C = new int[set.size()];
        int i = 0;
        for (int n : set) C[i++] = n;
        return C;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(mergeArrays(new int[]{1, 2, 3, 4, 5}, new int[]{2, 3, 5, 6, 7})));
    }
}

