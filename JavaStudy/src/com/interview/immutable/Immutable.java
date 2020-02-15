package com.interview.immutable;

public class Immutable {

    public static void main(String [] args) {
        StringBuilder name = new StringBuilder("name");
        MyImmutable myImmutable = new MyImmutable(name);
        name = name.append(" update");
        System.out.println(name);
        System.out.println(myImmutable.toString());

        System.out.println();

        String str = "test";
        System.out.println(str.hashCode());
        String str2 = "test";
        System.out.println(str2.hashCode());
        str = "teas";


        String strNew = new String("festival");
        System.out.println(strNew.hashCode());
        String strNew2 = new String("festival");
        System.out.println(strNew2.hashCode());

    }
}
