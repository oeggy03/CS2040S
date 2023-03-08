class Queue {
    private int[] queueArray;
    private int start;
    private int end;
    private int size;

    public Queue(int size) {
        queueArray = new int[size];
        start = 0;
        end = -1;
        this.size = size;
    }

    private void enqueue(int value) {
        if (end == size - 1) {
            end++;
            queueArray[end] = value;
        } else {
            System.out.println("Queue is full");
        }
    }

    private int dequeue() {
        if (start > end) {
            System.out.println("Queue is empty");
            return -1;
        }
        int dequeuedValue = queueArray[start];
        start++;
        return dequeuedValue;
    }

    private int peek() {
        if (start > end) {
            System.out.println("Queue is empty");
            return -1;
        }
        return queueArray[start];
    }

    boolean isEmpty() {
        return start > end;
    }

}

