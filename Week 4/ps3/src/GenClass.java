public class GenClass<T> {
    private T x;

    public GenClass (T x){
        this.x = x;
    }

    public static void main(String[] args) {
        GenClass<String> g = new GenClass<String>("hello");
    }
}
