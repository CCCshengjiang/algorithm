package com.cwblue.queue;

// 栈用数组表示(环形数组)
public class RingArray {
    public static class MyQueue {
        private int[] arr;
        private int pushI;
        private int pollI;
        private int size;
        private final int limit;

        // 构造方法
        public  MyQueue(int limit){
            arr = new int[limit];
            pushI = 0;
            pollI = 0;
            size = 0;
            this.limit = limit;
        }

        // 进队列
        public void push(int data) {
            if(size == limit) {
                throw new RuntimeException("队列满了，不能加了！");
            }
            size++;
            arr[pushI] = data;
            pushI = nextIndex(pushI);
        }

        // 出队列
        public int pop() {
            if(size == 0) {
                throw new RuntimeException("队列内没有元素！");
            }
            size--;
            int ans = arr[pollI];
            pollI = nextIndex(pollI);
            return ans;
        }

        // 计算索引
        private int nextIndex(int index) {
            return size < limit - 1 ? index + 1 : 0;
        }
    }
}
