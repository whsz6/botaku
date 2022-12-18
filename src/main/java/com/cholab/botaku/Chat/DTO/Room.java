package com.cholab.botaku.Chat.DTO;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Room implements Serializable {
    private String roomId;
    private String name;
    private ArrayList<String> playList;

    public static Room create(String name) {
        Room room = new Room();
        room.roomId = UUID.randomUUID().toString();
        room.name = name;
        room.playList = new ArrayList<String>();
        return room;
    }
}
