package com.effective.generic;

class MyList<T> {
    private Object [] obj;
    private int index;

    public MyList() {
        obj = new Object[10];
        index = 0;
    }

    public void add(T t) {
        this.obj[index++] = t;
    }

    public T get() {
        return (T) obj[index--];
    }
}

public class CompileTest {
    public static void main(String[] args) {
        MyList<String> myList = new MyList<String>();
        myList.add("제네릭");
        myList.add("스터디");
        //myList.add(1);  //컴파일 오류 발생
        //제네릭을 사용하여 컴파일 타임에 타입 검사가 가능.
    }
}
