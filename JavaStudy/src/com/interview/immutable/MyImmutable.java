package com.interview.immutable;

public class MyImmutable {
    private final StringBuilder name;

    public MyImmutable(StringBuilder name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyImmutable{" +
                "name='" + name + '\'' +
                '}';
    }
}
