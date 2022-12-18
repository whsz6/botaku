package com.cholab.botaku.Chat.Adapter.In.Web;

import com.cholab.botaku.Chat.DTO.Message;
import com.cholab.botaku.Chat.DTO.RecvChatRequest;
import com.cholab.botaku.Chat.DTO.SendChatRequest;
import com.cholab.botaku.Tichu.Adapter.Out.Persistence.ChatRoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.annotation.ConnectMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

// RSocketRequester vs RSocketRequestBuilder vs RSocketFactory
// RSocketFactory 는 io.rsocket 소속. RSocketRequester 는 org.springframework 소속.
// RSocketRequester 가 내부적으로 RSocketFactory 를 사용한다고 함.
@Slf4j
@Controller
public class MessageController {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @ConnectMapping
    public void onConnect(RSocketRequester rSocketRequester) {
        Objects.requireNonNull(rSocketRequester.rsocket(), "rSocket should not be null")
                .onClose()
                .doOnError(error -> log.warn(rSocketRequester.rsocketClient() + " Closed"))
                .doFinally(consumer -> log.info(rSocketRequester.rsocketClient() + " Disconnected"))
                .subscribe();
    }

    @MessageMapping("chat.send")
    public Mono<Boolean> sendChatMessage(@Payload SendChatRequest messageRequest) {
        log.debug("[*] chat.send 진입");
        return chatRoomRepository.saveMessage(
                messageRequest.getRoomId(),
                messageRequest.getFromPerson(),
                messageRequest.getMessage()).log();
    }

    @MessageMapping("chat.recv")
    public Flux<Message> receiveChatMessage(@Payload RecvChatRequest recvChatRequest) {
        log.debug("[*] chat.recv 진입");
        log.debug("[*] roomId : {}", recvChatRequest.getRoomId());
        return chatRoomRepository.getMessages(
                recvChatRequest.getRoomId(),
                recvChatRequest.getOffset(),
                recvChatRequest.getSize()).log();
    }
}
