package com.pattern.ch08_abstractFactory;

import com.pattern.ch08_abstractFactory.abst.BikeFactory;
import com.pattern.ch08_abstractFactory.abst.Body;
import com.pattern.ch08_abstractFactory.abst.Wheel;
import com.pattern.ch08_abstractFactory.gt.GtBikeFactory;
import com.pattern.ch08_abstractFactory.sam.SamFactory;

public class AbstractFactoryMain {
    public static void main(String[] args) {
        //BikeFactory bikeFactory = new SamFactory();
        GtBikeFactory bikeFactory = new GtBikeFactory();

        Body body = bikeFactory.createBody();
        Wheel wheel = bikeFactory.createWheel();

        System.out.println(body.getClass());
        System.out.println(wheel.getClass());
    }
}
