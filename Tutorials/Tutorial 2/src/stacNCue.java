public class stacNCue {
    public static int evacuation(int[] houses) {
        int noOfHouses = houses.length;
        Stack trackingStack = new Stack(noOfHouses);
        int flooded = 0;

        // iterate through the houses
        for (int i = 0; i < noOfHouses; i++) {
            //Decides whether we should start popping
            boolean shouldPop = false;

            //Checks if a house of elevation similar or larger than current has appeared
            for (int p = 0; p < i; p ++ ) {
                if (houses[p] >= houses[i]) {
                    shouldPop = true;
                }
            }
            // pop the indexes from the stack until the current house is equal or higher
            // peek returns the elevation of the previous house added
            while (!trackingStack.isEmpty() && trackingStack.peek() < houses[i] && shouldPop) {
                    trackingStack.pop();
                    flooded++;
            }
            trackingStack.push(houses[i]);
        }

        return flooded;
    }

    public static void main(String[] args) {
        System.out.println("We need to evacuate " + evacuation(new int[]{1, 0, 1, 2, 3, 1, 2, 3, 0}) + " houses");
    }
}
