package com.cholab.botaku.Tichu.Application.Port.Out;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public interface LoadGamePort {
    public <T> boolean createGame(Class<T> clazz, long gameRoomId, String initGameData);
    public <T> boolean hasGame(Class<T> clazz, long gameRoomId);
    public <T> T getGame(Class<T> clazz, long gameRoomId);
}
