///////////////////////////////////
// This is the main shift register class.
// Notice that it implements the ILFShiftRegister interface.
// You will need to fill in the functionality.
///////////////////////////////////

import java.sql.SQLOutput;


/**
 * class ShiftRegister
 * @author
 * Description: implements the ILFShiftRegister interface.
 */
public class ShiftRegister implements ILFShiftRegister {
    ///////////////////////////////////
    // Create your class variables here
    ///////////////////////////////////
    // TODO:
    int[] register = null;
    int registerSize = 0;
    int registerTap = 0;
    int registerK = 0;
    ///////////////////////////////////
    // Create your constructor here:
    ///////////////////////////////////
    public ShiftRegister(int size, int tap) {
        // TODO:
        registerSize = size - 1;
        registerTap = tap;
        register = new int[size];
    }

    ///////////////////////////////////
    // Create your class methods here:
    ///////////////////////////////////
    /**
     * setSeed
     * @param seed
     * Description:
     */
    @Override
    public void setSeed(int[] seed) {
        // TODO
        int length = seed.length;
        if (length != registerSize + 1) {
            registerSize = length - 1;
            register = new int[length];
        }

        for (int i = 0; i < length; i++) {
            if (seed[i] == 1 || seed[i] == 0) {
                register[i] = seed[i];
            } else {
                System.out.println("Error: Seed can only contain 1 and 0.");
                register = new int[length];
                break;
            }
        }
    }

    /**
     * shift
     * @return
     * Description:
     */
    @Override
    public int shift() {
        // TODO:
        int newLeastSignificant = (register[registerTap] ^ register[registerSize]);
        if (registerSize > 0) {
            for (int i = registerSize - 1; i >= 0; i--) {
                register[i + 1] = register[i];
            }
        }
        register[0] = newLeastSignificant;
        return newLeastSignificant;
    }

    /**
     * generate
     * @param k
     * @return
     * Description:
     */
    @Override
    public int generate(int k) {
        // TODO:
        registerK = k;
        int[] bitsReturned = new int[k];
        for (int i = 0; i < k; i ++) {
            bitsReturned[i] = shift();
        }

        return toDecimal(bitsReturned);

    }

    /**
     * Returns the integer representation for a binary int array.
     * @param array
     * @return
     */
    private int toDecimal(int[] array) {
        // TODO:
        double decimalDouble = 0;
        int decimalInt = 0;
        int counter = registerK;
        for (int i = 0; i < registerK; i++ ) {
            decimalDouble = decimalDouble + array[i] * Math.pow(2, registerK - 1 - i);
        }
        decimalInt = (int) Math.round(decimalDouble);
        return decimalInt;
    }
}


