package com.pattern.ch18_memento;

import com.pattern.ch18_memento.abc.Memento;
import com.pattern.ch18_memento.abc.Originator;

import java.util.Stack;

public class Application {

    public static void main(String[] args) {

        Stack<Memento> mementos = new Stack<>();

        Originator originator = new Originator();

        originator.setState("state 1");
        mementos.push(originator.createMemento());
        originator.setState("state 2");
        mementos.push(originator.createMemento());
        originator.setState("state 3");
        mementos.push(originator.createMemento());
        originator.setState("final state");
        mementos.push(originator.createMemento());

        originator.resotreMemento(mementos.pop());
        System.out.println(originator.getState());  //final state
        originator.resotreMemento(mementos.pop());
        System.out.println(originator.getState());  //state 1
        originator.resotreMemento(mementos.pop());
        System.out.println(originator.getState());  //state 2
        originator.resotreMemento(mementos.pop());
        System.out.println(originator.getState());  //state 3

        /////////////////////////////////////////////
        //사용자가 필요한 것만 저장 후 복구
        Stack<Memento> customMemento = new Stack<>();
        originator.setState("custom1");
        customMemento.push(originator.createMemento());
        originator.setState("custom2");
        originator.setState("custom3");
        originator.setState("custom4");
        System.out.println(originator.getState());
        originator.resotreMemento(customMemento.pop());
        System.out.println(originator.getState());

    }

}
