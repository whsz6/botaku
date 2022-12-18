package com.cholab.botaku.Domain.Game.Tichu.Cards.SpecialCard;

import com.cholab.botaku.Domain.Game.Library.Card.Card;
import com.cholab.botaku.Domain.Game.Library.Card.Emblem;

public class DogCard extends Card {
    public DogCard(){
        this.setScore(0);
        this.setIndex(0);
        this.setEmblem(Emblem.NONE);
    }
    @Override
    public String toString(){
        return "Dog";
    }
}