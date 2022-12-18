package com.cholab.botaku.Tichu.Domain.Cards.SpecialCard;

import com.cholab.botaku.Common.Card.Card;
import com.cholab.botaku.Common.Card.Emblem;

public class PhoenixCard extends Card {
    public PhoenixCard(){
        this.setScore(-25);
        this.setIndex(-1);
        this.setEmblem(Emblem.NONE);
    }
    @Override
    public String toString(){
        return "Phoenix";
    }
}
