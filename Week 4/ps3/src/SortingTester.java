import java.io.FileWriter;
import java.util.Arrays;
import java.util.Random;

public class SortingTester {
    public static boolean checkSort(ISort sorter, int size) {
        KeyValuePair[] testArr = new KeyValuePair[size];
        Random ranGen = new Random();

        for (int i = 0; i < size; i ++) {
            int key = ranGen.nextInt(100);
            int value = ranGen.nextInt(100);

            testArr[i] = new KeyValuePair(key,value);
        }

        sorter.sort(testArr);

        for (int i = 0; i < size - 1; i ++) {
            if (testArr[i].compareTo(testArr[i + 1]) == 1) {
                return false;
            }
        }

        return true;
    }

    public static boolean isStable(ISort sorter, int size) {
        KeyValuePair[] testArr = new KeyValuePair[size];
        Random ranGen = new Random();
        int value = ranGen.nextInt(100);

        int divisor = size;
        for (int i = 1; i < size; i ++ ) {
            if (size % i != 0) {
                divisor = i;
                break;
            }
        }

        //We need the divisor to not be a multiple of size, so that selection sort's swap will mess up the order
        for (int i = 0; i < size; i ++) {
            if (i > size / divisor) {
                testArr[i] = new KeyValuePair(1,value + i);
            } else {
                testArr[i] = new KeyValuePair(2,value + i);
            }
        }

        sorter.sort(testArr);

        for (int i = 0; i < size - 1; i ++) {
            if (testArr[i].compareTo(testArr[i + 1]) == 1
                || (testArr[i].compareTo(testArr[i + 1]) == 0 && testArr[i].getValue() > testArr[i + 1].getValue())) {
                return false;
            }
        }

        return true;
    }

    public static void sorterChecker (ISort[] sorters) {
        /*
        For sorters array.
        0: A, 1: B, 2: C, 3: D, 4: E, 5: F
         */
        //Test 0: Stability & correctness

        for (int i = 0; i < 6; i ++) {
            //Forcing algo to be wrong

            System.out.println("\nAlgo " + i + " is stable: " + isStable(sorters[i], 20));
            System.out.println("Algo " + i + " is correct: " + checkSort(sorters[i], 50));
        }

        //Test 1:
        //Worst case scenario for insertion sort: descending array
        KeyValuePair[] modelArray1 = new KeyValuePair[20];
        KeyValuePair[] testArr1 = new KeyValuePair[20];
        for (int i = 0; i < 20; i++ ){
            modelArray1[i] = new KeyValuePair(20 - i, 20 - i);
            testArr1[i] = new KeyValuePair(20 - i, 20 - i);
        }

        //Testing descending array
        System.out.println("\nTesting test 1.");
        for (int i = 0; i < 6; i ++) {
            long avgTracker = 0;
            System.out.println("");
            //Do test 3 times per algo
            for (int j = 0; j < 3; j ++ ){
                long cost = sorters[i].sort(testArr1);
                System.out.println("Test 1." + j +" with algo " + i + " costs " + cost);
                avgTracker += cost;
                for (int k = 0; k < 20; k ++) {
                    testArr1[k] = modelArray1[k];
                }
            }
            System.out.println("\nAverage cost for algo " + i + " for test 1 is " + avgTracker/3);

        }

        //Test 2:
        //Best case scenario for insertion sort: ascending array
        KeyValuePair[] modelArray2 = new KeyValuePair[20];
        KeyValuePair[] testArr2 = new KeyValuePair[20];

        for (int i = 0; i < 20; i++ ){
            modelArray2[i] = new KeyValuePair(i, i);
            testArr2[i] = new KeyValuePair(i, i);
        }

        //Testing ascending array
        System.out.println("\nTesting test 2.");
        for (int i = 0; i < 6; i ++) {
            long avgTracker = 0;
            System.out.println("");
            //Do test 3 times per algo
            for (int j = 0; j < 3; j ++ ){
                long cost = sorters[i].sort(testArr2);
                System.out.println("Test 2." + j +" with algo " + i + " costs " + cost);
                avgTracker += cost;
                for (int k = 0; k < 20; k++) {
                    testArr2[k] = modelArray2[k];
                }
            }
            System.out.println("\nAverage cost for algo " + i + " for test 2 is " + avgTracker/3);

        }

        //Test 3:
        //Best case for QuickSort: All elements are the same.
        KeyValuePair[] modelArray3 = new KeyValuePair[20];
        KeyValuePair[] testArr3 = new KeyValuePair[20];
        for (int i = 0; i < 20; i++ ){
            modelArray3[i] = new KeyValuePair(20, 20);
            testArr3[i] = new KeyValuePair(20, 20);
        }

        System.out.println("\nTesting test 3.");
        for (int i = 0; i < 6; i ++) {
            long avgTracker = 0;
            System.out.println("");
            //Do test 3 times per algo
            for (int j = 0; j < 3; j ++ ){
                long cost = sorters[i].sort(testArr3);
                System.out.println("Test 3." + j +" with algo " + i + " costs " + cost);
                avgTracker += cost;
                for (int k = 0; k < 20; k ++) {
                    testArr3[k] = modelArray3[k];
                }
            }
            System.out.println("\nAverage cost for algo " + i + " for test 3 is " + avgTracker/3);

        }

        //Test 4:
        //Differentiate Bubble and Insertion Sorts. Last element is smallest, rest of array is sorted.
        KeyValuePair[] modelArray4 = new KeyValuePair[20];
        KeyValuePair[] testArr4 = new KeyValuePair[20];
        for (int i = 0; i < 19; i++ ){
            modelArray4[i] = new KeyValuePair(i + 1, 20);
            testArr4[i] = new KeyValuePair(i + 1, 20);
        }
        modelArray4[19] = new KeyValuePair(0, 20);
        testArr4[19] = new KeyValuePair(0, 20);

        System.out.println("\nTesting test 4.");
        for (int i = 0; i < 6; i ++) {
            long avgTracker = 0;
            System.out.println("");
            //Do test 3 times per algo
            for (int j = 0; j < 3; j ++ ){
                long cost = sorters[i].sort(testArr4);
                System.out.println("Test 4." + j +" with algo " + i + " costs " + cost);
                avgTracker += cost;
                for (int k = 0; k < 20; k ++) {
                    testArr4[k] = modelArray4[k];
                }
            }
            System.out.println("\nAverage cost for algo " + i + " for test 4 is " + avgTracker/3);

        }
    }

    public static void hateSorterB () {
        //We see how many tries it takes for algo B to be incorrect
        int count = 1;
        while (checkSort(new SorterB(), 10000) && count < 500) {
            count ++;
        }
        System.out.println("\nB took " + count + " tries to be wrong.");
    }

    public static void main(String[] args) {
        //E is evil
        // TODO: implement this
        ISort[] sorters = new ISort[] {new SorterA(), new SorterB(), new SorterC(), new SorterD(), new SorterE(), new SorterF()};
        sorterChecker(sorters);
        hateSorterB();

    }
}
