package com.effective.generic;

public class GenericMain {
    public static void main(String [] args) {
        Book book = new Book();
        book.set("제네릭 공부");
        String str = (String)book.get();
    }
}
