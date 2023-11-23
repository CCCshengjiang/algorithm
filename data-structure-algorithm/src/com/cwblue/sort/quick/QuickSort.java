package com.cwblue.sort.quick;

public class QuickSort {
    private static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        swap(arr, left + (int)(Math.random() * (right - left + 1)), right);
        int[] partition = partition(arr, left, right);
        process(arr, left, partition[0] - 1);
        process(arr, partition[1] + 1, right);
    }

    private static int[] partition(int[] arr, int left, int right) {
        int index = left;
        int small = left - 1;
        int big = right;
        while (index < big) {
            if (arr[index] == arr[right]) {
                index++;
            } else if (arr[index] < arr[right]) {
                swap(arr, index++, ++small);
            }else {
                swap(arr, index, --big);
            }
        }
        swap(arr, right, big);
        return new int[] {++small, big};
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,8,9,5};
        quickSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

}
