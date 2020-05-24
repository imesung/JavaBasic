package com.pattern.ch11_decorate;

abstract public class AbstAdding implements IBeverage {
    private IBeverage base;

    public AbstAdding(IBeverage base) {
        super();
        this.base = base;
    }

    @Override
    public int getTotalPrice() {
        return base.getTotalPrice();
    }

    public IBeverage getBase() {
        return base;
    }

    public void setBase(IBeverage base) {
        this.base = base;
    }
}
