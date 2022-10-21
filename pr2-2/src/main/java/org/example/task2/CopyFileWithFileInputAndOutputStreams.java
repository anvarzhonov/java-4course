package org.example.task2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Реализация копирования файла с помощью FileInputStream для чтения и OutputStream для записи (библиотека Java IO)
 *
 * @author - Anvarzhonov Z.T. IKBO-20-19 on 09.10.2022 - 22:04
 */
public class CopyFileWithFileInputAndOutputStreams implements CopyingFile {
    @Override
    public void execute(String sourcePath, String destinationPath) throws FileNotFoundException {
        try (var input = new FileInputStream(sourcePath); var output = new FileOutputStream(destinationPath)) {
            while (input.available() > 0) { //пока есть еще непрочитанные байты
                byte[] bytes = input.readAllBytes();
                output.write(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
