package com.pattern.ch09_bridge;

public class FlashMCF implements MorseCodeFunction{
    @Override
    public void dot() {
        System.out.print("#");
    }

    @Override
    public void dash() {
        System.out.print("-*-");
    }

    @Override
    public void space() {
        System.out.print(" ");
    }
}
