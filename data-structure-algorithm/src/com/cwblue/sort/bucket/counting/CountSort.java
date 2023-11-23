package com.cwblue.sort.bucket.counting;

import java.util.Arrays;

public class CountSort {
    public static void countSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int[] bucket = new int[max + 1];
        for (int a : arr) {
            bucket[a]++;
        }
        int index = 0;
        for (int i = 0; i < bucket.length; i++) {
            while (bucket[i]-- > 0) {
                arr[index++] = i;
            }
        }
    }

    public static void comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        Arrays.sort(arr);
    }

    public static int[] generateRandomArr(int arrLen) {
        int[] ans = new int[arrLen];
        for (int i = 0; i < arrLen; i++) {
            int num = (int) (Math.random() * 1000 + 1);
            ans[i] = num;
        }
        return ans;
    }
    public static void main(String[] args) {
        int arrLength = 1000;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int arrLen = (int) (Math.random() * arrLength + 1);
            int[] arr1 = generateRandomArr(arrLen);
            int[] arr2 = new int[arr1.length];
            for (int j = 0; j < arr1.length; j++) {
                arr2[j] = arr1[j];
            }
            countSort(arr1);
            comparator(arr2);
            for (int j = 0; j < arr1.length; j++) {
                if (arr1[j] != arr2[j]) {
                    System.out.println("Oops!");
                }
            }
        }
        System.out.println("Finish!");
    }
}
