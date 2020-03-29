package com.mesung.demospringdi;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.Method;


public class BookServiceTest {
    /*BookService bookService = (BookService) Proxy.newProxyInstance(BookService.class.getClassLoader(), new Class[]{BookService.class},
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
    } */

    @Test
    public void di() {
        MethodInterceptor handler = new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                if(method.getName().equals("rent")) {
                    System.out.println("aaaa");
                    Object invoke = method.invoke(myBookService, objects);
                    System.out.println("bbbb");
                    return invoke;
                }
                return method.invoke(myBookService, objects);
            }

            MyBookService myBookService = new MyBookService();

        };

        MyBookService myBookService = (MyBookService) Enhancer.create(MyBookService.class, handler);

        Book book = new Book();
        book.setTitle("spring");
        myBookService.rent(book);
        myBookService.returnBook(book);
    }

}