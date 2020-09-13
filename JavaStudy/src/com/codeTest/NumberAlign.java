package com.codeTest;

import java.sql.Array;
import java.util.Arrays;
import java.util.Scanner;

//주어진 숫잘들을 큰 숫자 + 작은 숫자 + 2번째 큰 숫자 + 2번째 작은 숫자 순으로 정렬하세요.
//Ex. 35, 2, 4, 0, 22 => 35, 0, 22, 4, 0
public class NumberAlign {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();
        str = str.replaceAll(" ", "");
        System.out.println(str);
        String[] strArr = str.split(",");

        int [] intArr = new int [strArr.length];
        for(int i = 0; i < strArr.length; i++) {
            intArr[i] = Integer.valueOf(strArr[i]);
        }

        Arrays.sort(intArr);

        for(int i = 0 ; i < intArr.length; i++) {
            System.out.println(intArr[i]);
        }


        int [] resultArr = new int [intArr.length];
        for(int i = 0; i < resultArr.length; i++) {

        }
    }


}
