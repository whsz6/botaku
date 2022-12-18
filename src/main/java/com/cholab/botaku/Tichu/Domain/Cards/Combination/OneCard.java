package com.cholab.botaku.Tichu.Domain.Cards.Combination;

import com.cholab.botaku.Common.Card.Card;

import java.util.List;

public class OneCard extends TichuCardCombination {
    public OneCard(List<Card> cardList) throws Exception {
        super(cardList);
        if(this.cards.size() != 1) {
            throw new IllegalArgumentException("카드의 갯수는 항상 1개 이어야 합니다.");
        }
        if (!(this.cards.get(0).getIndex() >= -1 && this.cards.get(0).getIndex() <= 15)) {
            throw new IllegalArgumentException("카드의 인덱스가 잘못되었습니다.");
        }
    }
    @Override
    public boolean isGreaterThan(TichuCardCombination other) {
        double myCardIndex = this.cards.get(0).getIndex();
        double otherCardIndex = other.cards.get(0).getIndex();
        if (myCardIndex == 0 || otherCardIndex == 0) {
            throw new IllegalArgumentException("개 카드는 비교할 수 없습니다.");
        }
        if (myCardIndex == -1) {
            return otherCardIndex != 15;
        } else if (otherCardIndex == -1) {
            return myCardIndex != 15;
        }
        return this.cards.get(0).getIndex() > other.cards.get(0).getIndex();
    }

    @Override
    public TichuCardCombinationType getType() {
        return TichuCardCombinationType.ONE_CARD;
    }
}
