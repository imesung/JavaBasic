package com.eleventST;

import java.util.Arrays;
import java.util.List;

/*
    * implement the findMaxSum method that, efficiently with respect to time used,
    * returns the largest sum of any two elements in the given list of positive numbers.
    * for example, the largest sum of the list {5, 9, 7, 11} is the sum of the elements 9 and 11, which is 20.
    * */
public class FindMaxSum {
    public static List<Integer> list = Arrays.asList(5, 9, 7, 11);
    public static void main(String[] args) {
        System.out.println(findMaxSum(list));
    }

    public static int findMaxSum(List<Integer> list) {

        //배열로 변경
        int [] arr = chgArr(list);

        //퀵 정렬
        sort(arr, 0, list.size()-1);

        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        return arr[arr.length-2] + arr[arr.length-1];
    }

    public static int [] chgArr(List<Integer> list) {
        int [] arr = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    public static void sort(int[] data, int start, int end){
        int left = start;
        int right = end;
        int pivot = data[(start+end)/2];

        while(left <= right) {
            while(data[left] < pivot) left++;
            while(data[right] > pivot) right--;
            if(left <= right){
                int temp = data[left];
                data[left] = data[right];
                data[right] = temp;
                left++;
                right--;
            }
        }

        if(start < right) {
            sort(data, start, right);
        }
        if(end > left) {
            sort(data, left, end);
        }
    }

}
