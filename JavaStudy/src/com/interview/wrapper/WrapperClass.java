package com.interview.wrapper;

public class WrapperClass {

    public static void main(String [] args) {
        Integer i = new Integer(23);
        Integer ii = new Integer(43);
        int a = 3;
        System.out.println(i.compareTo(ii));
        System.out.println(i);

        print(a);
    }

    public static void print(Integer num) {
        System.out.println(num);
    }
}
