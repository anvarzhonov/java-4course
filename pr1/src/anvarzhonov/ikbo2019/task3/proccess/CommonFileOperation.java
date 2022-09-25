package anvarzhonov.ikbo2019.task3.proccess;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class CommonFileOperation {
    protected static final String[] FileTypes = new String[]{"XML", "JSON", "XLS"};

    protected final ExecutorService executorService = Executors.newSingleThreadExecutor();

    protected int generateRandom(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    protected String generateRandomFileType() {
        Random random = new Random();
        int index = random.nextInt(FileTypes.length);
        return FileTypes[index];
    }

    public void destroyExecutor() {
        executorService.shutdown();
    }
}
