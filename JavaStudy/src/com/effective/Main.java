package com.effective;

import com.effective.enumStudy.EnumSingle;
import com.effective.enumStudy.Gender;
import com.effective.enumStudy.Menu;
import com.effective.enumStudy.MenuImpl;

public class Main {

    public static void main(String[] args) {
	    Gender gender;

	    gender = Gender.MALE;
		System.out.println(gender);
	    gender = Gender.FEMALE;
		System.out.println(gender);


		MenuImpl menuImpl = MenuImpl.PIZZA;
		menuImpl.show();

		EnumSingle enumSingle = EnumSingle.INSTANCE;
		System.out.println(enumSingle.hashCode());

		EnumSingle enumSingle2 = EnumSingle.INSTANCE;
		System.out.println(enumSingle2.hashCode());
    }
}
