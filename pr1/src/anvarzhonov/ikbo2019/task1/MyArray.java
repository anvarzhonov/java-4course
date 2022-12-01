package anvarzhonov.ikbo2019.task1;

public class MyArray {
    public static final int SIZE_ARRAY = 3000;
    int[] arr = new int[SIZE_ARRAY];
    int sum, index = 0;

    public MyArray() {
        fillArray(arr);
    }

    public void addElementToSum() throws InterruptedException {
        sum += arr[index++];
        Thread.sleep(1);
    }

    public static void fillArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
    }

    public static void printArray(int[] arr) {
        for (int j : arr) {
            System.out.println(j);
        }
        System.out.println("============");
    }
}

