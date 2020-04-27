package com.pattern.ch04_factory.concrete;

import com.pattern.ch04_factory.framework.Item;
import com.pattern.ch04_factory.framework.ItemCreator;

public class FactoryMethodMain {
    public static void main(String [] args) {
        ItemCreator creator;
        Item item;

        creator = new HpCreator();
        item = creator.create();

        item.use();

        creator = new MpCreator();
        item = creator.create();

        item.use();
    }
}
