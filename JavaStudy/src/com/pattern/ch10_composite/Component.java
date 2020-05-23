package com.pattern.ch10_composite;

//구현을 공유해야할 필요가 있으면 추상클래스, 공유할 필요가 없으면 인터페이스
abstract public class Component {
    private String name;

    public Component(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
