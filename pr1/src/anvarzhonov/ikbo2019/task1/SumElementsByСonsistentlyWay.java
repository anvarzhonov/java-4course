package anvarzhonov.ikbo2019.task1;

public class SumElementsByСonsistentlyWay implements ArrayOperationService {

    @Override
    public int getSumElementsOfArray(MyArray array) throws InterruptedException {
        int totalSum = 0;
        for (int j : array.arr) {
            totalSum += j;
            Thread.sleep(1);
        }
        return totalSum;
    }
}
