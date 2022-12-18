package com.cholab.botaku.Tichu.Application.Service;

import com.cholab.botaku.Tichu.Domain.Tichu;
import com.cholab.botaku.Tichu.Domain.TichuPlayer;
import com.cholab.botaku.Tichu.Domain.TichuRound;
import com.cholab.botaku.Tichu.Application.Port.Out.LoadGamePort;
import com.cholab.botaku.Tichu.Application.Port.In.CardSendUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public record TichuCardSendService(LoadGamePort loadGamePort) implements CardSendUseCase {

    // 0. 현재 플레이어가 지금 카드를 낼 순서가 맞는지 확인한다.
    // 1. 카드가 현재 플레이어 손에 있는 카드리스트에 존재하는지 체크한다.
    // 2. 카드가 현재 덱 상단에 있는 족보와 같은 족보인지 체크한다. (갯수 족보)
    // 3. 플레이어 손에서 카드들을 제거한다.
    // 4. 덱에 카드들을 추가하고 덱 상단에 있는 족보를 업데이트한다.
    // 5. 다음 차례 플레이어를 업데이트한다.
    @Override
    public boolean sendCard(CardSendCommand command) {
        Tichu tichu = loadGamePort.getGame(Tichu.class, command.getFromPlayer().getRoomId());
        if (tichu == null)
            return false;
        TichuRound tichuRound = tichu.getRound();
        TichuPlayer currentPlayer = tichu.getRound().getCurrentPlayer();
        if (currentPlayer.getId() != command.getFromPlayer().getPlayerId() || !currentPlayer.getName().equals(command.getFromPlayer().getPlayerName()))
            return false;
        if (!currentPlayer.verifyCardListInHand(command.getCardList()))
            return false;
        if (!command.getCombination().isGreaterThan(tichuRound.getDeck().getTop()))
            return false;
        currentPlayer.removeCardsFromHands(command.getCardList());
        tichuRound.getDeck().setTop(command.getCombination());
        tichuRound.setNextPlayer();
        return true;
    }
}
