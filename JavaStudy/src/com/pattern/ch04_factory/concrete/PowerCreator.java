package com.pattern.ch04_factory.concrete;

import com.pattern.ch04_factory.framework.Item;
import com.pattern.ch04_factory.framework.ItemCreator;

import java.util.Date;

public class PowerCreator extends ItemCreator {
    @Override
    protected void requestItemsInfo() {
        System.out.println("DB에서 파워 물약의 정보를 가져온다.");
    }

    @Override
    protected void createItemLog() {
        System.out.println("파워 물약을 새로 생성했다." + new Date());
    }

    @Override
    protected Item createItem() {
        return new PowerPotion();
    }
}
