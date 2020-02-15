package com.interview.staticStudy;

public class MyNestedClass {
    private static int num;
    public static class NestedStaticClass {
        public int b = num;
        public static void printStaticMsg() {
            System.out.println("NestedStaticClass");
        }

        public void printMsg() {
            System.out.println("NestedStaticClass");
        }
    }

    public class InnerClass {
        public int a = num;
        public void print() {
            System.out.println(a);
            System.out.println("InnerClass");
        }
    }
}
