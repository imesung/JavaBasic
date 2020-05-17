package com.pattern.ch08_abstractFactory_2.mac;

import com.pattern.ch08_abstractFactory_2.abst.Button;

public class MacButton implements Button {
    @Override
    public void click() {
        System.out.println("맥 버튼");
    }
}
