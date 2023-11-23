package com.cwblue.heap;

/*
* 已知一个几乎有序的数组。
* 几乎有序是指，如果把数组排好序的话，每个元素移动的的距离一定不超过K，并且K是相对于数组长度来说是比较小的
* 请选择一个合适的排序册罗，对这个数组进行排序。
*
* */

import java.util.PriorityQueue;

public class SortArrayDistanceLessK {
    public static void sortArrayDistanceLessk(int[] arr, int K) {
        if (arr == null || arr.length < 2) {
            return;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        for (;index < K; index++) {
            heap.add(arr[index]);
        }
        int i = 0;
        for (; index < arr.length; index++, i++) {
            heap.add(index);
            arr[i] = heap.poll();
        }
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }
}
