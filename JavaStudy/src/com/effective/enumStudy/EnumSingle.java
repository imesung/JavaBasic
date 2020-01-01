package com.effective.enumStudy;

public enum EnumSingle {
    INSTANCE(30, "richard");

    String name;
    int age;

    EnumSingle(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

}
