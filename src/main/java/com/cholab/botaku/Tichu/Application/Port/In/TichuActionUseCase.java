package com.cholab.botaku.Tichu.Application.Port.In;

import com.cholab.botaku.Tichu.DTO.TichuCommand;
import com.cholab.botaku.Chat.DTO.RoomPlayer;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

public interface TichuActionUseCase {
    boolean doAction(TichuActionCommand command);
    @Data
    class TichuActionCommand {
        @NotNull
        private final TichuCommand command;
        @NotNull
        private final RoomPlayer fromPlayer;
        @NotNull
        private final Object payload;
        public TichuActionCommand(@NotNull TichuCommand command, @NotNull RoomPlayer fromPlayer, @NotNull Object payload) {
            this.command = command;
            this.fromPlayer = fromPlayer;
            this.payload = payload;
        }
    }
}
