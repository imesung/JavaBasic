package com.effective;

import com.effective.enumStudy.EnumSingle;
import com.effective.enumStudy.Gender;
import com.effective.enumStudy.Menu;
import com.effective.enumStudy.MenuImpl;

import java.util.Arrays;
import java.util.List;

class Book{}
class Book2{}

public class Main {

    public static void overloading(Object obj) {
        System.out.println("obj");
    }

    public static void overloading(Book book) {
        System.out.println("book");
    }

    public static void overloading(Book2 book2) {
        System.out.println("book2");
    }

    public static void main(String[] args) {
        List<Object> list = Arrays.asList(new Object(), new Book(), new Book2());

        for(Object obj : list) {
            System.out.println(obj.getClass());
            overloading(obj);
        }

	    /*Gender gender;

	    gender = Gender.MALE;
		System.out.println(gender);
	    gender = Gender.FEMALE;
		System.out.println(gender);


		MenuImpl menuImpl = MenuImpl.PIZZA;
		menuImpl.show();

		EnumSingle enumSingle = EnumSingle.INSTANCE;
		System.out.println(enumSingle.hashCode());

		EnumSingle enumSingle2 = EnumSingle.INSTANCE;
		System.out.println(enumSingle2.hashCode());*/
    }
}
