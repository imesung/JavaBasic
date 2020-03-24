package com.interview.finalStudy;

public class FinalTest {
    public int test = 10;
    private final int value;
    private static final String PR_STATIC = "private static";
    public static final String PU_STATIC = "public static";
    public FinalTest(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}

class FinalMain {
    public static void main(String [] args) {

        System.out.println(FinalTest.PU_STATIC);

        FinalTest f1 = new FinalTest(1);
        FinalTest f2 = new FinalTest(2);
        FinalTest f3 = new FinalTest(3);

        System.out.println(f1.getValue());
        System.out.println(f2.getValue());
        System.out.println(f3.getValue());


        String num = "-";
        System.out.println(Integer.parseInt(num));
    }
}