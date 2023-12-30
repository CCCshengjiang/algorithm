package cn.cwblue.sort.merge;

/*
* 求小数字和：数组中左边比这个数字小的数的和
*
* */

public class GetSmallSum {
    private static int getSmallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr == null ? 0 : arr[0];
        }
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        return process(arr, left, mid) + process(arr, mid + 1, right) + merge(arr, left, mid, right);
    }

    private static int merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int sum = 0;
        int i = 0;
        int p1 = left;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= right) {
            sum += arr[p1] < arr[p2] ? arr[p1] * (right - p2 + 1) : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
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
        return sum;
    }

    public static void main(String[] args) {
        int[] arr1 = {1,9,7,3,5,4,6,8,2};
        int[] arr2 = {1,9,7,3,5,4,6,8,2};

        int sum1 = 0;
        // 对数器
        for (int i = 1; i < arr1.length; i++) {
            for (int j = 0; j < i; j++) {
                if(arr1[j] < arr1[i]) {
                    sum1 += arr1[j];
                }
            }
        }

        int sum2 = getSmallSum(arr2);

        System.out.println(sum1);
        System.out.println(sum2);
        System.out.println(sum1 == sum2);

    }
}
