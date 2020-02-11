package com.advancedJava;

public class Main {

    public static void main(String [] args) {
        SuperClass.a = 1;
        SubClass.a = 2;
        System.out.println(SuperClass.a +"," + SubClass.a);
        SuperClass sc = new SubClass();
        sc.print();
    }

}
