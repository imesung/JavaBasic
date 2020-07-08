package com.pattern.ch17_state;

//State
public interface LightState {
    public LightState on(LightState lightState);
    public LightState off(LightState lightState);
}
