package com.codeTest;

import java.util.Scanner;

//주어진 문자열 중 가장 많이 나온 알파벳을 출력하세요. 단 같은 횟수일 경우 빠른 알파벳을 출력하세요.
//aabcde => a, abbccddd => d, abbccdd => b, abcabcd => a
public class MaxAlphabet {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

       String str = sc.nextLine();
       String alpha = "abcdefghijklmnopqrstuvwxyz";
       int [] result = new int [26];

       for(int i=0; i < str.length(); i++) {
           result[alpha.indexOf(str.charAt(i))]++;
       }

       for(int i = 0; i<result.length; i++) {
           System.out.print(result[i]);
       }

        System.out.println();

       int max = 0;
       int resultI = 0;
       for(int i = 0; i < result.length; i++) {
           if(max < result[i]) {
               max = result[i];
               resultI = i;
           }
       }

       System.out.println(alpha.charAt(resultI));

    }
}
