class Stack {
    private int[] stackArray;
    private int top;
    private int size;

    public Stack(int size) {
        stackArray = new int[size];
        top = -1;
        this.size = size;
    }

    public void push(int value) {
        if (top != size - 1) {
            top++;
            stackArray[top] = value;
        } else {
            System.out.println("Stack full");
        }
    }

    public int pop() {
        if (top != -1) {
            int poppedValue = stackArray[top];
            top--;
            return poppedValue;

        } else {
            System.out.println("Stack is empty");
            return -1;
        }

    }

    public int peek() {
        if (top == -1) {
            System.out.println("Stack is empty");
            return -1;
        }
        return stackArray[top];
    }

    boolean isEmpty() {
        return top == -1;
    }
}
