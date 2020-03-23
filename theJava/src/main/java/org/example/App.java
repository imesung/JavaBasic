package org.example;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws ClassNotFoundException {
        Class<Book> bookClass = Book.class;

        Book book = new Book();
        Arrays.stream(bookClass.getDeclaredFields()).forEach(f -> {
            try {
                //모든 접근제한자 접근
                f.setAccessible(true);

                System.out.printf("%s %s\n", f, f.get(book));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }
}
