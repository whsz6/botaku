package com.cholab.botaku.Chat.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message {
    private String roomId;
    private String fromPerson;
    private String message;
    private double date;
}
