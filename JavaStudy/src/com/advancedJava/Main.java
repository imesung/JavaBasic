package com.advancedJava;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {

    public static void main(String [] args) {
        Integer a = 129;
        Integer b = 129;
        System.out.println(a == b);
        System.out.println(a.hashCode() == b.hashCode());

        SuperClass.a = 1;
        SubClass.a = 2;
        System.out.println(SuperClass.a +"," + SubClass.a);
        SuperClass sc = new SubClass();
        sc.print();

        List<Object> obj = new ArrayList<Object>();
        HashSet<String> hs = new HashSet<String>();
        hs.contains("ss");

        System.out.println("===========");

        StringBuffer sf = new StringBuffer();
        System.out.println(sf.indexOf(","));


    }

}
