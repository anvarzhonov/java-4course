package ru.anvarzhonov.rsocketserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.anvarzhonov.rsocketserver.DTO.Plane;
import ru.anvarzhonov.rsocketserver.DTO.PlaneRequest;

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
public class Controller {
    private List<Plane> plans = new ArrayList<>();

    {
        plans.add(new Plane(1L, "test-1", 2000, "engine-1"));
        plans.add(new Plane(2L,"test-2", 2100, "engine-2"));
        plans.add(new Plane(3L,"test-3", 2300, "engine-3"));
        plans.add(new Plane(4L,"test-4", 2400, "engine-4"));
        plans.add(new Plane(5L,"test-5", 2500, "engine-5"));
        plans.add(new Plane(6L,"test-6", 2600, "engine-6"));
    }

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
        return plans
            .stream()
            .filter(s -> s.getId().equals(id))
            .findAny()
            .get();
    }

    private List<Plane> getPlans() {
        return plans;
    }

    private Plane savePlane(PlaneRequest r) {
        Plane plane = new Plane(r.getName(),
            r.getCapacityOfPeople(),
            r.getEngineName());
        plans.add(plane);
        return plane;
    }
}
