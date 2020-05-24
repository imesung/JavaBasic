package com.pattern.ch11_decorate;

public class Milk extends AbstAdding {

    public Milk(IBeverage meterial) {
        super(meterial);
    }

    @Override
    public int getTotalPrice() {
        return super.getTotalPrice()+50;
    }
}
