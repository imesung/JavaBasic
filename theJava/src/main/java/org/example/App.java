package org.example;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws ClassNotFoundException {
        Class<Book> bookClass = Book.class;

        Arrays.stream(bookClass.getFields()).forEach(System.out::println);
    }
}
