package com.cholab.botaku.Service;

import com.cholab.botaku.Domain.Game.Game;
import com.cholab.botaku.Domain.Game.GameType;
import com.cholab.botaku.Domain.Game.Tichu.Tichu;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class GameMap {
    private static Map<Long, Game> gameSessionList;

    GameMap() {
        gameSessionList = new ConcurrentHashMap<Long, Game>();
    }

    public boolean createGame(long gameRoomId, String initGameData) {
        if (!gameSessionList.containsKey(gameRoomId)) {
            gameSessionList.put(gameRoomId, new Game(gameRoomId, initGameData));
            return true;
        }
        return false;
    }
    public Game getGame(long gameRoomId) {
        return gameSessionList.getOrDefault(gameRoomId, null);
    }
}
