package com.mesung.demospringdi;

public class BookServiceProxy implements BookService {

    BookService bookService;

    public BookServiceProxy(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void rent(Book book) {
        System.out.println("aaaa");
        bookService.rent(book);
        System.out.println("bbbb");
    }

    @Override
    public void returnBook(Book book) {
        bookService.returnBook(book);
    }
}
