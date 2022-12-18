package com.cholab.botaku.Tichu.Domain.Cards;

import com.cholab.botaku.Common.Card.Card;
import com.cholab.botaku.Common.Card.Emblem;

public class TichuCard extends Card {

    public TichuCard(Emblem emblem, int index) {
        this.setEmblem(emblem);
        this.setIndex(index);
        this.setScore(0);
        if(index == 5){
            this.setScore(5);
        }
        else if(index == 10){
            this.setScore(10);
        }
        else if(index == 13){
            this.setScore(10);
        }
    }

    @Override
    public String toString() {
        return this.getEmblem() + " " + this.getIndex();
    }
}
