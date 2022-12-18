package com.cholab.botaku.Tichu.Domain;

import com.cholab.botaku.Common.Card.Card;
import com.cholab.botaku.Common.Card.Deck;
import com.cholab.botaku.Common.Card.Emblem;
import com.cholab.botaku.Tichu.Domain.Cards.Combination.TichuCardCombination;
import com.cholab.botaku.Tichu.Domain.Cards.SpecialCard.BirdCard;
import com.cholab.botaku.Tichu.Domain.Cards.SpecialCard.DogCard;
import com.cholab.botaku.Tichu.Domain.Cards.SpecialCard.DragonCard;
import com.cholab.botaku.Tichu.Domain.Cards.SpecialCard.PhoenixCard;
import com.cholab.botaku.Tichu.Domain.Cards.TichuCard;
import lombok.Data;

import java.util.*;

@Data
public class TichuDeck implements Deck {
    private TichuCardCombination top;
    private List<Card> deck;

    TichuDeck() {
        this.deck = new ArrayList<>();
        initDeck();
    }

    private void initDeck() {
        addCardWithEmblemOnDeck(Emblem.HEART);
        addCardWithEmblemOnDeck(Emblem.DIAMOND);
        addCardWithEmblemOnDeck(Emblem.CLOVER);
        addCardWithEmblemOnDeck(Emblem.SPADE);
        this.deck.add(new DragonCard());
        this.deck.add(new PhoenixCard());
        this.deck.add(new DogCard());
        this.deck.add(new BirdCard());
    }

    private void addCardWithEmblemOnDeck(Emblem emblem) {
        for (int i = 2; i < 15; i++) {
            this.deck.add(new TichuCard(emblem, i));
        }
    }

    private Optional<Card> drawCardFromDeck() {
        if (this.deck.size() > 0) {
            Random generator = new Random();
            int index = generator.nextInt(this.deck.size());
            return Optional.ofNullable(this.deck.remove(index));
        }
        return Optional.<Card>empty();
    }

    @Override
    public ArrayList<Card> drawCardListFromDeck(int times) {
        ArrayList<Card> cardList = new ArrayList<>(times);
        for(int i=0; i<times; i++) {
            drawCardFromDeck().ifPresent(cardList::add);
        }
        return cardList;
    }

    @Override
    public boolean cardExistInDeck(Card target) {
        return this.deck.stream().anyMatch(card -> (Objects.equals(card.getEmblem(), target.getEmblem()) && card.getIndex() == target.getIndex()));
    }
}
