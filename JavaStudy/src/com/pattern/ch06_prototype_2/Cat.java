package com.pattern.ch06_prototype_2;

public class Cat implements Cloneable{
    private String name;
    private Age age;

    public Cat(String name, Age age) {
        this.name = name;
        this.age = age;
    }

    public void chgName(String name) {
        this.name = name;
    }

    public void chgAge(int year, int value) {
        this.age.chgAge(year, value);
    }

    public Cat copy() throws CloneNotSupportedException {
        Cat ret = (Cat)this.clone();
        ret.age = new Age(this.age.getYear(), this.age.getValue());
        return ret;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
