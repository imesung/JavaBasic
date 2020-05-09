package com.oop;

public class AirPlane implements Vehicle{
    @Override
    public void ride() {
        System.out.println("비행기를 탄다.");
    }

    @Override
    public void move() {
        System.out.println("비행기를 이동한다.");
    }

    public void fly() {
        System.out.println("비행기가 난다.");
    }
}
