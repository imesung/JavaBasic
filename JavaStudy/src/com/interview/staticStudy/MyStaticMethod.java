package com.interview.staticStudy;

public class MyStaticMethod {
    static int a = 10;
    int b = 20;

    static void m1() {
        a = 20; //클래스 변수 접근 가능.
        System.out.println("from m1");

        //b = 10; //error : 클래스 변수가 아닌 인스턴스 변수이므로 접근 불가.

        //m2();  //error : static 메소드가 아니므로 접근 불가.

        //System.out.println(super.a); //error : super 불가.
        //System.out.println(this.b);  //eorror : this(MyStaticMethod) 불가.
    }

    void m2() {
        System.out.println("from m2");
    }
}
