package com.cholab.botaku;

import com.cholab.botaku.Chat.DTO.Message;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rsocket.server.LocalRSocketServerPort;
import org.springframework.http.codec.cbor.Jackson2CborDecoder;
import org.springframework.http.codec.cbor.Jackson2CborEncoder;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class BotakuApplicationTests {
    private static RSocketRequester requester;

    @BeforeAll
    public static void setUp(@Autowired RSocketRequester.Builder builder,
                             @LocalRSocketServerPort Integer port) {

        RSocketStrategies strategies = RSocketStrategies.builder()
                .encoders(encoders -> encoders.add(new Jackson2CborEncoder()))
                .decoders(decoders -> decoders.add(new Jackson2CborDecoder()))
                .build();

        requester = builder
                .rsocketStrategies(strategies)
                .tcp("localhost", port);
    }

    @DisplayName("chat.send 성공 테스트")
    @Test
    public void testChatSendSuccess() {
        Mono<Boolean> response = requester.route("chat.send")
                .data(new Message("testRoom", "tomas", "hello Message", 1.0))
                .retrieveMono(Boolean.class);

        StepVerifier.create(response)
                .consumeNextWith(msg -> assertThat(msg).isEqualTo(true))
                .verifyComplete();

        Flux<String> response2 = requester.route("chat.recv")
                .data("testRoom")
                .retrieveFlux(String.class);

        StepVerifier.create(response2)
                .assertNext(msg -> assertThat(msg).isEqualTo("essage(roomId=testRoom, fromPerson=tomas, message=hello Message, date=1.663073238378E12)"))
                .verifyComplete();
    }
}
