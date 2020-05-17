package com.pattern.ch08_abstractFactory_2.linux;

import com.pattern.ch08_abstractFactory_2.abst.TextArea;
import org.w3c.dom.Text;

public class LinuxTextArea implements TextArea {

    @Override
    public String getText() {
        return "리눅스 TextArea";
    }
}
