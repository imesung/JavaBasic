package com.pattern.ch18_memento.abc;

public class Originator {

    private String state;

    /**
     *
     * @info 메멘토에 상태 저장
     */
    public Memento createMemento() {
        return new Memento(state);
    }

    /**
     *
     * @info 메멘토를 활용하여 상태 복구
     */
    public void resotreMemento(Memento memento) {
        this.state = memento.getState();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
