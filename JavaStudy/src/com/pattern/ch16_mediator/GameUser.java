package com.pattern.ch16_mediator;

public class GameUser extends User{

    public GameUser(String name) {
        super(name);
    }

    @Override
    public void handle(String data) {
        System.out.println("[게임 채널]");
        System.out.println(this.getName() + data);
    }
}
