package anvarzhonov.ikbo2019.task3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.function.Function;

public class Main {
    static final String FILE_PATH = "pr2\\src\\anvarzhonov\\ikbo2019\\task3\\total.txt";

    public static void main(String[] args) {
        CalculateCheckSumService calculateCheckSumService = new CalculateCheckSumService();

        calculateCheckSumService.execute(FILE_PATH);
    }
}
