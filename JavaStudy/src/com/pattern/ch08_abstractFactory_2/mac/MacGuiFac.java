package com.pattern.ch08_abstractFactory_2.mac;

import com.pattern.ch08_abstractFactory_2.abst.Button;
import com.pattern.ch08_abstractFactory_2.abst.GuiFac;
import com.pattern.ch08_abstractFactory_2.abst.TextArea;

import javax.crypto.Mac;

public class MacGuiFac implements GuiFac {
    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public TextArea createTextArea() {
        return new MacTextArea();
    }
}
