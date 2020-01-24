package com.effective.generic;

import java.util.List;

public class GenericMain {
    public static void peekBox(Book<String> box) {
        System.out.println(box);
    }

    public static void main(String [] args) {
        Book<String> bs = new Book<>();
        peekBox(bs);
        Book book = new Book();
        book.set("제네릭 공부");
        String str = (String)book.get();

    }
}
