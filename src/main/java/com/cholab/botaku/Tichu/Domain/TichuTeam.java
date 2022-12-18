package com.cholab.botaku.Tichu.Domain;

public class TichuTeam {
    private final TichuPlayer p1, p2;
    private int teamScore;
    TichuTeam(TichuPlayer p1, TichuPlayer p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.teamScore = 0;
    }
    public TichuPlayer getP1() {
        return p1;
    }
    public TichuPlayer getP2() {
        return p2;
    }
    public int getTeamScore() {
        return teamScore;
    }
    public void setTeamScore(int teamScore) {
        this.teamScore = teamScore;
    }
}
