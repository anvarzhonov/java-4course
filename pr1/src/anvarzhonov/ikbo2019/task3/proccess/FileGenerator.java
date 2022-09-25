package anvarzhonov.ikbo2019.task3.proccess;

import anvarzhonov.ikbo2019.task3.File;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class FileGenerator extends CommonFileOperation{
    private static final int FILE_SIZE_MIN = 10, FILE_SIZE_MAX = 100;
    private static final int GENERATOR_DELAY_MIN = 100, GENERATOR_DELAY_MAX = 1000;

    public Future<File> generateFile() {
        Callable<File> task = () -> {
            int timeOfDelay = generateRandom(GENERATOR_DELAY_MIN, GENERATOR_DELAY_MAX);

            System.out.printf("Генерируем файлы с задержкой: %s ms%n", timeOfDelay);
            Thread.sleep(timeOfDelay);

            int randomFileSize = generateRandom(FILE_SIZE_MIN, FILE_SIZE_MAX);

            return new File(generateRandomFileType(), randomFileSize);
        };

        return executorService.submit(task);
    }

}
