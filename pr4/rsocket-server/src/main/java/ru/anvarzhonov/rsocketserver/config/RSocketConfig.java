//package ru.anvarzhonov.rsocketserver;
//
//import io.rsocket.RSocketFactory;
//import io.rsocket.transport.netty.client.TcpClientTransport;
//import io.rsocket.transport.netty.server.TcpServerTransport;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import reactor.core.Disposable;
//import reactor.core.publisher.Mono;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//
///**
// * todo Document type RSocketConfig
// *
// * @author - Anvarzhonov Z.T. IKBO-20-19 on 26.10.2022 - 17:04
// */
//@Configuration
//@RequiredArgsConstructor
//public class RSocketConfig {
//    private static final int SERVER_PORT = 7000;
//    private final RSocketService rSocketService;
//    private final Disposable server;
//
//    @PostConstruct
//    public void startServer() {
//        RSocketFactory.receive()
//            .acceptor((setupPayload, reactiveSocket) -> Mono.just(rSocketService))
//            .transport(TcpServerTransport.create("localhost", SERVER_PORT))
//            .start()
//            .subscribe();
//    }
//
//    @PreDestroy
//    public void destroyServer() {
//        server.dispose();
//    }
//}
