package com.codeTest2.solution1;

//파이프 미로 퀴즈
//물을 연결하는 파이프가 존재
//파이프는 번호마다 모양이 다름
//파이프 안에 있는 물은 직진으로만 가고, 모두가 뚫려있어도 직진으로만 감.
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int [][] pipeMap = {};
        
        pipeFunc(pipeMap, 1, 1);
    }
    
    public static int pipeFunc(int [][] pipeMap, int sX, int sY) {
        int xDir = {0, 1, 0, -1};   //좌하우상
        int ydir = {1, 0, -1, 0};   //좌하우상
        
        //start X = sX, start Y = sY
        
        while(true) {
        }
    }
}
