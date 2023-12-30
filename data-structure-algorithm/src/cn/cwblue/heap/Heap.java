package cn.cwblue.heap;

public class Heap {
    public static class MyMaxHeap {
        private int[] heap;
        private final int limit;
        private int heapSize;
        public MyMaxHeap(int limit) {
            heap = new int[limit];
            this.limit = limit;
            heapSize = 0;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        public void push(int value) {
            if(heapSize == limit) {
                throw new RuntimeException("Heap is full!");
            }
            heap[heapSize] = value;
            heapInsert(heap, heapSize++);
        }

        public int pop() {
            if(heapSize == 0) {
                throw new RuntimeException("Heap is empty!");
            }
            int ans = heap[0];
            swap(heap, 0, --heapSize);
            heapify(heap, 0, heapSize);
            return ans;
        }

        private void heapify(int[] heap, int index, int heapSize) {
            int left = index * 2 + 1;
            while (left < heapSize) {
                int largest = left + 1 > heapSize ? left : (heap[left] > heap[left + 1] ? left : left + 1);
                largest = heap[largest] > heap[index] ? largest : index;
                if (largest == index) {
                    return;
                }
                swap(heap, index, largest);
                index = largest;
                left = index * 2 - 1;
            }
        }

        private void heapInsert(int[] heap, int index) {
            while (heap[index] > heap[(index - 1) / 2]) {
                swap(heap, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void swap(int[] heap, int i, int j) {
            int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }
    }

    // 参照组：暴力
    public static class RightMaxHeap {
        private int[] heap;
        private final int limit;
        private int heapSize;

        public RightMaxHeap(int limit) {
            heap = new int[limit];
            this.limit = limit;
            heapSize = 0;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        public void push(int value) {
            if(heapSize == limit) {
                throw new RuntimeException("Heap is full!");
            }
            heap[heapSize++] = value;
        }

        public int pop() {
            if(heapSize == 0) {
                throw new RuntimeException("Heap is empty!");
            }
            int maxIndex = 0;
            for (int i = 1; i < heap.length; i++) {
                if(heap[maxIndex] < heap[i]) {
                    maxIndex = i;
                }
            }
            int ans = heap[maxIndex];
            heap[maxIndex] = heap[--heapSize];
            return ans;
        }
    }

    public static void main(String[] args) {
        // 写对数器验证堆结构
        int value = 1000;
        int limit = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int curLimit = (int)(Math.random() * limit) + 1;
            RightMaxHeap test = new RightMaxHeap(curLimit);
            MyMaxHeap my = new MyMaxHeap(curLimit);
            int curOpTimes = (int)(Math.random() * limit);
            for (int j = 0; j < curOpTimes; j++) {
                if (my.isEmpty() != test.isEmpty()) {
                    System.out.println("Oops!");
                }
                if (my.isFull() != test.isFull()) {
                    System.out.println("Oops!");
                }
                if (my.isEmpty()) {
                    int curValue = (int)(Math.random() * value);
                    my.push(value);
                    test.push(value);
                }else if (my.isFull()) {
                    if(my.pop() != test.pop()) {
                        System.out.println("Oops!");
                    }
                }else {
                    if(Math.random() < 0.5) {
                        int curValue = (int)(Math.random() * value);
                        my.push(value);
                        test.push(value);
                    }else {
                        if(my.pop() != test.pop()) {
                            System.out.println("Oops!");
                        }
                    }
                }
            }
        }
        System.out.println("Finish!");
    }
}
