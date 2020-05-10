package com.pattern.ch07_builder;

public class ComputerFactory {
    private BluePrint print;

    public void setBlueprint(BluePrint blueprint) {
        this.print = blueprint;
    }

    public void make() {
        print.setCpu();
        print.setRam();
        print.setStorage();
    }

    public Computer getComputer() {
        return print.getComputer();
    }
}
