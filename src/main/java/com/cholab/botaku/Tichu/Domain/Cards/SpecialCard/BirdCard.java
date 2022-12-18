package com.cholab.botaku.Tichu.Domain.Cards.SpecialCard;

import com.cholab.botaku.Common.Card.Card;
import com.cholab.botaku.Common.Card.Emblem;

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
