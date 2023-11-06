package com.wen.AAA自练习.计数排序;

import java.util.Arrays;
import java.util.Random;

public class CountSort {
    public static void countSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int max = arr[0];
        for (int i : arr) {
            max = Math.max(max, i);
        }
        int[] bucket = new int[max + 1];
        for (int i : arr) {
            bucket[i]++;
        }
        int j = 0;
        for (int i = 0; i < bucket.length; i++) {
            while (bucket[i]-- > 0) {
                arr[j++] = i;
            }
        }
    }

    public static void comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        Arrays.sort(arr);
    }

    public static int[] generateRandomArr(int numLimit, int arrLen) {
        arrLen = (int) (Math.random() * arrLen + 1);
        int[] arr = new int[arrLen];
        for (int i = 0; i < arrLen; i++) {
            int num = (int) (Math.random() * numLimit + 1);
            arr[i] = num;
        }
        return arr;
    }

    public static void main(String[] args) {
        int numLimit = 10000;
        int arrLen = 1000;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = generateRandomArr(numLimit, arrLen);
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
