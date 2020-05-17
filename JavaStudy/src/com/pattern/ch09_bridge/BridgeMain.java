package com.pattern.ch09_bridge;

public class BridgeMain {
    public static void main(String[] args) {
        PrintMorseCode printMorseCode = new PrintMorseCode(new DefaultMCF());
        printMorseCode.b().l().u().e();
    }
}
