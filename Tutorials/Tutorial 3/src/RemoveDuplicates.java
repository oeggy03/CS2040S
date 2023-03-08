import java.util.Arrays;
import java.util.HashSet;

public class RemoveDuplicates {
    public static int[] removeDuplicates(int[] A) {
        HashSet<Integer> set = new HashSet<>();
        for (int x : A) {
            set.add(x);
        }
        int[] B = new int[set.size()];
        int i = 0;
        for (int x : set) {
            B[i++] = x;
        }
        return B;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(removeDuplicates(new int[]{1, 77, 2, 5, 3, 4, 6, 5, 77, 78})));
    }
}
