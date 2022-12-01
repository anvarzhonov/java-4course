package ru.anvarzhonov.pr5;

/**
 * todo Document type StorageNotFoundException
 *
 * @author - Anvarzhonov Z.T. IKBO-20-19 on 15.11.2022 - 19:29
 */
public class StorageNotFoundException extends RuntimeException {

    public StorageNotFoundException(String message) {
        super(message);
    }

    public StorageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
