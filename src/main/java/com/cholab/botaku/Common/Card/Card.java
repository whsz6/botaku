package com.cholab.botaku.Common.Card;

import lombok.*;
@Setter
@Getter
@AllArgsConstructor
public class Card implements Comparable<Card> {
    // 카드 순서
    // J : 11, Q : 12, K : 13, A : 14, Dragon : 15
    private double index;
    // 카드 점수
    private int score;
    // 카드 앰블럼
    private Emblem emblem;

    public Card() {
        this.index = 0;
        this.score = 0;
    }

    @Override
    public int compareTo(Card target) {
        return Double.compare(this.index, target.index);
    }
}
