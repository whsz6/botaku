package com.cholab.botaku.Tichu.Domain.Cards.Combination;

import com.cholab.botaku.Common.Card.Card;

import java.util.List;

public class MultiPair extends TichuCardCombination {
    public MultiPair(List<Card> cardList) throws Exception {
        super(cardList);
        verifyInput();
    }
    private void verifyInput() {
        if(this.cards.size() % 2 == 1) {
            throw new IllegalArgumentException("카드의 갯수는 짝수 개이어야 합니다.");
        }
        boolean turn = false;
        double expect = 0;
        int index = 0;
        boolean phoenix = false;
        if(hasPhoenix()) {
            index = 1;
            phoenix = true;
        }
        while (index < this.cards.size()) {
            double now = this.cards.get(index).getIndex();
            if (expect == 0) {
                index++;
                expect = now;
                continue;
            }
            if (turn) {
                turn = false;
            } else {
                if (now == expect) {
                    turn = true;
                    expect++;
                } else if (phoenix && now == expect + 1) {
                    setPhoenix(expect);
                    phoenix = false;
                    expect++;
                } else {
                    throw new IllegalArgumentException("멀티페어는 연속된 페어로 구성되어야 합니다.");
                }
            }
            index++;
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
        return TichuCardCombinationType.MULTI_PAIR;
    }
}
