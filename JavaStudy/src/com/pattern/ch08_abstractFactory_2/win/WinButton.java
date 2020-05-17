package com.pattern.ch08_abstractFactory_2.win;

import com.pattern.ch08_abstractFactory_2.abst.Button;

public class WinButton implements Button {
    @Override
    public void click() {
        System.out.println("윈도우 버튼");
    }
}
