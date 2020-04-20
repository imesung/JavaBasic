package com.pattern.ch01_strategy;

public class AObj {

    AInterface aInterface;

    public AObj(AInterface aInterface) {
        this.aInterface = aInterface;
    }

    public void funcAA() {

        //1. 일반적인 기능 구현
        System.out.println("AAA");
        System.out.println("AAA");

        //2. 위임을 받아 기능 구현
        aInterface.funcA();
        aInterface.funcA();
    }
}
