import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;

/**
 * ShiftRegisterTest
 * @author dcsslg
 * Description: set of tests for a shift register implementation
 */
public class ShiftRegisterTest {
    /**
     * Returns a shift register to test.
     * @param size
     * @param tap
     * @return a new shift register
     */
    ILFShiftRegister getRegister(int size, int tap) {
        return new ShiftRegister(size, tap);
    }

    /**
     * Test edge case. seed = ‘101111010’, tap = 7. Expected: 1100011110
     */
    @Test
    public void myTest() {
        ShiftRegister shifter = new ShiftRegister(9, 7);

        int[] array = new int[] {0, 1, 0, 1, 1, 1, 1, 0, 1};
        shifter.setSeed(array);
        for (int i = 0; i < 10; i++) {
            int j = shifter.shift();
            System.out.print(j);
        }

    }

    /**
     * Test edge case. seed = ‘101111010’, tap = 7, expected = 6172216623
     */
    @Test
    public void myTest2() {
        int[] array = new int[] {0, 1, 0, 1, 1, 1, 1, 0, 1};
        ShiftRegister shifter = new ShiftRegister(9, 7);
        shifter.setSeed(array);
        for (int i = 0; i < 10; i++) {
            int j = shifter.generate(3);
            System.out.print(j);
        }
    }

    /**
     * Test case. seed = ‘1111’, tap = 2, expected = 0465
     */
    @Test
    public void myOwnTest3() {
        int[] array = new int[] {1, 1, 1, 1};
        ShiftRegister shifter = new ShiftRegister(4, 2);
        shifter.setSeed(array);
        for (int i = 0; i < 4; i++) {
            int j = shifter.generate(3);
            System.out.print(j);
        }
    }

    /**
     * Test corner case. seed = ‘0’, tap = 0, expected = 0
     */
    @Test
    public void myCornerTest() {
        int[] array = new int[] {0};
        ShiftRegister shifter = new ShiftRegister(1, 0);
        shifter.setSeed(array);
        for (int i = 0; i < 1; i++) {
            int j = shifter.generate(0);
            System.out.print(j);
        }
    }
    /**
     * Tests shift with simple example.
     */
    @Test
    public void testShift1() {
        ILFShiftRegister r = getRegister(9, 7);
        int[] seed = { 0, 1, 0, 1, 1, 1, 1, 0, 1 };
        //{1 0 1 1  1 1 0 1 0}
        r.setSeed(seed);
        int[] expected = { 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 };
        for (int i = 0; i < 10; i++) {
            assertEquals(expected[i], r.shift());
        }
    }

    /**
     * Tests generate with simple example.
     */
    @Test
    public void testGenerate1() {
        ILFShiftRegister r = getRegister(9, 7);
        int[] seed = { 0, 1, 0, 1, 1, 1, 1, 0, 1 };
        r.setSeed(seed);
        int[] expected = { 6, 1, 7, 2, 2, 1, 6, 6, 2, 3 };
        for (int i = 0; i < 10; i++) {
            assertEquals("GenerateTest", expected[i], r.generate(3));
        }
    }

    /**
     * Tests register of length 1.
     */
    @Test
    public void testOneLength() {
        ILFShiftRegister r = getRegister(1, 0);
        int[] seed = { 1 };
        r.setSeed(seed);
        int[] expected = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };
        for (int i = 0; i < 10; i++) {
            assertEquals(expected[i], r.generate(3));
        }
    }

    /**
     * Tests with erroneous seed.
     */
    @Test
    public void testError() {
        ILFShiftRegister r = getRegister(4, 1);
        int[] seed = { 1, 0, 0, 0, 1, 1, 0 };
        r.setSeed(seed);
        r.shift();
        r.generate(4);
    }
    /**
     * I believe that the correct way to handle this error is to only initialize the array when the seed is passed in.
     * If the seed is larger than the size, then initialize the register array of seed.length() bits.
     *
     * To test for this error, see if the register array is equal to the given seed after setSeed() is run. setSeed can be modified to return the created register array.
     */
}
