package com.pattern.ch08_abstractFactory_2.concrete;

import com.pattern.ch08_abstractFactory_2.abst.GuiFac;
import com.pattern.ch08_abstractFactory_2.linux.LinuxGuiFac;
import com.pattern.ch08_abstractFactory_2.mac.MacGuiFac;
import com.pattern.ch08_abstractFactory_2.win.WinGuiFac;

public class FactoryInstance {

    public static GuiFac getGuiFac(int type) {

        if(type == 0) {
            return new MacGuiFac();
        } else if(type == 1) {
            return new LinuxGuiFac();
        } else if(type == 2) {
            return new WinGuiFac();
        } else {
            return null;
        }
    }
}
