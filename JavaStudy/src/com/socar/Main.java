package com.socar;

public class Main {
    public static void main(String[] args) {
        Parent parent = new Child();
        parent.p1();

        Child child = (Child) parent;
        child.p2();
        child.p1();
        child.c2();
    }
}
