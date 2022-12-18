package com.cholab.botaku.Chat.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecvChatRequest {
    private long roomId;
    private long offset;
    private long size;
}
