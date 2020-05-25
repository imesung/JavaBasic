package com.pattern.ch08_abstractFactory_prst;

public class HyundaiDoor extends Door {
    protected void doClose() { System.out.println("close Hyundai Door"); }
    protected void doOpen() { System.out.println("open Hyundai Door"); }
}
