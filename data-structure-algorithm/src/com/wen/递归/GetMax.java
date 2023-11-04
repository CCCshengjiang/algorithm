package com.wen.递归;

/*
* 求数组最大值
*
* */

public class GetMax {
    public static void main(String[] args) {
        int[] arr = {1,6,9,8,4,7,3,1,2,19};
        System.out.println(getMax(arr));
    }

    private static int getMax(int[] arr) {
        if(arr.length == 0) {
            throw new RuntimeException("Your array is empty!");
        }
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int left, int right) {
        if(left == right) {
            return arr[left];
        }
        int mid = left + ((right - left) >> 1);
        int leftMax = process(arr, left, mid);
        int rightMax = process(arr, mid + 1, right);
        return Math.max(leftMax,rightMax);
    }
}
