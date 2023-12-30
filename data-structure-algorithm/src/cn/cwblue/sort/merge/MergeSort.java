package cn.cwblue.sort.merge;

/*
* 数组左右两边递归，然后左边有序 → 右边有序 → 整体有序
*
* */

public class MergeSort {
    public static void mergeSort(int[] arr) {
        if(arr == null || arr.length <= 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int left, int right) {
        if(left == right) {
            return;
        }
        int mid = left + ((right - left) >> 1);
        process(arr, left, mid);
        process(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int i = 0;
        int p1 = left;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= right) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= right) {
            help[i++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[left + j] = help[j];
        }
    }

    public static void mergeSort2(int[] arr) {
        if(arr == null && arr.length < 2) {
            return;
        }
        int N = arr.length;
        int mergeSize = 1;
        while (mergeSize < N) {
            int L = 0;
            while (L < N) {
                int M = L + mergeSize - 1;
                if (M >= N) {
                    break;
                }
                int R = Math.min(M + mergeSize, N - 1);
                merge(arr,L, M, R);
                L = R + 1;
            }
            if (mergeSize > N / 2){
                break;
            }
            mergeSize <<= 1;
        }

    }

    public static void main(String[] args) {
        int[] arr = {1,5,9,7,3,6,4,8,2};
        mergeSort(arr);
        for (int j : arr) {
            System.out.println(j);
        }

        int[] arr2 = {1,5,9,7,3,6,4,8,2};
        mergeSort2(arr2);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
