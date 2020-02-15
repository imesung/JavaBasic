package com.interview.staticStudy;

public class MyStaticClass {
    static int a = 10;
    static int b;
    static int c = print();

    static {
        System.out.println("static block");
        b = a * 4;
    }

    static int print() {
        System.out.println("static value");
        return 30;
    }

    public MyStaticClass() {
        System.out.println("new MyStaticClass");
    }

}
