package com.cholab.botaku.Usecase.Tichu;

import com.cholab.botaku.DTO.TichuCommand;
import com.cholab.botaku.Domain.Room.RoomPlayer;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

public interface GetCardInfoUseCase {
    String getCardInfo(GetCardInfoCommand command);
    @Data
    class GetCardInfoCommand {
        private final TichuCommand subCommand = TichuCommand.GAME_INFO;
        @NotNull
        private final RoomPlayer fromPlayer;
        public GetCardInfoCommand(RoomPlayer fromPlayer) {
            this.fromPlayer = fromPlayer;
        }
    }
}
