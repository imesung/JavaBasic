package com.effective.reflection;

import java.util.Arrays;

public class refMain {
    public static void main(String [] args) {
        Class<?> c1 = Book.class;
        Arrays.stream(c1.getFields()).forEach(field -> System.out.println(field));
    }
}
