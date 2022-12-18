package com.cholab.botaku.Common.Card;

import java.util.ArrayList;

public interface Deck {
    ArrayList<Card> drawCardListFromDeck(int times);
    boolean cardExistInDeck(Card target);
}
