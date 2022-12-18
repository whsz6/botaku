package com.cholab.botaku.Tichu.Domain.Cards.Combination;

import com.cholab.botaku.Common.Card.Card;

import java.util.List;

public class ThreeCard extends TichuCardCombination {
    public ThreeCard(List<Card> cardList) throws Exception {
        super(cardList);
        verifyInput();
    }
    private void verifyInput() {
        if(this.cards.size() != 3) {
            throw new IllegalArgumentException("카드의 갯수는 항상 3개 이어야 합니다.");
        }
        if (hasPhoenix()) {
            setPhoenix(this.cards.get(1).getIndex());
        }
        boolean duplicate = this.cards.stream().map(Card::getIndex).distinct().limit(2).count() == 1;
        if ( duplicate ){
            throw new IllegalArgumentException("쓰리카드는 연속된 세 숫자로 구성되어야 합니다.");
        }
    }
    @Override
    public boolean isGreaterThan(TichuCardCombination other) {
        int mySize = this.cards.size();
        int otherSize = other.cards.size();
        if (mySize == otherSize) {
            return this.cards.get(mySize - 1).getIndex() > other.cards.get(otherSize - 1).getIndex();
        }
        return false;
    }

    @Override
    public TichuCardCombinationType getType() {
        return TichuCardCombinationType.THREE_CARD;
    }
}
