package com.cholab.botaku.Tichu.Domain.Cards.Combination;

import com.cholab.botaku.Common.Card.Card;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public abstract class TichuCardCombination {
    protected boolean isBomb = false;

    protected HashMap<Double, Integer> cardsMap;
    protected List<Card> cards;
    TichuCardCombination(List<Card> cardList) throws Exception {
        this.cards = cardList;
        if(this.cards.size() != 1 && hasDragonAndDogAndBird()) {
            throw new IllegalArgumentException("카드 조합에 드래곤, 개, 새는 존재할 수 없습니다.");
        }
        sortCombination(this.cards);
    }
    public abstract boolean isGreaterThan(TichuCardCombination other);
    protected boolean isGreaterThanForOther(TichuCardCombination other) {
        if (this.isBomb || other.isBomb) {
            if (this.isBomb && !other.isBomb) return true;
            return this.getType() == TichuCardCombinationType.BOMB_STRAIGHT
                    && other.getType() == TichuCardCombinationType.BOMB_FOUR_CARD;
        }
        throw new IllegalArgumentException("다른 카드 조합끼리 비교할 수 없습니다.");
    }

    public abstract TichuCardCombinationType getType();
    private void sortCombination(List<Card> cardList) {
        Collections.sort(cardList);
    }

    protected void setCardsMap() {
        cardsMap = new HashMap<>();
        for (Card card : this.cards) {
            double nowIndex = card.getIndex();
            cardsMap.put(nowIndex, cardsMap.getOrDefault(nowIndex, 0) + 1);
        }
    }

    public boolean hasDragonAndDogAndBird() {
        long check = this.cards.stream()
                .filter(Card -> (Card.getIndex() == 15) && (Card.getIndex() == 0) && (Card.getIndex() == 1))
                .count();
        return check >= 1;
    }

    public boolean hasPhoenix() {
        return this.cards.get(0).getIndex() == -1;
    }

    public void setPhoenix(double index) {
        this.cards.get(0).setIndex(index);
    }
    public double getPhoenix() {
        return this.cards.get(0).getIndex();
    }
    public List<Card> getCards() {
        return cards;
    }
}
