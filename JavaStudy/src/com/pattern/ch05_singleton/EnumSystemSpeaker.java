package com.pattern.ch05_singleton;

public enum EnumSystemSpeaker {
    INSTANCE;

    private int volume = 5;

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
