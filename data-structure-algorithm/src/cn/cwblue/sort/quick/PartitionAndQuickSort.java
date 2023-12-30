package cn.cwblue.sort.quick;

public class PartitionAndQuickSort {
    private static void netherlandsFlag(int[] arr, int num) {
        if(arr == null || arr.length < 2) {
            return;
        }
        int i = 0;
        int p1 = -1;
        int p2 = arr.length;
        while (i < p2) {
            if(arr[i] < num) {
                swap(arr, i++, ++p1);
            }else if (arr[i] > num) {
                swap(arr, i, --p2);
            }else {
                i++;
            }
        }
    }

    private static int[] netherlandsFlag2(int[] arr, int left, int right) {
        if(left > right) {
            return new int[] {-1, -1};
        }
        if(left == right) {
            return new int[] {left, right};
        }
        int small = left - 1;
        int big = right;
        int index = left;
        while (index < big) {
            if(arr[index] == arr[right]) {
                index++;
            }else if (arr[index] < arr[right]) {
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
        int[] arr = {1,9,7,5,8,5,3,2,4,6,5};
        int[] arr2 = {1,9,7,5,8,5,3,2,4,6,5};

        netherlandsFlag(arr, 5);
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();

        int[] ans = netherlandsFlag2(arr2, 0, arr2.length - 1);
        for (int j : arr2) {
            System.out.print(j + " ");
        }
        System.out.println();
        System.out.println(ans[0] + " " + ans[1]);

    }

}
