package anvarzhonov.ikbo2019.task2;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        CalculatorExecutorService executorService;

        System.out.print("Введите число: ");
        double i = scanner.nextDouble();
        executorService = new CalculatorExecutorService(i);

        Future<Double> submittedTask = executorService.getSubmittedTask();

        while (!submittedTask.isDone()) {
            Thread.sleep(5);
            System.out.print("----[Повторный запрос во время обработки запроса] Введите число: ");

            i = scanner.nextDouble();

            System.out.println("----[Повторный запрос во время обработки запроса] Введенное число: " + i);
        }

        System.out.println("Выполненная таска: " + submittedTask.get());
        executorService.destroyExecutor();
    }
}
