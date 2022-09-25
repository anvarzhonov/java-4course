package anvarzhonov.ikbo2019.task3.proccess;

import anvarzhonov.ikbo2019.task3.File;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class FileHandler extends CommonFileOperation {
    private final File file;
    private final String fileType;

    public FileHandler(File file) {
        this.file = file;
        this.fileType = generateRandomFileType();
    }

    public Future<Boolean> handleFile() throws InterruptedException {
        Callable<Boolean> task = () -> {

            if (!file.getFileType().equals(fileType)) {
                System.out.printf("Обработчик не может обработать тип файла %s. Требуемый тип файла - %s%n", file.getFileType(), fileType);
                return false;
            }
            try {
                int timeOfHandling = file.getFileSize() * 7;
                System.out.printf("Обработка файла: %s займет %s ms%n", file, timeOfHandling);
                Thread.sleep(timeOfHandling);
                return true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return false;
        };

        return executorService.submit(task);
    }
}

