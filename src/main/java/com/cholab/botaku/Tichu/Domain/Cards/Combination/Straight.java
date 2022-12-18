package com.cholab.botaku.Tichu.Domain.Cards.Combination;

import com.cholab.botaku.Common.Card.Card;

import java.util.List;

public class Straight extends TichuCardCombination {

    public Straight(List<Card> cardList) throws Exception {
        super(cardList);
        verifyInput();
    }
    private void verifyInput() {
        if(this.cards.size() < 5) {
            throw new IllegalArgumentException("카드의 갯수는 5개 이상 이어야 합니다.");
        }

        // 초기화
        int index = 0;
        double expect = 0;
        boolean phoenix = false;
        if(hasPhoenix()) {
            index = 1;
            phoenix = true;
        }

        while (index < this.cards.size()) {
            double now = this.cards.get(index).getIndex();
            if (expect == 0) {
                index++;
                expect = now + 1;
                continue;
            }
            if (phoenix) {
                if (expect != now) {
                    phoenix = false;
                    setPhoenix(expect);
                    expect+=1;
                    continue;
                }
            } else {
                if (expect != now) {
                    throw new IllegalArgumentException("스트레이트는 카드 숫자가 연속이어야 합니다.");
                }
            }
            index++;
            expect+=1;
        }
        if (phoenix)
            setPhoenix(expect);
    }
    @Override
    public boolean isGreaterThan(TichuCardCombination other) {
        int mySize = this.cards.size();
        int otherSize = other.cards.size();
        if (mySize == otherSize) {
            if (this.cards.get(mySize-1).getIndex() > other.cards.get(otherSize-1).getIndex()) {
                return true;
            }
            else if (this.cards.get(mySize-1).getIndex() == other.cards.get(otherSize-1).getIndex()) {
                return this.cards.get(0).getIndex() > other.cards.get(0).getIndex();
            }
        }
        return false;
    }

    @Override
    public TichuCardCombinationType getType() {
        return TichuCardCombinationType.STRAIGHT;
    }
}
