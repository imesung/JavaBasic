package org.example;

@MyAnnotation("haong")
public class Book {
    private static String B = "BOOK";

    private static final String C = "BOOK";

    @AnotherAnnotation(value = "hello", number = 20)
    private String a = "a";

    public String d = "d";

    @AnotherAnnotation(value = "hi", number = 60)
    protected String e = "e";

    public Book() {
    }

    @AnotherAnnotation
    public Book(String a, String d, String e) {
        this.a = a;
        this.d = d;
        this.e = e;
    }

    private void f() {
        System.out.println("F");
    }

    public void g() {
        System.out.println("g");
    }

    public int h () {
        return 100;
    }
}
