package com.pattern.ch06_prototype;

public class Circle implements Shape{
    private int x,y,r;

    public Circle(int x, int y, int r) throws CloneNotSupportedException {
        super();
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public Circle copy() {
        Circle circle = null;
        try {
            circle = (Circle) clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        circle.x = x+1;
        circle.y = y+1;
        return circle;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }
}
