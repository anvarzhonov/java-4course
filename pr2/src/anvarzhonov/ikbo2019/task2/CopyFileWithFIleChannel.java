package anvarzhonov.ikbo2019.task2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Реализация копирования файла с помощью FileChannel в Java NIO
 *
 * @author - Anvarzhonov Z.T. IKBO-20-19 on 09.10.2022 - 23:51
 */
public class CopyFileWithFIleChannel implements CopyingFile {
    @Override
    public void execute(String sourcePath, String destinationPath) throws FileNotFoundException {
        try (var inputChannel = new FileInputStream(sourcePath).getChannel();
             var outputChannel = new FileOutputStream(destinationPath).getChannel()) {
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
