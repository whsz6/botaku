package com.cholab.botaku.Domain.Game.Tichu.Cards.SpecialCard;

import com.cholab.botaku.Domain.Game.Library.Card.Card;
import com.cholab.botaku.Domain.Game.Library.Card.Emblem;

public class DragonCard extends Card {
    public DragonCard(){
        this.setScore(25);
        // A 보다 높아야 하므로 15
        this.setIndex(15);
        this.setEmblem(Emblem.NONE);
    }
    @Override
    public String toString(){
        return "Dragon";
    }
}
