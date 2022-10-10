package anvarzhonov.ikbo2019.task1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

/**
 * @author - Anvarzhonov Z.T. IKBO-20-19 on 25.09.2022 - 17:06
 */
public class Main {
    public static void main(String[] args) throws IOException {
        try (RandomAccessFile file = new RandomAccessFile("pr2\\src\\anvarzhonov\\ikbo2019\\task2\\destFile.txt", "r")) {
            var fileChannel = file.getChannel();
            var byteBuffer = ByteBuffer.allocate(512);

            while (fileChannel.read(byteBuffer) > 0) {
                byteBuffer.flip();
                int i = 0;
                while (byteBuffer.hasRemaining()) {
                    System.out.print((char) byteBuffer.get());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
