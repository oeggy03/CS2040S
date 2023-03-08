import java.util.Arrays;

public class D <T> {
    private T[] tArr;

    public D () {
        this.tArr = (T[]) new Object[10];
    }

    public T[] getArr() {
        return tArr;
    }

    public static void main(String[] args) {
        D<Integer> test = new D<Integer>();
        System.out.println(Arrays.toString(test.getArr()));
    }
}
