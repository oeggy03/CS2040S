import java.util.Arrays;

public class MakeTarget {
    public static int[] twoSum(int[] A, int target) {
        Arrays.sort(A);
        System.out.println(Arrays.toString(A));
        int start = 0, end = A.length - 1;
        int[] result = new int[2];

        while (start < end) {
            int sum = A[start] + A[end];
            if (sum == target) {
                result[0] = A[start];
                result[1] = A[end];
                return result;
            } else if (sum < target) {
                start++;
            } else {
                end--;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] A = {8, 1, 2, 3, 4, 5, 6, 7};
        int target = 9;

        System.out.println(Arrays.toString(twoSum(A, target)));
    }
}

