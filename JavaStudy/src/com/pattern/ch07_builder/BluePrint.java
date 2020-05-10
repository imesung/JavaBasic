package com.pattern.ch07_builder;

public abstract class BluePrint {
    abstract public void setCpu();
    abstract public void setRam();
    abstract public void setStorage();

    abstract public Computer getComputer();
}
