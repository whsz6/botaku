package com.cholab.botaku.Tichu.Application.Port.In;

import com.cholab.botaku.Tichu.DTO.TichuCommand;
import com.cholab.botaku.Chat.DTO.RoomPlayer;
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
