package com.pattern.ch01_strategy.example;

public class Ax implements Weapon {
    @Override
    public void attack() {
        System.out.println("도끼 공격");
    }
}