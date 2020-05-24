package com.pattern.ch12_visitor;

public class VisitableA implements Visitable {

    private int age;

    public VisitableA(int age) {
        super();
        this.age = age;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
