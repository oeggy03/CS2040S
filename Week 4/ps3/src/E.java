public class E <T extends Comparable<T>>{
    private T[] field;

    public E() {
        //Since T will always extend Comparable, we are very sure that we can use Comparable here
        //Will throw rawtype warning
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Comparable[10];
        this.field = temp;
    }


}
