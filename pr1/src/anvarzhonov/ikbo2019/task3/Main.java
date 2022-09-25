package anvarzhonov.ikbo2019.task3;

import anvarzhonov.ikbo2019.task3.proccess.FileGenerator;
import anvarzhonov.ikbo2019.task3.proccess.FileHandler;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Main {
    static final int QUEUE_SIZE = 5;
    static Queue<File> queue = new ArrayDeque<>();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Queue<File> queue = new ArrayDeque<>();

        FileGenerator fileGenerator = new FileGenerator();
        while (queue.size() < QUEUE_SIZE) {
            Future<File> fileFuture = fileGenerator.generateFile();

            queue.add(fileFuture.get());
            System.out.println("До обработки: " + queue);

            FileHandler fileHandler = new FileHandler(queue.peek());

            if (isSuccessHandlingFile(fileHandler)) {
                queue.remove();
            }

            System.out.println("После обработки: " + queue);
            fileHandler.destroyExecutor();
        }

        fileGenerator.destroyExecutor();
    }

    private static void addFileToQueue(File file) throws InterruptedException {
        System.out.println(file);
        queue.offer(file);
    }

    private static Boolean isSuccessHandlingFile(FileHandler fileHandler) throws InterruptedException, ExecutionException {
        Future<Boolean> handledFile = fileHandler.handleFile();

        return handledFile.get();
    }
}
