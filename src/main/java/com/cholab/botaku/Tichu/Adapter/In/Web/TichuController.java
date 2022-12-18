package com.cholab.botaku.Tichu.Adapter.In.Web;

import com.cholab.botaku.Tichu.DTO.Library.GameType;
import com.cholab.botaku.Tichu.DTO.SendGameRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
public class TichuController {
    private final TichuService tichuService;

    @Autowired
    public TichuController(TichuService tichuService) {
        this.tichuService = tichuService;
    }

    @MessageMapping("game.setting")
    public Mono<String> sendGameMessage(@Payload SendGameRequest sendGameRequest) {
        log.debug("[*] game.command 진입");
        String response = "";
        var game = sendGameRequest.getGameType();
        if (game == GameType.TICHU) {
            response = tichuService.resolvePacket(sendGameRequest);
        }
        return Mono.just(response);
    }

    @MessageMapping("game.info")
    public Flux<String> receiveGameMessage(@Payload SendGameRequest sendGameRequest) {
        log.debug("[*] game.info 진입");
        String response = ".";
        var game = sendGameRequest.getGameType();
        if (game == GameType.TICHU) {
            response = tichuService.resolvePacket(sendGameRequest);
        }
        return Flux.just(response);
    }

}
