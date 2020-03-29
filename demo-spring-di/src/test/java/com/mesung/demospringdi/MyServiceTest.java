package com.mesung.demospringdi;

import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MyServiceTest {

    @Test
    public void my() throws Exception{
        MyRepository myRepositoryMock = mock(MyRepository.class);
        Book hibernateBook = new Book();
        hibernateBook.setTitle("hibernate");
        when(myRepositoryMock.save(any())).thenReturn(hibernateBook);
        MyService myService = new MyService(myRepositoryMock);

        Book book = new Book();
        book.setTitle("spring");
        myService.rent(book);
        myService.returnBook(book);
    }

}