package com.interview.thread.sleep;

public class SleepThread {
    public static void main(String [] args) {
        SleepThread_1 th1 = new SleepThread_1();
        SleepThread_2 th2 = new SleepThread_2();

        th1.start();
        th2.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }

        System.out.println("main 종료");
    }
}

class SleepThread_1 extends Thread {
    public void run() {
        for(int i = 0; i<300; i++) {
            System.out.print("-");
        }
        System.out.println("th_1 종료");
    }
}

class SleepThread_2 extends Thread {
    public void run() {
        for(int i = 0; i<300; i++) {
            System.out.print("|");
        }
        System.out.println("th_2 종료");
    }
}
