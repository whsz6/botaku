package com.cholab.botaku.Tichu.Domain;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

@Slf4j
@Getter
@Setter
public class Tichu extends Game {
    private long gameId;
    private HashMap<Long, String> playerMap;
    private ArrayList<TichuPlayer> gamePlayers;

    private TichuTeam team1, team2;
    private int roundIndex;
    private TichuRound round;

    public Tichu(long gameId, String initGameData) {
        super(gameId, initGameData);
        this.playerMap = new HashMap<>();
        initPlayer();
        buildTeam();
        initRound();
    }

    private void initPlayer() {
        parsePlayer();
        gamePlayers = new ArrayList<>(4);
        playerMap.forEach((key, value) -> {
            log.debug("[*] Tichu Player id : {}, name : {}", key, value);
            gamePlayers.add(new TichuPlayer(key, value));
        });
    }

    /*
    * 데이터 형식 : id:name,id:name ...
    * */
    private void parsePlayer() {
        StringTokenizer st, internalST;
        st = new StringTokenizer(this.initGameData, ",");
        while (st.hasMoreTokens()) {
            internalST = new StringTokenizer(st.nextToken(), ":");
            long playerId = Long.parseLong(internalST.nextToken());
            String playerName = internalST.nextToken();
            playerMap.put(playerId, playerName);
        }
    }
    private void buildTeam() {
        team1 = new TichuTeam(this.gamePlayers.get(0), gamePlayers.get(2));
        team2 = new TichuTeam(this.gamePlayers.get(1), gamePlayers.get(3));
    }

    private void initRound() {
        round = new TichuRound(gamePlayers);
    }
}