package org.example.task3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.function.Function;

public class CalculateCheckSumService {

    private Function<Integer, Integer> sizeToKB = (size) -> (size + 1023) / 1024;

    public void execute(String filePath) {
        File file = new File(filePath);

        try (var fileChannel = new FileInputStream(file).getChannel()) {
            int size = (int) fileChannel.size();
            var byteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, size);
            int checkSum = calculateCheckSum(byteBuffer);

            Integer sizeInKb = sizeToKB.apply(size);

            System.out.printf("Контрольная сумма у файла %s равна: %s   %s", file.getName(), checkSum, sizeInKb);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int calculateCheckSum(ByteBuffer buffer) {
        int sum = 0;
        while (buffer.hasRemaining()) {
            if ((sum & 1) != 0) {
                sum >>= 1 + 0x8000;
            } else {
                sum >>= 1;
            }
            sum += buffer.get() & 0xff & 0xffff;
        }
        return sum;
    }
}
