package com.pattern.ch04_factory.concrete;

import com.pattern.ch04_factory.framework.Item;

public class MpPotion implements Item {
    @Override
    public void use() {
        System.out.println("마력 회복");
    }
}
