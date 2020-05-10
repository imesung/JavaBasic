package com.pattern.ch06_prototype_2;

public class PrototypeMain_2 {
    public static void main(String[] args) throws CloneNotSupportedException {
        Cat navi = new Cat("navi", new Age(2018, 3));

        Cat yo = navi.copy();
        yo.chgName("yo");
        yo.chgAge(2013, 2);

        System.out.println(navi.toString());
        System.out.println(yo.toString());
    }
}
