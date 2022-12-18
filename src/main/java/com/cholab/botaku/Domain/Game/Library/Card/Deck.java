package com.cholab.botaku.Domain.Game.Library.Card;

import java.util.ArrayList;

public interface Deck {
    ArrayList<Card> drawCardListFromDeck(int times);
    boolean cardExistInDeck(Card target);
}
