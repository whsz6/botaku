package com.cholab.botaku.Service;

import com.cholab.botaku.Domain.Game.Library.Card.Card;
import com.cholab.botaku.Domain.Game.Library.Card.Emblem;
import com.cholab.botaku.Domain.Game.Tichu.Cards.Combination.TichuCardCombinationType;
import com.cholab.botaku.Domain.Game.Tichu.Tichu;
import com.cholab.botaku.Domain.Game.Tichu.TichuRound;
import com.cholab.botaku.Domain.Room.RoomPlayer;
import com.cholab.botaku.Usecase.Tichu.CardSendUseCase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static reactor.core.publisher.Mono.when;

@ExtendWith(MockitoExtension.class)
public class TichuCardSendServiceTest {
    @InjectMocks
    Tichu tichu;

    @Mock
    TichuRound tichuRound;

    @Mock
    private TichuCardSendService tichuCardSendService;

    @BeforeAll
    void initData() {
        String initGameData = "1:test,2:test2,3:test3,4:test4";
        tichu = new Tichu(111, initGameData);
    }

    // 테스트
    // 1. 보낸 카드들이 hand 에서 삭제되었는지, center Table 에 추가되었는지 확인
    @Test
    void _Success {
        // given

        // when

        // then
        CardSendUseCase.CardSendCommand command = new CardSendUseCase.CardSendCommand(
                new RoomPlayer(111, 2, "test2"),
                TichuCardCombinationType.THREE_CARD,
                new ArrayList<>(Arrays.asList(
                        new Card(3.0, 0, Emblem.SPADE),
                        new Card(3.0, 0, Emblem.CLOVER),
                        new Card(3.0, 0, Emblem.HEART)))
        );
        tichuCardSendService.sendCard()
    }
}
