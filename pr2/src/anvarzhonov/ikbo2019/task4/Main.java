package anvarzhonov.ikbo2019.task4;

import java.io.IOException;
import java.nio.file.Paths;

public class Main {
    static final String DIRECTORY_PATH = "pr2\\src\\anvarzhonov\\ikbo2019\\task4\\resources";

    public static void main(String[] args) throws IOException, InterruptedException {
        WatchingService watchingService = new WatchingService(Paths.get(DIRECTORY_PATH));

        watchingService.observeOfDirectory(Paths.get(DIRECTORY_PATH));
    }
}
