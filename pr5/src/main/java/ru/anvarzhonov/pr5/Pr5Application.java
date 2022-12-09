package ru.anvarzhonov.pr5;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.anvarzhonov.pr5.service.StorageService;

@SpringBootApplication
public class Pr5Application {

    public static void main(String[] args) {
        SpringApplication.run(Pr5Application.class, args);
    }

//    @Bean
//    CommandLineRunner init(StorageService storageService) {
//        return (args) -> {
////            storageService.deleteAll();
////            storageService.init();
//        };
//    }

}
