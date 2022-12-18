package com.cholab.botaku.Domain.Game.Tichu.Cards.SpecialCard;

import com.cholab.botaku.Domain.Game.Library.Card.Card;
import com.cholab.botaku.Domain.Game.Library.Card.Emblem;

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
