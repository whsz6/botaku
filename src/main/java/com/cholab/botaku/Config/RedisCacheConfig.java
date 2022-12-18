package com.cholab.botaku.Config;

import com.cholab.botaku.Domain.Room.Message;
import com.cholab.botaku.Domain.Room.Room;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.listener.ReactiveRedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisCacheConfig {
    @Bean
    public ReactiveRedisTemplate<String, Message> initializeMessageReactiveRedisTemplate(ReactiveRedisConnectionFactory factory) {
        RedisSerializationContext<String, Message> serializationContext = RedisSerializationContext
                .<String, Message>newSerializationContext(new StringRedisSerializer())
                .key(new StringRedisSerializer())
                .value(new Jackson2JsonRedisSerializer<>(Message.class))
                .hashKey(new Jackson2JsonRedisSerializer<>(String.class))
                .hashValue(new Jackson2JsonRedisSerializer<>(Message.class))
                .build();
        return new ReactiveRedisTemplate<>(factory, serializationContext);
    }

    @Bean
    public ReactiveRedisTemplate<String, Room> initializeRoomReactiveRedisTemplate(ReactiveRedisConnectionFactory factory) {
        RedisSerializationContext<String, Room> serializationContext = RedisSerializationContext
                .<String, Room>newSerializationContext(new StringRedisSerializer())
                .key(new StringRedisSerializer())
                .value(new Jackson2JsonRedisSerializer<>(Room.class))
                .hashKey(new Jackson2JsonRedisSerializer<>(String.class))
                .hashValue(new Jackson2JsonRedisSerializer<>(Room.class))
                .build();
        return new ReactiveRedisTemplate<>(factory, serializationContext);
    }

    @Bean
    ReactiveRedisMessageListenerContainer initializeReactiveRedisMessageListenerContainer(ReactiveRedisConnectionFactory factory) {
        return new ReactiveRedisMessageListenerContainer(factory);
    }
}
