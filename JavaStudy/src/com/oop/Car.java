package com.oop;

public class Car implements Vehicle{

    @Override
    public void ride() {
        System.out.println("자동차를 탄다");
    }

    @Override
    public void move() {
        System.out.println("자동차를 이동한다.");
    }

    public void back() {
        System.out.println("자동차를 후진한다.");
    }
}
