package com.pattern.ch08_abstractFactory_2.linux;

import com.pattern.ch08_abstractFactory_2.abst.Button;
import com.pattern.ch08_abstractFactory_2.abst.GuiFac;
import com.pattern.ch08_abstractFactory_2.abst.TextArea;

public class LinuxGuiFac implements GuiFac {
    @Override
    public Button createButton() {
        return new LinuxButton();
    }

    @Override
    public TextArea createTextArea() {
        return new LinuxTextArea();
    }
}
