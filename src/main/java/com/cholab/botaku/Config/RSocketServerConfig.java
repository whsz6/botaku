package com.cholab.botaku.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.cbor.Jackson2CborDecoder;
import org.springframework.http.codec.cbor.Jackson2CborEncoder;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.messaging.rsocket.annotation.support.RSocketMessageHandler;
import org.springframework.util.MimeType;

// 서버 사이드에서 annotated responder 를 사용하려면, RSocketMessageHandler 를 스프링 Configuration 에 선언해야 한다.
// 그래야 @Controller 빈에 있는 @MessageMapping, @ConnectMapping 메서드를 인식한다.
// spring boot 가 RSocketMessageHandler 를 빈으로 등록하기 때문에 따로 만들어주지 않아도 된다.
@Configuration
public class RSocketServerConfig {
    @Bean
    public RSocketStrategies rsocketStrategies() {
        return RSocketStrategies.builder()
                .encoders(encoders -> encoders.add(new Jackson2CborEncoder()))
                .decoders(decoders -> decoders.add(new Jackson2CborDecoder()))
                .build();
    }
}
