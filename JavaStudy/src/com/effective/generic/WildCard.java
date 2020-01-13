package com.effective.generic;

class A {}
class B extends A {}
class C extends B {}
class Test<T> {}

public class WildCard {
    public static void wildCardTest(Test<? extends B> test) {
        System.out.println(test.getClass());
    }

    public static void wildCardTest2(Test<? super B> test) {
        System.out.println(test.getClass());
    }

    public static void main(String [] args) {
        Test<C> test = new Test<>();
        wildCardTest(test);

        Test<A> test2 = new Test<>();
        wildCardTest2(test2);
    }
}
