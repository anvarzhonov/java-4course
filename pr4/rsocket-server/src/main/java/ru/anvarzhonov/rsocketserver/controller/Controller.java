package ru.anvarzhonov.rsocketserver.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.anvarzhonov.rsocketserver.DTO.Plane;
import ru.anvarzhonov.rsocketserver.DTO.PlaneRequest;
import ru.anvarzhonov.rsocketserver.repository.PlaneRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * todo Document type Controller
 *
 * @author - Anvarzhonov Z.T. IKBO-20-19 on 06.11.2022 - 15:16
 */
@RestController
@Slf4j
@RequiredArgsConstructor
public class Controller {
    private final PlaneRepository repository;

    @MessageMapping("request-response")
    Mono<Plane> requestResponse(Mono<Long> id) {
        return id
            .doOnNext(System.out::println)
            .map(this::getPlaneWithId);
    }

    @MessageMapping("request-stream")
    Flux<Plane> requestStream() {
        return Flux.fromStream(getPlans().stream());
    }

    @MessageMapping("fire-and-forget")
    void fireAndForget(Mono<PlaneRequest> request) {
        log.info("[fire and forget] new Plane {}", request);
        request
            .doOnNext(r -> log.info("[fire and forget] request to save new Plane, PlaneRequest {}", r))
            .map(this::savePlane)
            .doOnNext(r -> log.info("Saved successfully new Plane: {}", r)).subscribe();
    }

    @MessageMapping("channel")
    Flux<Plane> channel(Flux<Long> ids) {
        log.info("[channel] get planes info for ids: {}", ids);
        return ids
            .map(this::getPlaneWithId);
    }

    private Plane getPlaneWithId(Long id){
        return repository.findAll()
            .stream()
            .filter(s -> s.getId().equals(id))
            .findAny()
            .get();
    }

    private List<Plane> getPlans() {
        return repository.findAll();
    }

    private Plane savePlane(PlaneRequest r) {
        Plane plane = new Plane(r.getName(),
            r.getCapacityOfPeople(),
            r.getEngineName());
        Plane save = repository.save(plane);
        return save;
    }
}
