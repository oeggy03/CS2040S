import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

class MyOwnException extends Exception{

}
public class ExceptionDemo {
    public static void foo() throws MyOwnException {
        throw new MyOwnException();
    }

    public static void main(String[] args) {
        try {
            foo();
        } catch (MyOwnException e) {
            //do something
        }
    }
}
