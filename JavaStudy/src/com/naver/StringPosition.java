package com.naver;

import java.util.ArrayList;
import java.util.List;

public class StringPosition {
    public static void main(String[] args) {

        //같은 위치에 같은 값이 있으면 리턴한다. 이 때, 있는 곳과 있는 위치를 리턴


        //String [] s = {"abc", "bca", "dbe"};
        String [] s = {"zzzz", "ferz", "adsr", "fgtd"};
        //String [] s = {"gr", "sd", "rg"};
        //String [] s = {"bdafg", "ceagi"};
        int [] result = solution(s);

        for(int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }

    public static int[] solution(String[] S) {
        List<Integer> array = new ArrayList<>();

        for (int j = 0; j < S[0].length(); j++) {
            char first = S[0].charAt(j);
            for (int i = 1; i < S.length; i++) {
                if (first == S[i].charAt(j)) {
                    //처음 위치 문자와 같은 값이 나오면 0으로 세팅
                    if (array.isEmpty()) {
                        array.add(0);
                    }
                    //비교하고 있는 위치 추가
                    array.add(i);
                }
            }

            //현재 비교하고 있는 값 추가
            if (!array.isEmpty()) {
                array.add(j);
                break;
            }
        }

        int[] resultArr = new int[array.size()];
        for (int i = 0; i < array.size(); i++) {
            resultArr[i] = array.get(i);
        }

        return resultArr;
    }
}
