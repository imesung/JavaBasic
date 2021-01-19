package com.algorithmStudy.sort;

public class InsertionSort {
    public static void main(String [] args) {
        int data [] = {33, 21, 5, 12, 45};
        sort(data);

        for(int i = 0; i < data.length; i++) {
            System.out.print(data[i] + " ");
        }
    }

    public static void sort(int [] data) {
        for(int i = 1; i < data.length; i++) {
            int cur = i;
            for(int j = i-1; j >= 0; j--) {
                int temp1 = data[cur];
                int temp2 = data[j];
                if(data[cur] < data[j]) {
                    int temp = data[cur];
                    data[cur] = data[j];
                    data[j] = temp;
                    cur--;
                }

            }
        }
    }
}
