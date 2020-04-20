package com.pattern.ch01_strategy;

public class Main {

    public static void main(String [] args) {
        AInterface aInterface = new AInterfaceImpl();

        //통로
        //aInterface.funcA();

        AObj aObj = new AObj(aInterface);
        aObj.funcAA();
    }
}
