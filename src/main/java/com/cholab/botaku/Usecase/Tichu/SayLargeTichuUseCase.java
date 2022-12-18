package com.cholab.botaku.Usecase.Tichu;

import com.cholab.botaku.DTO.TichuCommand;
import com.cholab.botaku.Domain.Room.RoomPlayer;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

public interface SayLargeTichuUseCase {
    boolean sayLargeTichu(SayLargeTichuCommand command);
    @Data
    class SayLargeTichuCommand {
        private final TichuCommand subCommand = TichuCommand.SAY_LARGE_TICHU;
        @NotNull
        private final RoomPlayer fromPlayer;
        public SayLargeTichuCommand(RoomPlayer fromPlayer) {
            this.fromPlayer = fromPlayer;
        }
    }
}
