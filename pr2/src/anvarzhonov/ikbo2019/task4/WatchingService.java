package anvarzhonov.ikbo2019.task4;

import anvarzhonov.ikbo2019.task3.CalculateCheckSumService;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashMap;
import java.util.Map;

public class WatchingService {
    private final Path directoryPath;

    CalculateCheckSumService calculateCheckSumService;
    WatchService watchService;
    Map<Path, String> filesMap = new HashMap<>();

    public WatchingService(Path directoryPath) throws IOException {
        this.directoryPath = directoryPath;
        watchService = FileSystems.getDefault().newWatchService();
        calculateCheckSumService = new CalculateCheckSumService();
        System.out.println("Наблюдение за каталогом началось..." + directoryPath);
    }

    public void observeOfDirectory(Path path) throws IOException, InterruptedException {
        path.register(watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);

        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                if (!isFilePathNotContainSpecSymbol(event.context().toString())) {
                    actionOnEvent(event.kind(), event.context());
                }
            }
            key.reset();
        }
    }

    private void actionOnEvent(WatchEvent.Kind event, Object filePath) throws IOException {
        if (StandardWatchEventKinds.ENTRY_CREATE.equals(event)) {
            System.out.printf("[EVENT CREATED FILE] Был создан новый файл %s\\%s\n", directoryPath, filePath.toString());
            addTheSameFileToMap(filePath.toString());
        } else if (StandardWatchEventKinds.ENTRY_MODIFY.equals(event)) {
//            System.out.println("Event kind:" + event
//                    + ". File affected: " + filePath + ".");
            System.out.printf("[EVENT MODIFY FILE] Изменения в файле %s\n", filePath);
            updatedAction(filePath.toString());
        } else if (StandardWatchEventKinds.ENTRY_DELETE.equals(event)) {
            System.out.printf("[EVENT DELETE FILE] Был удален файл %s\n", filePath);
            Path path = getPath(filePath.toString());
            String linesMatchedByFilePath = filesMap.get(path);
            int checkSum = calculateCheckSumService.calculateCheckSum(ByteBuffer.wrap(linesMatchedByFilePath.getBytes(StandardCharsets.UTF_8)));
            System.out.printf("Контрольная сумма у удаленного файла %s равна: %s \n", path, checkSum);
            filesMap.remove(path);
        }
    }

    private void addTheSameFileToMap(String filePath) {
        String fullfilePath = getFullPath(filePath);
        Path path = Paths.get(fullfilePath);
        filesMap.put(path, "");
        System.out.println("Map: " + filesMap);

    }

    private void updatedAction(String filePath) throws IOException {
        Path path = getPath(filePath);

        var oldestLines = filesMap.get(path);
        var newLines = Files.readString(path);

        if (!oldestLines.equals(newLines) && oldestLines.isEmpty()) {
            System.out.printf("В файле %s был добавлен символ %s\n", filePath, newLines);
        } else if (!oldestLines.equals(newLines) && oldestLines.length() > newLines.length()) {
            String substring = oldestLines.substring(newLines.length());
            System.out.printf("В файле %s были удалены символы %s\n", filePath, substring);
        } else if (!oldestLines.equals(newLines)) {
            String substring = newLines.substring(oldestLines.length());
            System.out.printf("В файле %s был добавлен символ %s\n", filePath, substring);
        }


        filesMap.put(path, newLines);
        System.out.println("Map: " + filesMap);
    }

    private String getFullPath(String filePath) {
        return String.format("%s\\%s", directoryPath, filePath);
    }

    private Path getPath(String filePath) {
        return Paths.get(getFullPath(filePath));
    }
    private boolean isFilePathNotContainSpecSymbol(String filePath) {
        return getFullPath(filePath).contains("~");
    }

}
