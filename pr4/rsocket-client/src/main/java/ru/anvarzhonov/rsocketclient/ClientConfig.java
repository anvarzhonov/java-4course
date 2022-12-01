package ru.anvarzhonov.rsocketclient;

import io.rsocket.transport.netty.client.TcpClientTransport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.codec.cbor.Jackson2CborDecoder;
import org.springframework.http.codec.cbor.Jackson2CborEncoder;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

/**
 * todo Document type ClientConfig
 *
 * @author - Anvarzhonov Z.T. IKBO-20-19 on 24.10.2022 - 20:00
 */
@Configuration
@Slf4j
public class ClientConfig {
    private static final int SERVER_PORT = 7000;

    //    @Bean
    //    public RSocket rSocketConfig() {
    //        return RSocketFactory.connect()
    //            .transport(TcpClientTransport.create("localhost", SERVER_PORT))
    //            .start()
    //            .doOnNext(x -> log.info("Client is started."))
    //            .block();
    //    }

    //    @Bean
    //    public RSocketRequester rSocketRequester(RSocketStrategies rSocketStrategies) {
    //        var inetSocketAddress = new InetSocketAddress(SERVER_PORT);
    //        return RSocketRequester.builder()
    //
    //    }

    @Bean
    public RSocketStrategies rSocketStrategies() {
        return RSocketStrategies.builder()
            .encoders(encoders -> encoders.add(new Jackson2CborEncoder()))
            .decoders(decoders -> decoders.add(new Jackson2CborDecoder()))
            .build();
    }

    @Bean
    public Mono<RSocketRequester> getRSocketRequester(RSocketRequester.Builder builder) {
        return builder
            .rsocketConnector(rSocketConnector -> rSocketConnector.reconnect(Retry.fixedDelay(2, Duration.ofSeconds(2))))
            .dataMimeType(MediaType.APPLICATION_CBOR)
            .connect(TcpClientTransport.create(SERVER_PORT));
    }
}
