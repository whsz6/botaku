package com.cholab.botaku.Utill.Json;

import com.cholab.botaku.Domain.Game.Library.Card.Card;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;

public class CardSerializer extends JsonSerializer<Card> {
    @Override
    public void serialize(Card value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("index", value.getIndex());
        gen.writeNumberField("score", value.getScore());
        if (value.getEmblem() != null)
            gen.writeStringField("emblem", value.getEmblem().getEmblemName());
        gen.writeEndObject();
    }
}
