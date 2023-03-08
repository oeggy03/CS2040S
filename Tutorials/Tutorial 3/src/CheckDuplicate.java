import java.util.HashSet;

public class CheckDuplicate {
    public static boolean hasDuplicate(int[] array) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            if (!set.add(array[i])) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(hasDuplicate(new int[]{1, 2, 3, 4}));
    }
}

