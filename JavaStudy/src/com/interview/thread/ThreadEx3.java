package com.interview.thread;

public class ThreadEx3 {
    public static void main(String [] args) {
        ThreadEx3_1 th1 = new ThreadEx3_1();
        ThreadEx3_2 th2 = new ThreadEx3_2();

        th2.setPriority(7);
        System.out.println("Priority of th1 (-) : " + th1.getPriority());
        System.out.println("Priority of th2 (|) : " + th2.getPriority());

        th1.start();
        th2.start();

    }
}

class ThreadEx3_1 extends Thread {
    public void run() {
        for(int i = 0; i < 300; i++) {
            System.out.print("-");
            for(int x = 0; x < 1000000; x++);
        }
    }
}

class ThreadEx3_2 extends Thread {
    public void run() {
        for(int i = 0; i < 300; i++) {
            System.out.print("|");
            for(int x = 0; x < 1000000; x++);
        }
    }
}