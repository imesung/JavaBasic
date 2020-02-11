package com.effective.equalsItems;

import java.util.Set;

class CounterPointTest {

    private static final Set<Point> unitCircle = Set.of(
            new Point(1,0),  new Point(0,1),
            new Point(-1,0), new Point(0,-1)
    );


    public static boolean onUnitCircle(Point point) {
        return unitCircle.contains(point);
    }


    public static void main(String[] args) {
        Point point = new Point(1,0);
        CounterPoint point1 = new CounterPoint(1,0);

        boolean b = onUnitCircle(point);
        boolean b1 = onUnitCircle(point1);

        System.out.println(b);
        System.out.println(b1);
    }
}
