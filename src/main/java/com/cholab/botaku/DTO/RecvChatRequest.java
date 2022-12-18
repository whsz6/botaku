package com.cholab.botaku.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecvChatRequest {
    private long roomId;
    private long offset;
    private long size;
}
