//package anvarzhonov.ikbo2019.task2;
//
//import org.apache.commons.io.FileUtils;
//
//import java.io.FileNotFoundException;
//import java.nio.file.Path;
//
///**
// * Реализация копирования файла с помощью библиотеки Apache Commons IO
// *
// * @author - Anvarzhonov Z.T. IKBO-20-19 on 09.10.2022 - 23:51
// */
//public class CopyFIleWithApacheCommonsIO implements CopyingFile {
//    @Override
//    public void execute(String sourcePath, String destinationPath) throws FileNotFoundException {
//        FileUtils.copyFile(Path.of(sourcePath), Path.of(destinationPath));
//    }
//}
