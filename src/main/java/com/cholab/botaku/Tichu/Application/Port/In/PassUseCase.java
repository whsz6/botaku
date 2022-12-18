package com.cholab.botaku.Tichu.Application.Port.In;

import com.cholab.botaku.Tichu.DTO.TichuCommand;
import com.cholab.botaku.Chat.DTO.RoomPlayer;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

public interface PassUseCase {
    boolean pass(PassCommand command);
    @Data
    class PassCommand {
        private final TichuCommand subCommand = TichuCommand.PASS;
        @NotNull
        private final RoomPlayer fromPlayer;
        public PassCommand(RoomPlayer fromPlayer) {
            this.fromPlayer = fromPlayer;
        }
    }
}
