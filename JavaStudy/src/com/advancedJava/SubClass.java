package com.advancedJava;

public class SubClass extends SuperClass{

    static int a;

    public SubClass() {
    }

    public void print() {
        super.print();
        System.out.println("SubClass");
    }
}
