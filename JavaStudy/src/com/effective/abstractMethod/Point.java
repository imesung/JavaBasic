package com.effective.abstractMethod;

public class Point extends Circle{
    private int x;
    private int y;

    public Point(int x, int y) {
        super(3);
        this.x = x;
        this.y = y;
    }

    @Override
    public int printCircle(Point point) {
        return 0;
    }
}
