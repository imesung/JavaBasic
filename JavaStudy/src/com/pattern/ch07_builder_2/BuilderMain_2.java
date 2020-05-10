package com.pattern.ch07_builder_2;

public class BuilderMain_2 {
    public static void main(String[] args) {
        Computer computer = ComputerBuilder
            .startWithCpu("i6")
            //.start()
            //.setCpu("i7")
            .setRam("8G")
            .build();

        System.out.println(computer.toString());
    }
}
