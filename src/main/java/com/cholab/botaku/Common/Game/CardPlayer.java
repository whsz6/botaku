package com.cholab.botaku.Common.Game;

import com.cholab.botaku.Common.Card.Card;

import java.util.List;

public interface CardPlayer {
    void addCardsToHands(List<Card> target);
    void removeCardsFromHands(List<Card> target);
    void addCardsToTable(List<Card> target);
    void removeCardsFromTable(List<Card> target);
}
