package com.pattern.ch09_bridge;

public class PrintMorseCode extends MorseCode {

    public PrintMorseCode(MorseCodeFunction function) {
        super(function);
    }

    //blue
    public PrintMorseCode b() {
        dash();dot();dot();dot();space();
        return this;
    }

    public PrintMorseCode l() {
        dot();dash();dot();dot();space();
        return this;
    }

    public PrintMorseCode u() {
        dot();dot();dash();space();
        return this;
    }

    public PrintMorseCode e() {
        dot();space();
        return this;
    }
}
