package org.example.task2;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * todo Document type CopyingFile
 *
 * @author - Anvarzhonov Z.T. IKBO-20-19 on 09.10.2022 - 22:02
 */
public interface CopyingFile {

    /**
     * Выполнить копирование файла
     * @param sourcePath - путь к файлу
     * @param destinationPath - путь к файлу куда идет запись
     */
    void execute(String sourcePath, String destinationPath) throws IOException;
}
