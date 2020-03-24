package org.example;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface AnotherAnnotation {
    String value() default "mesung";

    int number() default 100;
}
