package com.interview.finalStudy;

public class MyFinal {
    int notFinal = 10;
    public static void main(String args[]) {
        final MyFinal t1 = new MyFinal();
        MyFinal t2 = new MyFinal();
        //t = t2;    //컴파일 에러 발생

        System.out.println("final 클래스의 변수 : " + t1.notFinal);
        t1.notFinal = 20;
        System.out.println("final 클래스의 변수 수정 : " + t1.notFinal);
    }
}
