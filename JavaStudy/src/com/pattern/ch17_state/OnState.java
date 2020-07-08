package com.pattern.ch17_state;

public class OnState implements LightState {
    @Override
    public LightState on(LightState lightState) {
        System.out.println("Light Nothing");
        return lightState;
    }

    @Override
    public LightState off(LightState lightState) {
        System.out.println("Light Off");
        return new OffState();
    }
}
