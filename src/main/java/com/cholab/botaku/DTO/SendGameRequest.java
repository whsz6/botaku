package com.cholab.botaku.DTO;

import com.cholab.botaku.DTO.Library.GameType;
import com.cholab.botaku.Domain.Room.RoomPlayer;
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
