//package ru.anvarzhonov.rsocketserver;
//
//import io.rsocket.AbstractRSocket;
//import io.rsocket.Payload;
//import io.rsocket.util.DefaultPayload;
//import lombok.extern.slf4j.Slf4j;
//import org.reactivestreams.Publisher;
//import org.springframework.stereotype.Service;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
///**
// * todo Document type RSocketService
// *
// * @author - Anvarzhonov Z.T. IKBO-20-19 on 24.10.2022 - 20:04
// */
//@Slf4j
////@Service
//public class RSocketService extends AbstractRSocket {
//
//    @Override
//    public Mono<Payload> requestResponse(Payload payload) {
//        log.info("requestResponse: comming data: {}", payload.getDataUtf8());
//        return Mono.just(DefaultPayload.create("Connection success!"));
//    }
//
//    @Override
//    public Mono<Void> fireAndForget(Payload payload) {
//        log.info("fire-and-forgot: server received: {}", payload.getDataUtf8());
//        return Mono.empty();
//    }
//
//    @Override
//    public Flux<Payload> requestStream(Payload payload) {
//        log.info("request-Stream: server received: {}", payload.getDataUtf8());
//        return Flux.range(1,5)
//            .map(x -> DefaultPayload.create("request-stream" + x));
//    }
//
//    @Override
//    public Flux<Payload> requestChannel(Publisher<Payload> payloads) {
//        log.info("request-channel: server received: {}", payloads);
//
//        return Flux.from(payloads).map(Payload::getDataUtf8)
//            .doOnNext(str -> log.info("received: {}", str))
//            .map(DefaultPayload::create);
//    }
//
//}
//
