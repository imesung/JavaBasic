package com.effective.extend;

public class User extends Group{

    public Group getGroup(String name) {
        return new Group(name);
    }

}
