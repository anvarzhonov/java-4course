//package ru.anvarzhonov.pr5.service;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.UrlResource;
//import org.springframework.stereotype.Service;
//import org.springframework.util.FileSystemUtils;
//import org.springframework.web.multipart.MultipartFile;
//import ru.anvarzhonov.pr5.StorageNotFoundException;
//
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
//import java.util.stream.Stream;
//
///**
// * todo Document type FileSystemStorageService
// *
// * @author - Anvarzhonov Z.T. IKBO-20-19 on 15.11.2022 - 19:21
// */
//@Service
//public class FileSystemStorageService implements StorageService {
//
//    private final Path rootLocation;
//
//    public FileSystemStorageService(@Value("${storage.location}") String path) {
//        this.rootLocation = Paths.get(path);
//        System.out.println(this.rootLocation);
//    }
//
//    @Override
//    public void init() {
//        try {
//            Files.createDirectory(rootLocation);
//        } catch (IOException e) {
//            throw new StorageNotFoundException("Could not initialize storage", e);
//        }
//    }
//
//    @Override
//    public void store(MultipartFile file) {
//        try {
//            if (file.isEmpty()) {
//                throw new StorageNotFoundException("Failed to store empty file.");
//            }
//            Path destinationFile = rootLocation
//                .resolve(Paths.get(file.getOriginalFilename()))
//                .normalize()
//                .toAbsolutePath();
//
//            if (!destinationFile.getParent().equals(rootLocation.toAbsolutePath())) {
//                throw new StorageNotFoundException(
//                    "Cannot store file outside current directory.");
//            }
//            try (var is = file.getInputStream()) {
//                Files.copy(is, destinationFile, StandardCopyOption.REPLACE_EXISTING);
//            }
//        } catch (IOException e) {
//            throw new StorageNotFoundException("Failed to store file", e);
//        }
//    }
//
//    @Override
//    public Stream<Path> loadAll() {
//        try {
//            return Files.walk(rootLocation, 1)
//                .filter(path -> !path.equals(rootLocation))
//                .map(rootLocation::relativize);
//        } catch (IOException e) {
//            throw new StorageNotFoundException("Failed to read stored files", e);
//        }
//    }
//
//    @Override
//    public Path load(String fileName) {
//        return rootLocation.resolve(fileName);
//    }
//
//    @Override
//    public Resource loadAsResource(String fileName) {
//        try {
//            Path file = load(fileName);
//            Resource resource = new UrlResource(file.toUri());
//            if (resource.exists() || resource.isReadable()) {
//                return resource;
//            }
//            else {
//                throw new StorageNotFoundException(
//                    "Could not read file: " + fileName);
//            }
//        }
//        catch (MalformedURLException e) {
//            throw new StorageNotFoundException("Could not read file: " + fileName, e);
//        }
//    }
//
//    @Override
//    public void deleteAll() {
//        FileSystemUtils.deleteRecursively(rootLocation.toFile());
//    }
//}
