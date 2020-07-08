package com.pattern.ch16_mediator;

import java.util.ArrayList;
import java.util.List;

public abstract class Mediator {
    protected List<User> users;

    public Mediator() {
        users = new ArrayList<>();
    }

    /**
     * @info user들 단톡방 접속 시 추가
     * @param user
     */
    public boolean addUser(User user) {
        return users.add(user);
    }

    /**
     * @info 중재자 역할(단톡방)로서 메시지를 user들에게 전달
     * @param data
     */
    public abstract void mediate(String data);
}
