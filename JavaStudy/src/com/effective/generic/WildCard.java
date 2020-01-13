package com.effective.generic;

class A {
    public int a = 1;
    //메소드 리턴 타입에 제네릭 활용
    public <T extends String> void printA(T t) {
        System.out.println(t + " " + a);
    }
}
class B extends A {}
class C extends B {}
//클래스 생성 시 서브타입에 제네릭 활용
class Test<T extends A> {
    private T t;
    public Test(T t) {
        this.t = t;
    }
    public T print() {
        return t;
    }
}

public class WildCard {
    //와일드 카드 활용하여 메소드 매개변수 제한
    public static void wildCardTest(Test<? extends B> test) {
        System.out.println(test.getClass());
    }

    public static void wildCardTest2(Test<? super B> test) {
        System.out.println(test.getClass());
    }

    public static <T extends B> void wild(Test<T> test){

    }

    public static void main(String [] args) {
        Test<C> test = new Test<>(new C());
        test.print().printA("A의 결과는");
        wildCardTest(test);

        Test<A> test2 = new Test<>(new A());
        wildCardTest2(test2);

        wild(test);
    }
}
