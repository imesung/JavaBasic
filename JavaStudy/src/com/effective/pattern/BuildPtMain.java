package com.effective.pattern;

import java.util.Calendar;
import java.util.Date;

public class BuildPtMain {
    public static void main (String [] args) {
        BuildPtPet.Builder builder = new BuildPtPet.Builder();

        BuildPtPet pet = builder
                .withPetName("cat")
                .withOwnerName("mesung")
                //.withTelephone("112")
                .withAddress("suwon")
                .withDateOfBirth(Calendar.getInstance().getTime())
                .withEmailAddress("myhs2002")
                .build();

        System.out.println(pet.toString());


        String obj = "";
        obj.equals("");

        Thre

    }
}
