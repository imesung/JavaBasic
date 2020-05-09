package com.oop;

public class CarSpec extends VehicleSpec{
    private int wheelSize;
    public CarSpec(int price, String color, int wheelSize) {
        super(price, color);
        this.wheelSize = wheelSize;
    }
}
