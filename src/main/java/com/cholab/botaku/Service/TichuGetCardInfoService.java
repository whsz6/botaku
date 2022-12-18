package com.cholab.botaku.Service;

import com.cholab.botaku.Domain.Game.Library.Card.Card;
import com.cholab.botaku.Domain.Game.Tichu.Tichu;
import com.cholab.botaku.Usecase.Tichu.GetCardInfoUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class TichuGetCardInfoService implements GetCardInfoUseCase {

    @Autowired
    GameMap gameMap;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public String getCardInfo(GetCardInfoCommand command) {
        Tichu tichu = ((Tichu) gameMap.getGame(command.getFromPlayer().getRoomId()));
        if (tichu == null)
            return "";
        Map<Long, List<Card>> playersCard = tichu.getRound().getPlayersCard();
        try {
            return objectMapper.writeValueAsString(playersCard);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
