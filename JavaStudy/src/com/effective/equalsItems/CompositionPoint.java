package com.effective.equalsItems;

public class CompositionPoint {
    private final Point point;

    public CompositionPoint(int x, int y) {
        point = new Point(x, y);
    }

    public Point asPoint() {
        return point;
    }

    @Override
    public boolean equals(Object o) {
        System.out.println("this" + this);
        if(!(o instanceof CompositionPoint)) {
            System.out.println("타입 불일치");
            return false;
        }
        CompositionPoint cp = (CompositionPoint) o;
        return cp.point.equals(point);
    }
}
