package com.wen.AAA自练习.基数排序;

import java.util.Arrays;

public class RadixSort {
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        radixSort(arr, 0, arr.length - 1, myBits(arr));
    }

    private static int myBits(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int ans = 0;
        while (max > 0) {
            ans++;
            max /= 10;
        }
        return ans;
    }

    public static void radixSort(int[] arr, int left, int right, int bits) {
        final int radix = 10;
        int i = 0;
        int j = 0;
        int[] help = new int[right - left + 1];
        int b = 0;
        for (i = 1; i <= bits; i++) {
            int[] count = new int[radix];
            for (j = left; j <= right; j++) {
                b = getBits(arr[j], i);
                count[b]++;
            }
            for (j = 1; j < radix; j++) {
                count[j] += count[j - 1];
            }
            for (j = right; j >= left; j--) {
                b = getBits(arr[j], i);
                help[count[b] - 1] = arr[j];
                count[b]--;
            }
            for (j = left; j <= right; j++) {
                arr[j] = help[j];
            }
        }
    }

    private static int getBits(int num, int bits) {
        return ((num / (int) Math.pow(10, bits - 1)) % 10);
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
            radixSort(arr1);
            comparator(arr2);
            for (int j = 0; j < arr1.length; j++) {
                if (arr1[j] != arr2[j]) {
                    System.out.println(arr1[j]);
                    System.out.println(arr2[j]);
                    System.out.println("Oops!");
                }
            }
        }
        System.out.println("Finish!");
    }
}
