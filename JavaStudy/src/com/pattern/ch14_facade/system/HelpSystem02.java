package com.pattern.ch14_facade.system;

class HelpSystem02 {
    public HelpSystem02() {
        System.out.println("Call Constructor : " + getClass().getSimpleName());
    }

    public void process() {
        System.out.println("Call Process : " + getClass().getSimpleName());
    }
}
