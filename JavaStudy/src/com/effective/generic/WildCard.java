package com.effective.generic;

class A {
    public int a = 1;
    public <T extends String> void printA(T t) {
        System.out.println(t + " " + a);
    }
}
class B extends A {}
class C extends B {}
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
