package com.cholab.botaku.Domain.Game.Tichu.Cards.SpecialCard;

import com.cholab.botaku.Domain.Game.Library.Card.Card;
import com.cholab.botaku.Domain.Game.Library.Card.Emblem;

public class BirdCard extends Card {
    private int wishNumber;

    public int getWishNumber() {
        return wishNumber;
    }

    public void setWishNumber(int wishNumber) {
        this.wishNumber = wishNumber;
    }

    public BirdCard(){
        this.setScore(0);
        this.setIndex(1);
        this.setEmblem(Emblem.NONE);
    }

    @Override
    public String toString(){
        return "Bird";
    }
}
