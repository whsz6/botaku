package com.cholab.botaku.Tichu.DTO;

public enum TichuCommand {
    /*
    * GAME_START : 게임 시작. 방장만 호출할 수 있음.
    * CARD_SEND : 카드 내기
    * PASS : 패스
    * SAY_SMALL_TICHU : 스몰 티츄 부르기
    * SAY_LARGE_TICHU : 라지 티츄 부르기
    * GAME_END : 게임 종료. 모두 호출하여야 됨.
    * */

    GAME_START("GAME_START"),
    GAME_INFO("GAME_INFO"),
    CARD_SEND("CARD_SEND"),
    PASS("PASS"),
    SAY_SMALL_TICHU("SAY_SMALL_TICHU"),
    SAY_LARGE_TICHU("SAY_LARGE_TICHU"),
    GAME_END("GAME_END");

    private final String command;
    TichuCommand(String commandName) {
        this.command = commandName;
    }
    public String getCommandName() {
        return command;
    }
}
