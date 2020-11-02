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
        list.sort(null);
        return list.get(list.size()-1) + list.get(list.size()-2);
    }
}
