package anvarzhonov.ikbo2019.task1;

import java.util.concurrent.*;

public class SumElementsArrayByForkJoinWay implements ArrayOperationService {
    @Override
    public int getSumElementsOfArray(MyArray array) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newWorkStealingPool();
        Object waitObject = new Object();

        Callable<Integer> task = () -> {
            synchronized (waitObject) {
                array.addElementToSum();
            }
            return array.sum;
        };

        int totalSum = 0;
        for (int i = 0; i < MyArray.SIZE_ARRAY; i++) {
            Future<Integer> currentSum = executorService.submit(task);
            totalSum = currentSum.get();
        }

        executorService.shutdown();
        return totalSum;
    }
}
