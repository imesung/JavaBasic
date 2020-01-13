package com.effective.generic;

class Multi<T, E> {
    private T t;
    private E e;

    public void setFirst(T t) {
        this.t = t;
    }

    public void setSecond(E e) {
        this.e = e;
    }

    public void print() {
        System.out.println(t);
        System.out.println(e);
    }
}

public class MultiGeneric {
    public static void main(String[] args) {
        Multi<String, Integer> multi = new Multi<>();
        multi.setFirst("하이");
        multi.setSecond(222);
        multi.print();
    }
}
