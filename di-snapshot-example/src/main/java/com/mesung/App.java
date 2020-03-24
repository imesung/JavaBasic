package com.mesung;

import com.mesung.di.ContainerService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

        AccountService accountService = ContainerService.getObject(AccountService.class);
        accountService.join();
    }
}
