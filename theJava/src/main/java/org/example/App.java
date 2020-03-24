package org.example;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws ClassNotFoundException {
        Arrays.stream(Book.class.getDeclaredFields()).forEach(f -> {
            Arrays.stream(f.getAnnotations()).forEach(a -> {
                if(a instanceof AnotherAnnotation) {
                    AnotherAnnotation anotherAnnotation = (AnotherAnnotation) a;
                    System.out.println(anotherAnnotation.value());
                    System.out.println(anotherAnnotation.number());
                }
            });
        });
    }
}
