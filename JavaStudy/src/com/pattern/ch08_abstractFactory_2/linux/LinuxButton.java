package com.pattern.ch08_abstractFactory_2.linux;

import com.pattern.ch08_abstractFactory_2.abst.Button;

public class LinuxButton implements Button {
    @Override
    public void click() {
        System.out.println("리눅스 버튼");
    }
}
