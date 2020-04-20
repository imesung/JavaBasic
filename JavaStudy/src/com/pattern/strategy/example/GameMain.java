package com.pattern.strategy.example;

public class GameMain {
    public static void main(String [] args) {
        GameCharacter gameCharacter = new GameCharacter();
        gameCharacter.attack();

        gameCharacter.setWeapon(new Knife());
        gameCharacter.attack();

        gameCharacter.setWeapon(new Sword());
        gameCharacter.attack();

        gameCharacter.setWeapon(new Ax());
        gameCharacter.attack();
    }
}
