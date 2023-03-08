public class ToBinary {
    private char[]charac = null;
    private int strLength = 0;

    private StringBuilder resultStr = new StringBuilder();

    /**
     * Constructor takes in the string to be encoded
     * @param str
     */
    public ToBinary(String str) {
        strLength = str.length();
        charac = str.toCharArray();
    }

    private StringBuilder convertChar() {
        for (int i = 0; i < strLength; i ++) {
            String charStr = Integer.toBinaryString(charac[i]);
            resultStr.append(charStr);
        }

        return resultStr;
    }

    public static void main(String[] args) {
        /**
         * Replace "TheCowJumpedOverTheMoon" with string of choice
         * Otherwise use Scanner to detect user input (IDK if I'm allowed to import Scanner library :( )
         */
        ToBinary binaryVersion = new ToBinary( "TheCowJumpedOverTheMoon");
        System.out.println(binaryVersion.convertChar().toString());
    }
}
