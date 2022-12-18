package com.cholab.botaku.Usecase.Tichu;

import com.cholab.botaku.DTO.TichuCommand;
import com.cholab.botaku.Domain.Room.RoomPlayer;
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
