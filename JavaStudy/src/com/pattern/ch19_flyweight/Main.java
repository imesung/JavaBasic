package com.pattern.ch19_flyweight;

public class Main {
    public static void main(String[] args) {
        FlyWeightFactory flyWeightFactory = new FlyWeightFactory();
        FlyWeight flyWeight = flyWeightFactory.getFlyWeight("AA");
        System.out.println(flyWeight);

        flyWeight = flyWeightFactory.getFlyWeight("AA");
        System.out.println(flyWeight);

        flyWeight = flyWeightFactory.getFlyWeight("CC");
        System.out.println(flyWeight);

        flyWeight = flyWeightFactory.getFlyWeight("BB");
        System.out.println(flyWeight);

        flyWeight = flyWeightFactory.getFlyWeight("AA");
        System.out.println(flyWeight);

        flyWeight = flyWeightFactory.getFlyWeight("CC");
        System.out.println(flyWeight);



        ///////////////////////////////////////////

        /*String s1 = "hello";
        System.out.println(s1.hashCode());
        String s2 = "hello";
        System.out.println(s2.hashCode());

        String s3 = new String("hi");
        System.out.println(s3.hashCode());
        String s4 = "hi";
        String s5 = s4.intern();
        System.out.println(s4.hashCode());
        System.out.println(s5.hashCode());*/

    }
}
