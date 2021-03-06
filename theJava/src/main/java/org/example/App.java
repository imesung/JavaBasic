package org.example;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class<?> bookClass = Class.forName("org.example.Book");
        Constructor<?> constructor = bookClass.getConstructor(String.class);
        Book book = (Book) constructor.newInstance("parameter");
        System.out.println(book);

        Method c = Book.class.getDeclaredMethod("sum", int.class, int.class);
        int invoke = (int)c.invoke(book, 1, 2);
        System.out.println(invoke);
    }
}
