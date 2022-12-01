package ru.anvarzhonov.pr5.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * todo Document type StorageService
 *
 * @author - Anvarzhonov Z.T. IKBO-20-19 on 14.11.2022 - 20:19
 */
public interface StorageService {
    void init();

    void store(MultipartFile file);

    Stream<Path> loadAll();

    Path load(String fileName);

    Resource loadAsResource(String fileName);

    void deleteAll();
}
