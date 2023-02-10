public class Main {
    /**
     * Binary search slightly modified from the lecture
     */
    public static int[] find(int[] a, int x) {
        return find0(a, x, 0, a.length - 1, 0);
    }

    public static int[] find0(int[] a, int x, int n1, int n2, int numberOfSteps) {
        int t = (n1 + n2) / 2;
        if (a[t] == x)
            return new int[]{t, numberOfSteps};
        else if (n1 >= n2)
            return new int[]{-1, numberOfSteps};
        else if (x > a[t])
            return find0(a, x, t + 1, n2, numberOfSteps + 1);
        else if (n1 < t)
            return find0(a, x, n1, t - 1, numberOfSteps + 1);
        else return new int[]{-1, numberOfSteps};
    }

    public static int[] probalisticSearch(int[] arr, int value) {
        int position;
        int powCounter = 1;
        int i = 1;
        float maxSubMin = arr[arr.length - 1] - arr[0];
        position = Math.round((value - arr[0]) / (maxSubMin / (arr.length - 1)));
        int a = position, b = position;
        while (value < arr[a] || value > arr[b]) {
            if (value < arr[a]) {
                //LEFT
                a = a - powCounter;
                b = b - powCounter / 2;
                if (a < 0) {
                    a = 0;
                }
            } else {
                //RIGHT
                a = a + powCounter / 2;
                b = b + powCounter;
                if (b > (arr.length - 1)) {
                    b = arr.length - 1;
                }
            }
            powCounter *= 2;
            i++;
        }
        return find0(arr, value, a + 1, b - 1, i);
    }

    public static void compareApproaches(int[] arr, int min, int max) {
        //comparing  the largest number of steps and at what value this occurred min and max in the array searches with both variants and outputs how many calls the variants needed
        long value = 0;
        long maxNumOfCalls = 0;
        long NumOfTotCalls = 0;
        for (int i = min; i <= max; i++) {
            int[] newArr = find(arr, i);
            NumOfTotCalls += newArr[1];
            if (newArr[1] > maxNumOfCalls) {
                maxNumOfCalls = newArr[1];
                value = i;
            }
        }
        System.out.println("Binary Search: \n" +
                "Maximum number of calls:\n" + maxNumOfCalls + "\n" +
                "Value at which the maximum number of calls occurs:\n" + value + "\n" +
                "Number of total calls:\n" + NumOfTotCalls + "\n");

        long value1 = 0;
        long maxNumOfCalls1 = 0;
        long NumOfTotCalls1 = 0;
        for (int j = min; j <= max; j++) {
            int[] newArr = probalisticSearch(arr, j);
            NumOfTotCalls1 += newArr[1];
            if (newArr[1] > maxNumOfCalls1) {
                maxNumOfCalls1 = newArr[1];
                value1 = j;
            }
        }
        System.out.println("Probabilistic Search:\n" +
                "Maximum number of calls:\n" + maxNumOfCalls1 + "\n" +
                "Value at which the maximum number of calls occurs:\n" + value1 + "\n" +
                "Number of total calls:\n" + NumOfTotCalls1 + "\n");
    }

    public static void main(String[] args) {
        // Not part of the exercise but can be helpful for debugging purposes
        //int[] exampleArray = new int[]{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 3, 100};
        int[] exampleArray = new int[]{6, 20, 22, 35, 51, 54, 59, 74, 77, 80, 87, 94, 97};
        int arr[] = probalisticSearch(exampleArray, 74);
        System.out.println(arr[0]);
        System.out.println(arr[1]);
    }
}
