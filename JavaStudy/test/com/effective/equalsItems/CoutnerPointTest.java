package com.effective.equalsItems;

import java.util.HashMap;
import java.util.Objects;
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

        CompositionPoint cp = new CompositionPoint(1, 2);
        Point pt = new Point(1, 2);
        System.out.println(cp.equals(pt));


        Point point = new Point(1,0);
        CounterPoint point1 = new CounterPoint(1,0);

        boolean b = onUnitCircle(point);
        boolean b1 = onUnitCircle(point1);

        System.out.println(b);
        System.out.println(b1);

        String str = "ss";
        boolean abc = str.equals("bb");
        HashMap<String, Integer> hs = new HashMap<>();
        hs.put("1", 1);
        hs.get("1");
        Objects.hash("", "", "");
    }
}
