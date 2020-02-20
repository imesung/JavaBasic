package com.interview.thread;

import javax.swing.*;
import java.sql.SQLOutput;

public class ThreadEx1 {
    public static void main (String [] args) throws Exception {
        String input = JOptionPane.showInputDialog("아무 값이나 입력하시오.");
        System.out.println("입력 값 : " + input);

        for(int i = 10; i > 0; i--) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch(Exception e) {}
        }
    }
}
