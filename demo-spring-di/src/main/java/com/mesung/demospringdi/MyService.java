package com.mesung.demospringdi;

public class MyService {

    MyRepository myRepository;

    public MyService(MyRepository myRepository) {
        this.myRepository = myRepository;
    }

    public void rent(Book book) {
        Book save = myRepository.save(book);
        System.out.println(save.getTitle());
    }

    public void returnBook(Book book) {
        myRepository.save(book);
    }
}
