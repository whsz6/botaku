package com.cholab.botaku.Tichu.Application.Service;

import com.cholab.botaku.Tichu.Application.Port.In.GameStartUseCase;
import com.cholab.botaku.Tichu.Application.Port.Out.LoadGamePort;
import com.cholab.botaku.Tichu.Domain.Tichu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public record TichuGameStartService(LoadGamePort loadGamePort) implements GameStartUseCase {

    @Override
    public boolean gameStart(GameStartCommand command) {
        long roomId = command.getFromPlayer().getRoomId();
        String gameInitData = command.getGameInitData();
        if (loadGamePort.getGame(Tichu.class, roomId) == null) {
            return loadGamePort.createGame(Tichu.class, roomId, gameInitData);
        }
        return false;
    }
}
