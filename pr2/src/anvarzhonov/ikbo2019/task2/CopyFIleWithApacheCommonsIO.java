package anvarzhonov.ikbo2019.task2;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Реализация копирования файла с помощью библиотеки Apache Commons IO
 *
 * @author - Anvarzhonov Z.T. IKBO-20-19 on 09.10.2022 - 23:51
 */
public class CopyFIleWithApacheCommonsIO implements CopyingFile {
    @Override
    public void execute(String sourcePath, String destinationPath) throws IOException {
        File sourceFile = new File(sourcePath);
        File destFile = new File(destinationPath);
        FileUtils.copyFile(sourceFile, destFile);
    }
}
