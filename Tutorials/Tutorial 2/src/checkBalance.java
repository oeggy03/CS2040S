public class checkBalance {
    public static boolean isBalanced(String input) {
        int inputLength = input.length();
        Stack targetStack = new Stack(inputLength);
        for (int i = 0; i < inputLength; i++) {
            char c = input.charAt(i);
            if (c == '(') {
                targetStack.push(c);
            } else if (c == ')') {
                if (targetStack.isEmpty()) {
                    return false;
                } else {
                    targetStack.pop(); // If we find a ) can just rem the ( in the stack to signify that it is cancelled out
                }
            }
        }
        return targetStack.isEmpty();
    }

    public static void main(String[] args) {
        String input = ")(())(";
        System.out.println(isBalanced(input));
    }
}





