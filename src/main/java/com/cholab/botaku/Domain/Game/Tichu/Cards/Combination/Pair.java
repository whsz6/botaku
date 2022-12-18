package com.cholab.botaku.Domain.Game.Tichu.Cards.Combination;

import com.cholab.botaku.Domain.Game.Library.Card.Card;

import java.util.ArrayList;
import java.util.List;

public class Pair extends TichuCardCombination {
    public Pair(List<Card> cardList) throws Exception {
        super(cardList);
        verifyInput();
    }
    private void verifyInput() {
        if(this.cards.size() != 2) {
            throw new IllegalArgumentException("카드의 갯수는 항상 2개 이어야 합니다.");
        }
        if (hasPhoenix()) {
            setPhoenix(this.cards.get(1).getIndex());
        } else if (this.cards.get(0).getIndex() != this.cards.get(1).getIndex()) {
            throw new IllegalArgumentException("원 페어는 연속된 두 숫자로 구성되어야 합니다.");
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
        return TichuCardCombinationType.PAIR;
    }
}
