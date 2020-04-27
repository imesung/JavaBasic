package com.pattern.ch04_factory.concrete;

import com.pattern.ch04_factory.framework.Item;

public class HpPotion implements Item {
    @Override
    public void use() {
        System.out.println("체력 회복");
    }
}
