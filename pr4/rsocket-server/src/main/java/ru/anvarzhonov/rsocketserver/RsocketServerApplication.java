package ru.anvarzhonov.rsocketserver;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.anvarzhonov.rsocketserver.DTO.Plane;
import ru.anvarzhonov.rsocketserver.repository.PlaneRepository;

import java.util.List;

@SpringBootApplication
public class RsocketServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RsocketServerApplication.class, args);
    }

    @Bean
    CommandLineRunner init(PlaneRepository repository) {
        return args -> {
            repository.deleteAll();
            repository.saveAll(List.of(
                new Plane(1L, "test-1", 2000, "engine-1"),
                new Plane(2L, "test-2", 2100, "engine-2"),
                new Plane(3L, "test-3", 2300, "engine-3"),
                new Plane(4L, "test-4", 2400, "engine-4"),
                new Plane(5L, "test-5", 2500, "engine-5"),
                new Plane(6L, "test-6", 2600, "engine-6")
            ));
        };
    }
}
