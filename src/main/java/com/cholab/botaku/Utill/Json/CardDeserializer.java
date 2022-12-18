package com.cholab.botaku.Utill.Json;

import com.cholab.botaku.Domain.Game.Library.Card.Card;
import com.cholab.botaku.Domain.Game.Library.Card.Emblem;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class CardDeserializer extends JsonDeserializer<Card> {
    @Override
    public Card deserialize(JsonParser jp, DeserializationContext context) throws IOException, JacksonException {
        JsonNode dataNode = jp.getCodec().readTree(jp);

        double index = dataNode.get("index").asDouble();
        var score = dataNode.get("score").asInt();
        var emblem = dataNode.get("emblem").asText();

        return new Card(index, score, Emblem.valueOf(emblem));
    }
}
