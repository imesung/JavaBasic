package com.pattern.ch07_builder;

public class BuilderMain {
    public static void main(String[] args) {

        ComputerFactory factory = new ComputerFactory();
        factory.setBlueprint(new LgGramBluePrint());
        //factory.setBlueprint(new SonyBluePrint());

        factory.make();
        Computer computer = factory.getComputer();

        System.out.println(computer.toString());

    }
}
