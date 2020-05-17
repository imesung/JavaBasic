package com.pattern.ch08_abstractFactory.abst;

public interface BikeFactory {

    public Body createBody();
    public Wheel createWheel();

}
