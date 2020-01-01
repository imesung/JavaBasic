package com.effective.enumStudy;

public enum Menu {
    PIZZA(2000), BURGER(1000), COKE(500);
    int value;

    private Menu(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
