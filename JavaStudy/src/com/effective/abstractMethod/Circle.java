package com.effective.abstractMethod;

public abstract class Circle {

    private Point point;
    private int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    public abstract int printCircle(Point point);

    public int diameter(int radius) {
        return radius * 2;
    }
}
