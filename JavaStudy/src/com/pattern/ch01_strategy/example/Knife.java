package com.pattern.ch01_strategy.example;

public class Knife implements Weapon{
    @Override
    public void attack() {
        System.out.println("칼 공격");
    }
}
