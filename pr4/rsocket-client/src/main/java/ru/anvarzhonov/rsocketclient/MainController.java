package ru.anvarzhonov.rsocketclient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.anvarzhonov.rsocketclient.DTO.Plane;
import ru.anvarzhonov.rsocketclient.DTO.PlaneRequest;

import java.util.List;

/**
 * todo Document type MainController
 *
 * @author - Anvarzhonov Z.T. IKBO-20-19 on 24.10.2022 - 19:57
 */
@RestController
@Slf4j
@RequiredArgsConstructor
public class MainController {

    private final Mono<RSocketRequester> requester;

    //    @GetMapping("fire-and-forget")
    //    public Mono<Void> fireAndForget() {
    //        socket.fireAndForget(DefaultPayload.create("fire-and-forget"))
    //            .subscribe(System.out::println);
    //
    //        return Mono.empty();
    //    }

    @GetMapping("/plane/{id}")
    public Mono<Plane> getRequestResponse(@PathVariable Long id) {
        return requester
            .map(r -> r.route("request-response")
                .data(id))
            .flatMap(r -> r.retrieveMono(Plane.class));

        //        return socket.requestResponse(DefaultPayload.create("request-response- hello"))
        //            .doOnNext(x -> log.info("response from server: {}", x.getDataUtf8()));
    }

    @GetMapping("/plans")
    public Flux<Plane> getPlans() {
        return requester
            .map(r -> r.route("request-stream"))
            .flatMapMany(r -> r.retrieveFlux(Plane.class));
    }

    @PostMapping("/newPlan")
    public void createNewPlane(@RequestBody PlaneRequest request) {
        log.info("[fire and forget] new Plane {}", request);
        requester
            .map(r -> r.route("fire-and-forget")
                .data(request)
                .send()
                .subscribe())
            .subscribe();
    }

    @PostMapping("/getPlansWithId")
    public Flux<Plane> getPlansWithID(@RequestBody List<Long> ids) {
        Flux<Long> longFlux = Flux.fromStream(ids.stream());

        return requester
            .map(r -> r.route("channel")
                .data(longFlux))
            .flatMapMany(r -> r.retrieveFlux(Plane.class));
//                .retrieveFlux(Plane.class)
//                .subscribe(out -> log.info("[channel] Plane : {}", out)))

    }

    //    @GetMapping("stream")
    //    public Flux<Payload> getRequestStream() {
    //        return socket.requestStream(DefaultPayload.create("request-stream from client..."))
    //            .delayElements(Duration.ofSeconds(3))
    //            .doOnNext(payload -> log.info(payload.getDataUtf8()))
    //            .doOnComplete(() -> log.info("completed!"));
    ////            .subscribe(payload ->
    ////                log.info(payload.getDataUtf8()),
    ////                e -> log.error("error from server response <- {}", e.toString()),
    ////                () -> log.info("Completed!")
    ////            );
    //    }

    //    @GetMapping("channel")
    //    public Flux<String> getChannel() {
    //        return socket.requestChannel(
    //            Flux.interval(Duration.ofMillis(2000))
    //                .map(l -> DefaultPayload.create("PING!")))
    //            .map(Payload::getDataUtf8)
    //            .doOnNext(str -> log.info("received: {}", str))
    //            .take(20);
    //    }
}
