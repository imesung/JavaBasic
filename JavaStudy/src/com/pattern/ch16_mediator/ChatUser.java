package com.pattern.ch16_mediator;

public class ChatUser extends User{
    public ChatUser(String name) {
        super(name);
    }

    @Override
    public void handle(String data) {
        System.out.println("[채팅 채널]");
        System.out.println(this.getName() + data);
    }
}
