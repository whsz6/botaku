package com.cholab.botaku.Domain.Game;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
public abstract class Game {
    protected long gameId;
    protected String initGameData;
}
