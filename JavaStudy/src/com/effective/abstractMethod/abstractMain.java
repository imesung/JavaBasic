package com.effective.abstractMethod;

public class abstractMain {
    public static void main(String[] args) {
        Circle circle = new Circle(3) {
            @Override
            public int printCircle(Point point) {
                return 0;
            }
        };
    }

}
