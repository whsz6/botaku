package com.cholab.botaku.Usecase.Tichu;

import com.cholab.botaku.DTO.TichuCommand;
import com.cholab.botaku.Domain.Game.Library.Card.Card;
import com.cholab.botaku.Domain.Game.Tichu.Cards.Combination.*;
import com.cholab.botaku.Domain.Room.RoomPlayer;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface CardSendUseCase {
    boolean sendCard(CardSendCommand command);
    @Data
    class CardSendCommand {
        private final TichuCommand subCommand = TichuCommand.CARD_SEND;
        private final RoomPlayer fromPlayer;
        private final TichuCardCombinationType type;
        private final List<Card> cardList;
        private TichuCardCombination combination;
        public CardSendCommand(@NotNull RoomPlayer fromPlayer, @NotNull TichuCardCombinationType type, @NotNull List<Card> sendCardList) {
            this.fromPlayer = fromPlayer;
            this.type = type;
            this.cardList = sendCardList;
            setCardCombination();
        }

        private void setCardCombination() {
            try {
                switch (this.type) {
                    case ONE_CARD -> this.combination = new OneCard(this.cardList);
                    case PAIR -> this.combination = new Pair(this.cardList);
                    case MULTI_PAIR -> this.combination = new MultiPair(this.cardList);
                    case THREE_CARD -> this.combination = new ThreeCard(this.cardList);
                    case FULL_HOUSE -> this.combination = new FullHouse(this.cardList);
                    case BOMB_FOUR_CARD -> this.combination = new FourCardBomb(this.cardList);
                    case STRAIGHT -> this.combination = new Straight(this.cardList);
                    case BOMB_STRAIGHT -> this.combination = new StraightBomb(this.cardList);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
