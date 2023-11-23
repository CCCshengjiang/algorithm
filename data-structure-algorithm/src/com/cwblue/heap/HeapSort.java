package com.cwblue.heap;

public class HeapSort {
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int heapSize = arr.length;
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, heapSize);
        }
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    private static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize ? (arr[left] < arr[left + 1] ? left + 1 : left) : left;
            largest = arr[index] < arr[largest] ? largest : index;
            if (largest == index) {
                return;
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {1,9,7,3,6,4,8,2,5};
        heapSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
