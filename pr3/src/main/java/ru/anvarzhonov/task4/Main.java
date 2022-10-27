package ru.anvarzhonov.task4;

import org.apache.commons.lang3.RandomUtils;
import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * todo Document type Main
 *
 * @author - Anvarzhonov Z.T. IKBO-20-19 on 20.10.2022 - 10:17
 */
public class Main {
    protected static final String[] FileTypes = new String[]{"XML", "JSON", "XLS"};
    private static final int FILE_SIZE_MIN = 1000, FILE_SIZE_MAX = 2000;
    static Queue<File> queue = new ArrayDeque<>(5);

    public static void main(String[] args) {

        int delay = RandomUtils.nextInt(100, 1000);

        Observable<File> fileFlow = Observable
            .interval(2000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.from(Executors.newSingleThreadExecutor()))
            .observeOn(Schedulers.from(Executors.newSingleThreadExecutor()))
            .map(x -> {
                int randomFileSize = generateRandom();

                File file = new File(generateRandomFileType(), randomFileSize);

                System.out.println("Сгенерился новый файл: " + file);

                return file;
            });

        Observable<File> fileObservable = Observable.from(queue)
            .subscribeOn(Schedulers.from(Executors.newSingleThreadExecutor()))
            .observeOn(Schedulers.from(Executors.newSingleThreadExecutor()));

        fileFlow.subscribe(x -> {
            if (queue.size() != 5) {
                System.out.println("В очередь добавлен файл: " + x);
                queue.add(x);
            }
            fileObservable.subscribe(Main::handleFile);
        });
    }

    private static void handleFile(File file) {
        try {
            int timeOfHandling = file.getFileSize() * 7;
            System.out.printf("Обработка файла: %s займет %s ms%n", file, timeOfHandling);
            Thread.sleep(timeOfHandling);
            System.out.printf("Обработка файла: %s закончена %s ms%n", file, timeOfHandling);
            queue.remove(file);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static int generateRandom() {
        Random random = new Random();
        return random.nextInt(FILE_SIZE_MAX - FILE_SIZE_MIN) + FILE_SIZE_MIN;
    }

    private static String generateRandomFileType() {
        Random random = new Random();
        int index = random.nextInt(FileTypes.length);
        return FileTypes[index];
    }
}
