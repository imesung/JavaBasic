package com.pattern.ch04_factory.framework;

public abstract class ItemCreator {
    public Item create() {
        Item item;

        requestItemsInfo();
        item = createItem();
        createItemLog();

        return item;
    }

    //step1. 아이템을 생성하기 전에 DB에서 아이템 정보를 요청한다.
    abstract protected void requestItemsInfo();
    //step2. 아이템을 생성한 후 아이템 복제 등의 불법을 방지하기 위해 데이터베이스에 아이템 생성 정보를 남긴다.
    abstract protected void createItemLog();
    //step3. 아이템을 생성하는 알고리즘
    abstract protected Item createItem();
}
