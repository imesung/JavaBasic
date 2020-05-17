package com.pattern.ch08_abstractFactory_2.mac;

import com.pattern.ch08_abstractFactory_2.abst.TextArea;
import org.w3c.dom.Text;

public class MacTextArea implements TextArea {
    @Override
    public String getText() {
        return "맥 TextArea";
    }
}
