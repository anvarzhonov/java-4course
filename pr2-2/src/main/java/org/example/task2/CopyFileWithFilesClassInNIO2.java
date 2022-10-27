package org.example.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * Реализация копирования файла с помощью Files.class (библиотека Java NIO2)
 *
 * @author - Anvarzhonov Z.T. IKBO-20-19 on 09.10.2022 - 23:52
 */
public class CopyFileWithFilesClassInNIO2 implements CopyingFile {
    @Override
    public void execute(String sourcePath, String destinationPath) throws IOException {
//        Files.delete(Path.of(destinationPath));
        Files.copy(Path.of(sourcePath), Path.of(destinationPath), StandardCopyOption.REPLACE_EXISTING);
    }
}
