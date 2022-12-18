package com.cholab.botaku.Tichu.Domain.Cards.Combination;

import com.cholab.botaku.Common.Card.Card;

import java.util.List;

public class FourCardBomb extends TichuCardCombination {
    public FourCardBomb(List<Card> cardList) throws Exception {
        super(cardList);
        verifyInput();
        this.isBomb = true;
    }
    private void verifyInput() {
        if(this.cards.size() != 4) {
            throw new IllegalArgumentException("카드의 갯수는 4개이어야 합니다.");
        }
        if(this.hasPhoenix()) {
            throw new IllegalArgumentException("포카드 폭탄에는 피닉스를 가지고 있을 수 없습니다.");
        }
        boolean duplicate = this.cards.stream().map(Card::getIndex).distinct().limit(2).count() == 1;
        if ( duplicate ){
            throw new IllegalArgumentException("포카드 폭탄은 연속된 세 숫자로 구성되어야 합니다.");
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
        return TichuCardCombinationType.BOMB_FOUR_CARD;
    }
}
