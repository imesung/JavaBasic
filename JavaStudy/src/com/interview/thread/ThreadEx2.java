package com.interview.thread;

import javax.swing.*;

public class ThreadEx2 {

    public static void main (String [] args) throws Exception {

        ThreadEx2_1 th1 = new ThreadEx2_1();
        th1.start();

        String input = JOptionPane.showInputDialog("아무 값이나 입력하시오.");
        System.out.println("입력 값 : " + input);


    }
}

class ThreadEx2_1 extends Thread {
    public void run() {
        for(int i = 10; i > 0; i--) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch(Exception e) {}
        }
    }
}