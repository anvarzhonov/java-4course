package anvarzhonov.ikbo2019.task2;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * todo Document type CalculatorExecutorService
 */
public class CalculatorExecutorService {
    private Random random = new Random();
    private final ExecutorService executorService;

    private final double enteredDigit;

    public CalculatorExecutorService(double enteredDigit) {
        this.enteredDigit = enteredDigit;
        executorService = Executors.newSingleThreadExecutor();
    }

    public Future<Double> getSubmittedTask() {
        Callable<Double> task = () -> {
            System.out.println("Идет обработка запроса...");
            double calculatedValue = Math.pow(enteredDigit, 2);
            int timeOfDelay = random.nextInt(5 - 3) + 3;
            System.out.printf("Окончание обработки через %s секунды %n", timeOfDelay);
            Thread.sleep(timeOfDelay * 1000);
            return calculatedValue;
        };

        return executorService.submit(task);
    }

    public void destroyExecutor() {
        executorService.shutdown();
    }
}
