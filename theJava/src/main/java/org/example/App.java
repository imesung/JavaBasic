package org.example;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws ClassNotFoundException {
        Class<Book> bookClass = Book.class;

        //getDeclaredFields는 접근제한자에 상관없이 모든 필드를 가져온다.
        Arrays.stream(bookClass.getDeclaredFields()).forEach(System.out::println);
    }
}
