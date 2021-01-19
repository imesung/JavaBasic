package com.naver;

public class Cars {

    public static void main(String[] args) {
        int p [] = {4, 4, 2, 4};
        int s [] = {5, 5, 2, 5};

        System.out.println(solution(p, s));
    }


    public static int solution(int[] P, int[] S) {
        // write your code in Java SE 8

        int freeSpace = 0;
        for(int i = 0; i < P.length; i++) {
            freeSpace += S[i] - P[i];
        }

        divide(P, 0, P.length-1);

        int resultCar = 0;
        for(int i = 0; i < P.length; i++) {
            freeSpace = freeSpace - P[i];
            if(freeSpace < 0) {
                break;
            }
            resultCar++;
        }

        return P.length - resultCar;
    }

    public static void divide(int [] array, int start, int end) {
        if(start < end) {
            int mid = (start+end)/2;
            divide(array, start, mid);
            divide(array, mid+1, end);
            mergeSort(array, start, mid, end);
        }
    }

    public static void mergeSort(int [] array, int start, int mid, int end) {
        int [] resultArr = new int[array.length];
        int idx = start;
        int left = start;
        int right = mid+1;
        while(left <= mid && right <= end) {
            if(array[left] <= array[right]) {
                resultArr[idx++] = array[left++];
            } else {
                resultArr[idx++] = array[right++];
            }
        }

        while(left <= mid) {
            resultArr[idx++] = array[left++];
        }

        while(right <= end) {
            resultArr[idx++] = array[right++];
        }

        for(int i = start; i < idx; i++) {
            array[i] = resultArr[i];
        }
    }

}
