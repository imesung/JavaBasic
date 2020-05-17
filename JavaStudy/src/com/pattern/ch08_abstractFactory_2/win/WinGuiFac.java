package com.pattern.ch08_abstractFactory_2.win;

import com.pattern.ch08_abstractFactory_2.abst.Button;
import com.pattern.ch08_abstractFactory_2.abst.GuiFac;
import com.pattern.ch08_abstractFactory_2.abst.TextArea;

public class WinGuiFac implements GuiFac {
    @Override
    public Button createButton() {
        return new WinButton();
    }

    @Override
    public TextArea createTextArea() {
        return new WinTextArea();
    }
}
