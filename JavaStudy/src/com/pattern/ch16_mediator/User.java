package com.pattern.ch16_mediator;

public abstract class User {
    private String name;
    private Mediator mediator;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * @info Ex. 단톡방 접속
     * @param mediator
     */
    public boolean join(Mediator mediator) {
        this.mediator = mediator;
        return mediator.addUser(this);
    }

    /**
     * @info 단톡방에 메시지 전달.
     * @param data
     */
    public void sendData(String data) {
        mediator.mediate(data);
    }


    /**
     * @info 단톡방에서 user에게 메시지 전달 시 채팅방에 따라 메시지를 각 user에 맞게 노출
     * @param data
     */
    abstract public void handle(String data);
}
