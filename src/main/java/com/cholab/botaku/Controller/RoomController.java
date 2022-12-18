package com.cholab.botaku.Controller;
import com.cholab.botaku.Repository.ChatRoomRepository;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class RoomController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoomController.class);

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @GetMapping(value = "/rooms/")
    public Flux<String> getRoomsList() {
        return chatRoomRepository.getRoomInfo()
                .doOnNext(data -> log.info("rooms data : {}", data))
                .map((room) -> {
                    if(room != null) {
                        return String.format("%s 방 -> ", room);
                    } else {
                        return "아직 방이 존재하지 않습니다.";
                    }
                })
                .doOnNext((data)->log.info("수정 후 rooms data : {}", data))
                .doOnComplete(() ->log.info("끝"))
                .log();
    }

    @PostMapping(value = "/room/", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Mono<String> createRoom(
            @RequestBody(required = false) String roomName) {
        return chatRoomRepository.createRoom(roomName)
                .flatMap((room) -> Mono.just(String.format("%s id 로 방이 생성되었습니다.", room)))
                .switchIfEmpty(Mono.just("예기치 않은 오류로 생성되지 못 했습니다."))
                .log();
    }

    @DeleteMapping(value = "/room/{roomId}", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Mono<Boolean> deleteRoom(@PathVariable String roomId) {
        return chatRoomRepository.deleteRoom(roomId).log();
    }
}
