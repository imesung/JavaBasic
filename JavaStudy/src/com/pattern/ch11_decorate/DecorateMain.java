package com.pattern.ch11_decorate;

import java.util.Scanner;

public class DecorateMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        IBeverage beverage = new Base();
        boolean done = false;
        while(!done) {
            System.out.println("음료 현재 가격 : " + beverage.getTotalPrice());
            System.out.println("선택 : 1. 샷 추가 / 2. 우유 추가");
            int inputVal = sc.nextInt();
            if(inputVal == 0) {
                done = true;
            } else if(inputVal == 1) {
                beverage = new Espresso(beverage);
            } else if(inputVal == 2) {
                beverage = new Milk(beverage);
            }
        }

        System.out.println("음료 가격 : " + beverage.getTotalPrice());
        sc.close();
    }
}
