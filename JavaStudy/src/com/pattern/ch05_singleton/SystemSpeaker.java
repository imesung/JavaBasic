package com.pattern.ch05_singleton;

public class SystemSpeaker {
    static private SystemSpeaker instance;
    private int volume;

    private SystemSpeaker() {
        volume = 5;
    }

    private SystemSpeaker(String volume) {
    }

    public static SystemSpeaker getInstance() {
        if(null == instance) {
            return instance = new SystemSpeaker();
        }
        return instance;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}

