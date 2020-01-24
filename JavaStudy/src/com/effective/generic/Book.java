package com.effective.generic;

public class Book<T> {
    private Object obj;
    public Object get() {
        return obj;
    }
    public void set(Object obj) {
        this.obj = obj;
    }
}
