package com.interview.staticStudy;

public class MyNestedMain {

    public static void main(String [] a) {
        //static 클래스(중첩 클래스)의 static 메소드 접근
        MyNestedClass.NestedStaticClass.printStaticMsg();

        //static 클래스(중첩 클래스)의 일반 메소드 접근
        MyNestedClass.NestedStaticClass nestedStaticClass = new MyNestedClass.NestedStaticClass();
        nestedStaticClass.printMsg();

        //일반 클래스의 내부 클래스 생성 후 메소드 접근
        MyNestedClass.InnerClass innerClass = new MyNestedClass().new InnerClass();
        innerClass.print();
    }
}
