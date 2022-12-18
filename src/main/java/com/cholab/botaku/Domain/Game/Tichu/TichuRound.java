package com.cholab.botaku.Domain.Game.Tichu;

import com.cholab.botaku.Domain.Game.Library.Card.Card;
import com.cholab.botaku.Domain.Game.Library.Game.CardRound;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

@Data
public class TichuRound implements CardRound {
    private ArrayList<TichuPlayer> gamePlayers;
    private TichuPlayer currentPlayer;
    private TichuDeck deck;
    private ArrayList<Card> centerTable;
    private int wishNumber;
    public TichuRound(ArrayList<TichuPlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
        this.deck = new TichuDeck();
        this.centerTable = new ArrayList<>();
        this.wishNumber = 0;
        initRound();
    }
    private void initRound() {
        initializePlayerWithEightCard();
        initializePlayerWithSixMoreCard();
    }

    public boolean setNextPlayer() {
        int check = this.gamePlayers.size();
        while (check-- > 0) {
            int currentIndex = this.gamePlayers.indexOf(this.currentPlayer);
            if ( currentIndex == (this.gamePlayers.size()-1) ) {
                currentIndex = 0;
            } else {
                currentIndex++;
            }
            if ( this.gamePlayers.get(currentIndex).isActive() ) {
                this.currentPlayer = this.gamePlayers.get(currentIndex);
                return true;
            }
        }
        return false;
    }
    private void initializePlayerWithEightCard() {
        allocateCardToPlayers(8);
    }
    private void initializePlayerWithSixMoreCard() {
        allocateCardToPlayers(6);
    }

    /* Helper Function */
    private void allocateCardToPlayers(int times) {
        for (TichuPlayer gamePlayer : this.gamePlayers) {
            ArrayList<Card> drawCardList = this.deck.drawCardListFromDeck(times);
            gamePlayer.addCardsToHands(drawCardList);
        }
    }

    public Map<Long, List<Card>> getPlayersCard() {
        return this.gamePlayers.stream().collect(Collectors.toMap(TichuPlayer::getId, TichuPlayer::getHands));
    }

    @Override
    public void clearRound() {
    }
    private void endRound() {
    }

    @Override
    public void getRoundWinner() {
    }

}