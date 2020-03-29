package com.mesung.demospringdi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static org.junit.Assert.assertNotNull;


public class BookServiceTest {
    BookService bookService = (BookService) Proxy.newProxyInstance(BookService.class.getClassLoader(), new Class[]{BookService.class},
            new InvocationHandler() {

                BookService bookService = new DefaultBookService();

                @Override
                public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                    if(method.getName().equals("rent")) {
                        System.out.println("aaaa");
                        Object invoke = method.invoke(bookService, objects);
                        System.out.println("bbbb");
                        return invoke;
                    }

                    return method.invoke(bookService, objects);
                }
            });

    @Test
    public void di() {
        Book book = new Book();
        book.setTitle("spring");
        bookService.rent(book);
        bookService.returnBook(book);
    }
}