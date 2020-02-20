package com.interview.thread;

public class ThreadEx4 implements Runnable {
    static boolean autoSave = false;

    public static void main(String [] args) {
        Thread t = new  Thread(new ThreadEx4());
        //t.setDaemon(true);
        t.start();

        for(int i = 1; i < 5; i++) {
            try{
                Thread.sleep(1000);
            } catch(InterruptedException e) {}
            System.out.println(i);

            if(i == 2) {
                autoSave = true;
            }
        }
        System.out.println("시스템 종료");
    }

    public void run() {
        while(true) {
            try {
                Thread.sleep(3000);
            } catch(InterruptedException e) {}
            if(autoSave) {
                autoSave();
            }
        }
    }

    public void autoSave() {
        System.out.println("작업파일이 자동저장되었습니다.");
    }
}
