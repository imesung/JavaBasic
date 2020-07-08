package com.pattern.ch17_state;

public class Application {
    public static void main(String[] args) {
        Light light = new Light(new OffState());
        light.off();
        light.off();

        light.on();
        light.on();

        light.off();
        light.on();
    }
}
