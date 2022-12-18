package com.cholab.botaku.Tichu.Domain.Cards.Combination;

import com.cholab.botaku.Common.Card.Card;

import java.util.*;
import java.util.stream.Collectors;

public class FullHouse extends TichuCardCombination {
    public FullHouse(List<Card> cardList) throws Exception {
        super(cardList);
        verifyInput();
    }
    private void verifyInput() {
        if(this.cards.size() != 5) {
            throw new IllegalArgumentException("카드의 갯수는 5개이어야 합니다.");
        }
        int turn = 0;
        int count = 0;
        HashMap<Double, Integer> countMap = new HashMap<>();
        double expect = 0;
        int index = 0;
        boolean phoenix = false;
        if(hasPhoenix()) {
            index = 1;
            phoenix = true;
        }
        while (index < this.cards.size()) {
            double now = this.cards.get(index).getIndex();
            if (expect == 0) {
                count = 1;
                index++;
                expect = now;
                continue;
            }
            if (now != expect) {
                turn += 1;
                if (turn >= 2) {
                    throw new IllegalArgumentException("풀하우스는 쓰리카드와 페어로 구성해야 합니다.");
                }
                countMap.put(expect, count);
                expect = now;
                count = 0;
            }
            if (index == this.cards.size()-1) {
                countMap.put(now, count + 1);
            }
            count++;
            index++;
        }
        double firstKey = 0, firstValue = 0;
        ArrayList<Double> countMapkeyList = new ArrayList<Double>(countMap.keySet());
        if (phoenix) {
            for(int i = 0; i < countMap.size(); i++) {
                if (firstKey == 0 && firstValue == 0) {
                    firstKey = countMapkeyList.get(i);
                    firstValue = countMap.get(firstKey);
                    if (firstValue == 4)
                        throw new IllegalArgumentException("풀하우스는 피닉스 포함 시 포카드를 가질 수 없습니다.");
                    continue;
                }
                double secondKey = countMapkeyList.get(i);
                int secondValue = countMap.get(secondKey);
                if (secondValue > firstValue) {
                    setPhoenix(firstKey);
                } else if (secondValue == firstValue && secondKey < firstKey) {
                    setPhoenix(firstKey);
                } else {
                    setPhoenix(secondKey);
                }
            }
        } else {
            countMap.values().stream()
                    .filter(v -> v == 4)
                    .findAny()
                    .ifPresent(v -> {throw new IllegalArgumentException("풀하우스는 포카드로 구성되어 있지 않습니다.");});

            int keySize = countMap.size();
            int valueSize = countMap.values().stream().mapToInt(i -> i).sum();
            if (keySize != 2 || valueSize != 5) {
                throw new IllegalArgumentException("풀하우스는 카드의 갯수가 5개이고 쓰리카드와 페어로 구성되어 있이야 합니다.");
            }
        }
    }
    @Override
    public boolean isGreaterThan(TichuCardCombination other) {
        Map<Double, Long> myThreeCountMap = this.cards.stream()
                .collect(Collectors.groupingBy(Card::getIndex, Collectors.counting()));
        double myThreeCount = myThreeCountMap.entrySet().stream()
                .filter(e -> e.getValue() == 3)
                .map(Map.Entry::getKey)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("비교 대상이 풀하우스가 아닙니다."));

        Map<Double, Long> otherThreeCountMap = other.cards.stream()
                .collect(Collectors.groupingBy(Card::getIndex, Collectors.counting()));
        double otherThreeCount = otherThreeCountMap.entrySet().stream()
                .filter(e -> e.getValue() == 3)
                .map(Map.Entry::getKey)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("비교 대상이 풀하우스가 아닙니다."));

        return myThreeCount > otherThreeCount;
    }

    @Override
    public TichuCardCombinationType getType() {
        return TichuCardCombinationType.FULL_HOUSE;
    }
}
