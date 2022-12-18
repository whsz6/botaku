package com.cholab.botaku.Usecase.Tichu;

import com.cholab.botaku.DTO.TichuCommand;
import com.cholab.botaku.Domain.Room.RoomPlayer;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

public interface GameStartUseCase {
    boolean gameStart(GameStartCommand command);
    @Data
    class GameStartCommand {
        private final TichuCommand subCommand = TichuCommand.GAME_START;
        @NotNull
        private final RoomPlayer fromPlayer;
        @NotNull
        private final String gameInitData;
        public GameStartCommand(@NotNull RoomPlayer fromPlayer, @NotNull String gameInitData) {
            this.fromPlayer = fromPlayer;
            this.gameInitData = gameInitData;
        }
    }
}
