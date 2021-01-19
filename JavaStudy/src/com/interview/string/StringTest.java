package com.interview.string;

public class StringTest {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("a");
        sb.append("b");

        String s = "ab";

        System.out.println(s == sb.toString());
        System.out.println(sb.toString() == sb.toString());

    }
}
