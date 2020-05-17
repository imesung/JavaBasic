package com.pattern.ch08_abstractFactory.gt;

import com.pattern.ch08_abstractFactory.abst.BikeFactory;
import com.pattern.ch08_abstractFactory.abst.Body;
import com.pattern.ch08_abstractFactory.abst.Wheel;

public class GtBikeFactory implements BikeFactory {
    @Override
    public Body createBody() {
        return new GtBody();
    }

    @Override
    public Wheel createWheel() {
        return new GtWheel();
    }
}
