package ru.anvarzhonov.task4;

/**
 * todo Document type File
 *
 * @author - Anvarzhonov Z.T. IKBO-20-19 on 20.10.2022 - 10:18
 */
public class File {
    private final String fileType;
    private final int fileSize;

    public File(String fileType, int fileSize) {
        this.fileType = fileType;
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public int getFileSize() {
        return fileSize;
    }

    @Override
    public String toString() {
        return "File{" +
            "fileType='" + fileType + '\'' +
            ", fileSize=" + fileSize +
            '}';
    }
}
