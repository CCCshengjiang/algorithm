package com.wen.桶排序.基数排序;

import java.util.Arrays;

public class RadixSort {
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        radixSort(arr, 0, arr.length - 1, maxBits(arr));
    }

    // 求数组中最大的数有几位：1000  --->  4位
    private static int maxBits(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int ans = 0;
        while (max != 0) {
            max /= 10;
            ans++;
        }
        return ans;
    }

    // 基数排序的实现
    public static void radixSort(int[] arr, int left, int right, int digit) {
        final int radix = 10;
        int[] help = new int[right - left + 1];
        for (int d = 1; d <= digit; d++) {
            int[] count = new int[radix];
            int i = 0;
            int j = 0;
            for (i = left; i <= right; i++) {
                j= getDigit(arr[i], d);
                count[j]++;
            }
            for (i = 1; i < radix; i++) {
                count[i] += count[i - 1];
            }
            for (i = right; i >= left; i--) {
                j = getDigit(arr[i], d);
                help[count[j] - 1] = arr[i];
                count[j]--;
            }
            for (i = left, j = 0; i <= right; i++, j++) {
                arr[i] = help[j];
            }
        }
    }

    // num这个数的digit位的数字：(123，1) ---> 3
    private static int getDigit(int num, int digit) {
        return ((num / (int) Math.pow(10, digit - 1)) % 10);
    }

    // 对数器测试
    public static void comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        Arrays.sort(arr);
    }

    public static int generateRandomNum(int num) {
        return (int) (Math.random() * num + 1);
    }

    public static int[] generateRandomArr(int arrLen, int num) {
        int length = (int) (Math.random() * arrLen + 1);
        int[] ans = new int[length];
        for (int i = 0; i < length; i++) {
            ans[i] = generateRandomNum(num);
        }
        return ans;
    }
    public static void main(String[] args) {
        int num = 1000;
        int arrLen = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = generateRandomArr(arrLen, num);
            int[] arr2 = new int[arr1.length];
            for (int j = 0; j < arr1.length; j++) {
                arr2[j] = arr1[j];
            }
            radixSort(arr1);
            comparator(arr2);
            for (int j = 0; j < arr1.length; j++) {
                if (arr2[j] != arr1[j]) {
                    System.out.println("Oops!");
                }
            }
        }
        System.out.println("Finish!");
    }
}
