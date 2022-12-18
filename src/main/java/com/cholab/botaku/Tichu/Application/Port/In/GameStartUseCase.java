package com.cholab.botaku.Tichu.Application.Port.In;

import com.cholab.botaku.Tichu.DTO.TichuCommand;
import com.cholab.botaku.Chat.DTO.RoomPlayer;
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
