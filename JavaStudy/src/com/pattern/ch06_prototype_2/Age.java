package com.pattern.ch06_prototype_2;

public class Age {
    private int year;
    private int value;

    public Age(int year, int value) {
        this.year = year;
        this.value = value;
    }

    public void chgAge(int year, int value) {
        this.year = year;
        this.value = value;
    }

    public int getYear() {
        return year;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Age{" +
                "year=" + year +
                ", value=" + value +
                '}';
    }
}
