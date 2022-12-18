package com.cholab.botaku.Common.Card;

public enum Emblem {
    HEART("Heart"), SPADE("Spade"), DIAMOND("Diamond"), CLOVER("Clover"), NONE("None");
    private final String emblemName;
    Emblem(String emblemName) {
        this.emblemName = emblemName;
    }
    public String getEmblemName() {
        return emblemName;
    }
}
