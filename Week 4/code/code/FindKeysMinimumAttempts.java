import java.util.Arrays;

public class FindKeysMinimumAttempts implements IFindKeys {

    @Override
    public int[] findKeys(int N, int K, ITreasureExtractor treasureExtractor) {
        int[] keys = new int[N];
        int start = 0;
        int end = (int) Math.pow(2, N) - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            int[] candidateKeys = toBinaryArray(mid, N);
            if (countOnes(candidateKeys) < K) {
                start = mid + 1;
            } else if (treasureExtractor.tryUnlockChest(candidateKeys)) {
                end = mid - 1;
                keys = candidateKeys;
            } else {
                start = mid + 1;
            }
        }
        return keys;
    }

    private int[] toBinaryArray(int num, int size) {
        int[] binary = new int[size];
        for (int i = 0; i < size; i++) {
            binary[size - i - 1] = num % 2;
            num /= 2;
        }
        return binary;
    }

    private int countOnes(int[] array) {
        return (int) Arrays.stream(array).filter(i -> i == 1).count();
    }
}
