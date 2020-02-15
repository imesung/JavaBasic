package com.interview.staticStudy;

public class MyStaticMain {

    public static void main(String [] args) {
        System.out.println("front main");
        System.out.println("a : " + MyStaticClass.a + ", b : " + MyStaticClass.b);
        MyStaticClass myStatic = new MyStaticClass();
        System.out.println();
    }
}
