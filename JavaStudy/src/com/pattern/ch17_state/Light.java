package com.pattern.ch17_state;

//Context
public class Light{

    private LightState lightState;


    /**
     * @info 처음 상태는 꺼진 상태
     */
    public Light(LightState lightState) {
        this.lightState = lightState;
    }

    /**
     * @info 해당 행동을 통해 상태도 변경(on)
     */
    public void on() {
        this.lightState = this.lightState.on(this.lightState);
    }


    /**
     * @info 해당 행동을 통해 상태도 변경(off)
     */
    public void off() {
        this.lightState = this.lightState.off(this.lightState);
    }
}
