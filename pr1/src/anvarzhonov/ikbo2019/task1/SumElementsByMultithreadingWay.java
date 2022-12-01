package anvarzhonov.ikbo2019.task1;

import java.util.concurrent.*;

public class SumElementsByMultithreadingWay implements ArrayOperationService {
    private static final int NUMBER_OF_THREADS = 2;

    @Override
    public int getSumElementsOfArray(MyArray array) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        Object waitObject = new Object();

        Callable<Integer> task = () -> {
            synchronized (waitObject) {
                array.addElementToSum();
//                Thread.sleep(1);
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
