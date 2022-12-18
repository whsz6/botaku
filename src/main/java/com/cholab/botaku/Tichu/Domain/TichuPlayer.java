package com.cholab.botaku.Tichu.Domain;

import com.cholab.botaku.Common.Card.Card;
import com.cholab.botaku.Common.Game.CardPlayer;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TichuPlayer implements CardPlayer {
    private boolean isActive;
    private long id;
    private String name;
    private int score;
    private List<Card> hands;
    private List<Card> table;
    private boolean saidSmallTichu;
    private boolean saidLargeTichu;
    private boolean saidBird;

    public TichuPlayer(long id, String name) {
        this.id=id;
        this.name=name;
        this.hands = new ArrayList<Card>();
        this.table =new ArrayList<Card>();
        this.saidSmallTichu=false;
        this.saidLargeTichu=false;
        this.saidBird=false;
        this.score=0;
    }

    @Override
    public void addCardsToHands(List<Card> target) { this.hands.addAll(target); }

    @Override
    public void removeCardsFromHands(List<Card> target) {
        this.hands.removeAll(target);
    }

    @Override
    public void addCardsToTable(List<Card> target) {
        this.table.addAll(target);
    }

    @Override
    public void removeCardsFromTable(List<Card> target) {
        this.table.removeAll(target);
    }

//    public ArrayList<Card> doSendCards(TichuCardCombination myCards, TichuCardCombination tableTopCards, int wishNumber) {
//        boolean bigger = false;
//        if (myCards.getType() == TichuCardCombinationType.BOMB)
//        if (myCards.getType() == tableTopCards.getType())
//        {
//            bigger = myCards.isGreaterThan(tableTopCards);
//        }
//        removeCardsFromHands(myCards.getCards());
//        return cards.getCards();
//    }

    public boolean verifyCardListInHand(List<Card> cardList) {
        return hands.equals(cardList);
    }

    public void doPass() {

    }

    public boolean haveWishCardWithValue(float wish) {
        return this.hands.stream()
                .anyMatch(card -> card.getIndex() == wish);
    }

    public void sayLargeTichu() { this.saidLargeTichu = true; }
    public void saySmallTichu() { this.saidSmallTichu = true; }
    public void sayBird() { this.saidBird = true; }
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
}
