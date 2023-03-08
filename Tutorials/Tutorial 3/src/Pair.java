public class D <T> {
    private T[] tArr;

    public D () {
        @SuppressWarnings()
        T[] tmp = (T[]) new Object[10]; //New to use temp variable and typecasting. Still work though without it
        this.tArr = tmp;
    }

    public T[] getArr() {
        return tArr;
    }
}