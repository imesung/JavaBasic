package com.codeTest2.solution4;

public class Solution {
    public static void main(String[] args) {
        int [][] arr = {{1, 4}, {3, 4}, {3, 10}};
        int [] result = solution(arr);

        System.out.println(arr[0][0]);
        System.out.println(result[0] + ", " + result[1]);
    }

    public static int[] solution(int[][] v) {

        int[] answer = new int [2];

        for(int i = 0; i < v[i].length; i++) {
            if(v[0][i] == v[1][i]) {
                answer[i] = v[2][i];
            } else if(v[0][i] == v[2][i]) {
                answer[i] = v[1][i];
            } else {
                answer[i] = v[0][i];
            }
        }

        return answer;
    }
}
