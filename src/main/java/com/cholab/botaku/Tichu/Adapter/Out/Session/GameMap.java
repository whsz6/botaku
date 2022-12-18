package com.cholab.botaku.Tichu.Adapter.Out.Session;

import com.cholab.botaku.Tichu.Application.Port.Out.LoadGamePort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class GameMap implements LoadGamePort {
    private static Map<Long, Object> gameSessionList;

    GameMap() {
        gameSessionList = new ConcurrentHashMap<>();
    }

    @SuppressWarnings("unchecked")
    public <T> boolean createGame(Class<T> clazz, long gameRoomId, String initGameData) {
        if (hasGame(clazz, gameRoomId))
            return false;
        try {
            Constructor<?> gameConstructor = clazz.getConstructor(Long.class, String.class);
            T newGame = (T) gameConstructor.newInstance(gameRoomId, initGameData);
            gameSessionList.put(gameRoomId, newGame);
            return true;
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            log.debug("[*] GameMap don't created for exception");
            return false;
        }
    }

    public <T> boolean hasGame(Class<T> clazz, long gameRoomId) {
        if (!gameSessionList.containsKey(gameRoomId)) {
            return false;
        }
        return gameSessionList.get(gameRoomId).getClass().getName().equals(clazz.getName());
    }

    @SuppressWarnings("unchecked")
    public <T> T getGame(Class<T> clazz, long gameRoomId) {
        if (hasGame(clazz, gameRoomId))
            return (T) gameSessionList.get(gameRoomId);
        return null;
    }
}
