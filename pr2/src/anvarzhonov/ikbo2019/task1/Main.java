package anvarzhonov.ikbo2019.task1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author - Anvarzhonov Z.T. IKBO-20-19 on 25.09.2022 - 17:06
 */
public class Main {
    public static void main(String[] args) throws IOException {
        try (RandomAccessFile file = new RandomAccessFile("pr2\\src\\anvarzhonov\\ikbo2019\\task1\\example.txt", "r")) {
            FileChannel fileChannel = file.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(512);

            char[] chars = new char[200];
            while (fileChannel.read(byteBuffer) > 0) {
                byteBuffer.flip();
                int i = 0;
                while (byteBuffer.hasRemaining()) {
                    chars[i++] = (char) byteBuffer.get();
                    //                    System.out.println((char) byteBuffer.get());
                }
            }
            System.out.println(chars);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
