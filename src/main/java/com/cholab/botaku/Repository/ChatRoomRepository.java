package com.cholab.botaku.Repository;

import com.cholab.botaku.Domain.Room.Message;
import com.cholab.botaku.Domain.Room.Room;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Range;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
public class ChatRoomRepository {
    //private static final Logger LOGGER = LoggerFactory.getLogger(ChatRoomRepository.class);

    /* HASH -> roomId: Room */
    private static final String ROOM_INFO = "ROOM_INFO";
    /* ZSET -> timestamp: Message */
    private static final String ROOM_CHAT = "R%s_CHAT";

    private final ReactiveRedisTemplate<String, Message> messageTemplate;
    private final ReactiveRedisTemplate<String, Room> roomTemplate;

    public ChatRoomRepository(ReactiveRedisTemplate<String, Message> messageTemplate,
                              ReactiveRedisTemplate<String, Room> roomTemplate) {
        this.messageTemplate = messageTemplate;
        this.roomTemplate = roomTemplate;
    }

    public Mono<String> createRoom(String roomName) {
        Room imsiRoom = Room.create(roomName);
        log.debug(imsiRoom.getName());
        return roomTemplate.opsForHash()
                .put(ROOM_INFO, imsiRoom.getRoomId(), imsiRoom)
                .flatMap((bool) -> {
                    if(bool) {
                        return Mono.just(imsiRoom.getRoomId());
                    } else {
                        return Mono.empty();
                    }
                });
    }

    public Mono<Boolean> isRoomExists(String roomId) {
        ScanOptions scan = ScanOptions.scanOptions()
                .count(50).build();
        return roomTemplate.opsForHash()
                .scan(ROOM_INFO, scan)
                .any(entity -> {
                    String room = (String)entity.getKey();
                    return room.equals(roomId);
                })
                .hasElement()
                .switchIfEmpty(Mono.just(false));
    }

    public Mono<Boolean> deleteRoom(String roomId) {
        return messageTemplate.opsForHash()
                .get(ROOM_CHAT, roomId)
                .map(o -> o instanceof Room)
                .switchIfEmpty(Mono.just(false));
    }

    public Flux<String> getRoomInfo() {
        ScanOptions scanOption = ScanOptions.scanOptions()
                .count(50).build();
        return roomTemplate.opsForHash()
                .scan(ROOM_INFO, scanOption)
                .doOnNext((Entry) -> log.info("Room : {}", Entry.getValue()) )
                .map(entry -> ((Room)entry.getValue()).toString())
                .switchIfEmpty(Flux.empty());
    }

    public Flux<Message> getMessages(String roomId, long offset, long size){
        String roomKey = String.format(ROOM_CHAT, roomId);
        log.debug("[*] roomKey : {}", roomKey);
        return isRoomExists(roomId)
                .flatMapMany( exist -> {
                    if(exist) {
                        return messageTemplate.opsForZSet()
                                .reverseRange(roomKey, Range.closed(offset, offset + size))
                                .doOnNext((msg) -> log.debug("[*] message : {}", msg))
                                .doOnComplete(() -> log.debug(String.format("[*] Received messages by roomKey:%s, offset:%d, size:%d", roomKey, offset, size)));
                    }
                    return null;
                });
    }

    public Mono<Boolean> saveMessage(String roomId, String player, String toMessage) {
        Message message = new Message(roomId, player, toMessage, (double)System.currentTimeMillis());
        String roomKey = String.format(ROOM_CHAT, message.getRoomId());
        return isRoomExists(roomId)
                .flatMap( exist -> {
                    if(exist) {
                        return messageTemplate.opsForZSet()
                                .add(roomKey, message, message.getDate())
                                .doOnSuccess((result) -> log.debug(String.format("[*] Saved messages to %s, score:%s, message:%s, response:%s", roomKey, message.getDate(), message, result)));
                    } else {
                        return Mono.just(false);
                    }
                });
    }
}
