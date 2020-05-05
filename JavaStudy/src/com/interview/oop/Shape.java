package com.interview.oop;

abstract class Shape{
    abstract public void draw();
}

class MyShape{
    public void print(){}
}
class Rec extends MyShape{
    public void print(){}
    public void draw(){}
}

class Rectangle extends Shape{
    public void draw(){ rectangle();}
    public void rectangle(){ System.out.println("rectangle"); }
}

class Main {
    public static void main(String[] args) {
        Shape shape = new Rectangle();	//코드 2
        shape.draw();  // Shape 클래스에 의존적인 코드
    }
}

