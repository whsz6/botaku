package com.cholab.botaku.Domain.Room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomPlayer {
    private long roomId;
    private long playerId;
    private String playerName;
}
