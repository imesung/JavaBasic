package com.pattern.ch12_visitor;

import java.util.ArrayList;

public class VisitorMain {
    public static void main(String[] args) {
        ArrayList<Visitable> visitables = new ArrayList<>();
        visitables.add(new VisitableA(1));
        visitables.add(new VisitableA(2));
        visitables.add(new VisitableA(3));
        visitables.add(new VisitableA(4));
        visitables.add(new VisitableA(5));

        Visitor visitor = new VisitorA();

        //int ageSum = 0;

        for (Visitable visitable : visitables) {
            visitable.accept(visitor);
            //ageSum += ((VisitableA)visitable).getAge();
            //visitableA에 접근하여 외부에서 수정이 가능하고 수정이 되면 복잡한 로직이 해당 for문에 들어가 추후에 유지보수가 매우 힘들어 질 수 있다.
            //현재는 accept() 메소드로만 우리가 원하는 결과값을 얻을 수 있으니 유지보수 측면에서 매우 효율적이다.
        }

        System.out.println(((VisitorA)visitor).getAgeSum());
        //System.out.println(ageSum);
    }
}
