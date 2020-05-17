package com.pattern.ch08_abstractFactory_2;

import com.pattern.ch08_abstractFactory_2.abst.Button;
import com.pattern.ch08_abstractFactory_2.abst.GuiFac;
import com.pattern.ch08_abstractFactory_2.abst.TextArea;
import com.pattern.ch08_abstractFactory_2.linux.LinuxGuiFac;

public class AbstFactory_2_Main {
    public static void main(String[] args) {
        GuiFac fac = new LinuxGuiFac();
        Button button = fac.createButton();
        TextArea textArea = fac.createTextArea();

        button.click();
        System.out.println(textArea.getText());
    }
}
