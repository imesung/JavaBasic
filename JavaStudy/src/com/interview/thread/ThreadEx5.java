package com.interview.thread;

import jdk.jfr.StackTrace;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class ThreadEx5 {
    public static void main(String [] args) throws Exception{
        ThreadEx5_1 t1 = new ThreadEx5_1("Thread1");
        ThreadEx5_2 t2 = new ThreadEx5_2("Thread2");
        t1.start();
        t2.start();
    }
}

class ThreadEx5_1 extends Thread {
    ThreadEx5_1(String name) {
        super(name);
    }

    public void run() {
        try {
            sleep(5000);
        } catch(InterruptedException e) {}
    }
}

class ThreadEx5_2 extends Thread {
    ThreadEx5_2(String name) {
        super(name);
    }

    public void run() {
        Map map = getAllStackTraces();
        Iterator it = map.keySet().iterator();

        int x = 0;
        while(it.hasNext()) {
            Object obj = it.next();
            Thread t = (Thread) obj;
            StackTraceElement[] ste = (StackTraceElement []) (map.get(obj));

            System.out.println("[" + (++x) + "] name : " + t.getName()
                + ", group : " + t.getThreadGroup().getName()
                + ", daemon : " + t.isDaemon());

            for(int i = 0; i < ste.length; i++) {
                System.out.println(ste[i]);
            }

            System.out.println();
        }
    }
}