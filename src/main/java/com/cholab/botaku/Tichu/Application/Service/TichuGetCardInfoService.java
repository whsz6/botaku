package com.cholab.botaku.Tichu.Application.Service;

import com.cholab.botaku.Common.Card.Card;
import com.cholab.botaku.Tichu.Application.Port.Out.LoadGamePort;
import com.cholab.botaku.Tichu.Domain.Tichu;
import com.cholab.botaku.Tichu.Application.Port.In.GetCardInfoUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Slf4j
@RequiredArgsConstructor
@Service
public class TichuGetCardInfoService implements GetCardInfoUseCase {

    private final LoadGamePort loadGamePort;
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public String getCardInfo(GetCardInfoCommand command) {
        Tichu tichu = loadGamePort.getGame(Tichu.class, command.getFromPlayer().getRoomId());
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
