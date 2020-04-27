package com.pattern.ch04_factory.concrete;

import com.pattern.ch04_factory.framework.Item;

public class PowerPotion implements Item {
    @Override
    public void use() {
        System.out.println("파워 상승");
    }
}
