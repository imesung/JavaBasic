package com.pattern.ch17_state;

public class OffState implements LightState {
    @Override
    public LightState on(LightState lightState) {
        System.out.println("Light ON");
        return new OnState();
    }

    @Override
    public LightState off(LightState lightState) {
        System.out.println("Light Nothing");
        return lightState;
    }
}
