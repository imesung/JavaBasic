package com.pattern.ch08_abstractFactory_2.win;

import com.pattern.ch08_abstractFactory_2.abst.TextArea;

public class WinTextArea implements TextArea {
    @Override
    public String getText() {
        return "윈도우 TextArea";
    }
}
