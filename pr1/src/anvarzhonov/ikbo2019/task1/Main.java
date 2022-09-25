package anvarzhonov.ikbo2019.task1;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        List<ArrayOperationService> getSumArrayServices = List.of(
            new SumElementsByСonsistentlyWay(),
            new SumElementsArrayByForkJoinWay(),
            new SumElementsByMultithreadingWay()
        );

        for (ArrayOperationService service : getSumArrayServices) {
            long startTime = System.currentTimeMillis();
            long totalMemory = Runtime.getRuntime().totalMemory();

            int sumElementsOfArray = service.getSumElementsOfArray(new MyArray());

            long endTime = System.currentTimeMillis() - startTime;
            long freeMemory = Runtime.getRuntime().freeMemory();

            System.out.printf("[%s] Сумма элементов массива = %s%n", service.getClass().getSimpleName(), sumElementsOfArray);
            System.out.printf("[%s] Время выполнения программы: %s ms%n", service.getClass().getSimpleName(), endTime);
            System.out.printf("[%s] Затраты по памяти: %s bytes %n", service.getClass().getSimpleName(), totalMemory - freeMemory);
        }
    }
}
