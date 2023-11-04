package com.wen.AAA自练习.改进堆;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class MyHeap<T> {
    private ArrayList<T> heap;
    private HashMap<T,Integer> heapIndex;
    private int heapSize;
    private Comparator<? super T> comparator;
    public MyHeap(Comparator<? super T> comparator) {
        heap = new ArrayList<>();
        heapIndex = new HashMap<>();
        heapSize = 0;
        this.comparator = comparator;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int getHeapSize() {
        return heapSize;
    }

    public void push(T value) {
        heap.add(value);
        heapIndex.put(value, heapSize);
        heapInsert(heapSize++);
    }

    public T pop() {
        T ans = heap.get(0);
        int end = heapSize - 1;
        swap(0, end);
        heap.remove(end);
        heapIndex.remove(ans);
        heapify(0, --heapSize);
        return ans;
    }

    public void resign(T value) {
        int valueIndex = heapIndex.get(value);
        heapify(valueIndex, heapSize);
        heapInsert(valueIndex);
    }

    private void heapify(int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int smallest = left + 1 < heapSize ? (comparator.compare(heap.get(left),heap.get(left + 1)) < 0 ? left : left + 1 ) : left;
            smallest = comparator.compare(heap.get(index), heap.get(smallest)) < 0 ? index : smallest;
            if (smallest == index) {
                return;
            }
            swap(index,smallest);
            index = smallest;
            left = index * 2 - 1;
        }
    }

    private void heapInsert(int index) {
        while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void swap(int i, int j) {
        T o1 = heap.get(i);
        T o2 = heap.get(j);
        heap.set(i, o2);
        heap.set(j, o1);
        heapIndex.put(o1, j);
        heapIndex.put(o2, i);
    }


}
