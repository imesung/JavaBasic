package com.effective.override;

public class Truck extends Car{
    @Override
    public void print() {
        System.out.println("트럭 달달달");
        super.print();	//부릉부릉
    }
}
