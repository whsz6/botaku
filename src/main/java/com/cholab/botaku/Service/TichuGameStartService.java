package com.cholab.botaku.Service;

import com.cholab.botaku.Usecase.Tichu.GameStartUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TichuGameStartService implements GameStartUseCase {
    @Autowired
    GameMap gameMap;

    @Override
    public boolean gameStart(GameStartCommand command) {
        long roomId = command.getFromPlayer().getRoomId();
        String gameInitData = command.getGameInitData();
        if (gameMap.getGame(roomId) == null) {
            return gameMap.createGame(roomId, gameInitData);
        }
        return false;
    }
}
