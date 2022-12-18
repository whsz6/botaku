package com.cholab.botaku.Tichu.DTO;

import com.cholab.botaku.Tichu.DTO.Library.GameType;
import com.cholab.botaku.Chat.DTO.RoomPlayer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SendGameRequest {
    private RoomPlayer fromPlayer;
    private GameType gameType;
    private String gameCommand;
    private Object payload;
}
