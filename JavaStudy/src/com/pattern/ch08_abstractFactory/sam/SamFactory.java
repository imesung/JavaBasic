package com.pattern.ch08_abstractFactory.sam;

import com.pattern.ch08_abstractFactory.abst.BikeFactory;
import com.pattern.ch08_abstractFactory.abst.Body;
import com.pattern.ch08_abstractFactory.abst.Wheel;

public class SamFactory implements BikeFactory {
    @Override
    public Body createBody() {
        return new SamBody();
    }

    @Override
    public Wheel createWheel() {
        return new SamWheel();
    }
}
