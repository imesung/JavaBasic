package com.algorithmStudy.sort;

public class MergeSort {
    public static void main(String[] args) {
        int [] array = {10, 3, 56, 23, 1, 23, 6, 7};
        divide(array, 0, (array.length-1));
        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    public static void divide(int [] intArray, int start, int end) {
        if(start < end) {
            int mid = (start+end)/2;
            divide(intArray, start, mid);
            divide(intArray, mid+1, end);
            mergeSort(intArray, start, mid, end);
        }
    }

    public static void mergeSort(int [] sortArray, int start, int mid, int end) {
        int [] resultArr = new int[sortArray.length];
        int idx = start;
        int left = start;
        int right = mid+1;
        while(left <= mid && right <= end) {
            if(sortArray[left] <= sortArray[right]) {
                resultArr[idx++] = sortArray[left++];
            } else {
                resultArr[idx++] = sortArray[right++];
            }
        }

        while(left <= mid) {
            resultArr[idx++] = sortArray[left++];
        }

        while(right <= end) {
            resultArr[idx++] = sortArray[right++];
        }

        for(int i = start; i < idx; i++) {
            sortArray[i] = resultArr[i];
        }
    }
}
