package com.cholab.botaku.Config.Json;

import com.cholab.botaku.Common.Card.Card;
import com.cholab.botaku.Common.Util.Json.CardDeserializer;
import com.cholab.botaku.Common.Util.Json.CardSerializer;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.fasterxml.jackson.databind.type.ClassKey;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class TichuJacksonModule extends Module {
    private static class TichuDeserializers extends Deserializers.Base {
        final Map<ClassKey, JsonDeserializer<?>> cache = new ConcurrentHashMap<>();

        @Override
        public JsonDeserializer<?> findBeanDeserializer(JavaType type, DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {
            if (Card.class.isAssignableFrom(type.getRawClass())) {
                JsonDeserializer<?> cardDeserializer = new CardDeserializer();
                addDeserializer(type.getRawClass(), cardDeserializer);
                return cardDeserializer;
            }
            return null;
        }

        @Override
        public boolean hasDeserializerFor(DeserializationConfig config, Class<?> valueType) {
            return cache.containsKey(new ClassKey(valueType));
        }

        public void addDeserializer(Class<?> forClass, JsonDeserializer<?> deserializer) {
            ClassKey key = new ClassKey(forClass);
            cache.put(key, deserializer);
        }
    }
    private static class TichuSerializers extends Serializers.Base {
        final Map<ClassKey, JsonSerializer<?>> cache = new ConcurrentHashMap<>();

        @Override
        public JsonSerializer<?> findSerializer(SerializationConfig config, JavaType type, BeanDescription beanDesc) {
            if (Card.class.isAssignableFrom(type.getRawClass())) {
                ClassKey classKey = new ClassKey(type.getRawClass());
                if (cache.containsKey(classKey))
                    return cache.get(classKey);
                JsonSerializer<?> cardSerializer = new CardSerializer();
                addSerializer(type.getRawClass(), cardSerializer);
                return cardSerializer;
            }
            return null;
        }
        public void addSerializer(Class<?> forClass, JsonSerializer<?> serializer) {
            ClassKey key = new ClassKey(forClass);
            cache.put(key, serializer);
        }
    }
    @Override
    public String getModuleName() {
        return "Tichu";
    }

    @Override
    public Version version() {
        return Version.unknownVersion();
    }

    @Override
    public void setupModule(SetupContext context) {
        context.addDeserializers(new TichuDeserializers());
        context.addSerializers(new TichuSerializers());
    }
}
