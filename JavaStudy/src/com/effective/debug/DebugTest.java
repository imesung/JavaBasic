package com.effective.debug;

public class DebugTest {

    Integer a = 127;
    Integer b = 127;

    Integer c = 128;
    Integer d = 128;
}

class Main {
    public static void main(String[] args) {
        DebugTest debugTest = new DebugTest();
        System.out.println(debugTest.a == debugTest.b);
        System.out.println(debugTest.c == debugTest.d);

        String a = "a";
        String b = "b";
        boolean ab = a.equals(b);
    }
}
