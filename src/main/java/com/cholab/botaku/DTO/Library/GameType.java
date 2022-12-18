package com.cholab.botaku.DTO.Library;

public enum GameType {
    TICHU("TICHU"), MIGHTY("MIGHTY");
    private final String gameTypeName;
    GameType(String gameTypeName) {
        this.gameTypeName = gameTypeName;
    }
    public String getGameTypeName() {
        return gameTypeName;
    }
}
