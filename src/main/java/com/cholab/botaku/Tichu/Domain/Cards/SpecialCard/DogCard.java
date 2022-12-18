package com.cholab.botaku.Tichu.Domain.Cards.SpecialCard;

import com.cholab.botaku.Common.Card.Card;
import com.cholab.botaku.Common.Card.Emblem;

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