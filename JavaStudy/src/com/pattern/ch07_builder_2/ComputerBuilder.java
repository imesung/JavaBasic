package com.pattern.ch07_builder_2;

public class ComputerBuilder {
    private Computer computer;
    private ComputerBuilder(){
        this.computer = new Computer("default", "default" , "default");
    }

    public static ComputerBuilder start() {
        return new ComputerBuilder();
    }

    //CPU를 시작과 동시에 넣겠다.
    public static ComputerBuilder startWithCpu(String cpu) {
        ComputerBuilder builder = new ComputerBuilder();
        builder.computer.setCpu(cpu);
        return builder;
    }

    public ComputerBuilder setCpu(String cpu) {
        computer.setCpu(cpu);
        return this;
    }

    public ComputerBuilder setRam(String ram) {
        computer.setRam(ram);
        return this;
    }

    public ComputerBuilder setStorage(String storage) {
        computer.setStorage(storage);
        return this;
    }

    public Computer build() {
        return this.computer;
    }
}
