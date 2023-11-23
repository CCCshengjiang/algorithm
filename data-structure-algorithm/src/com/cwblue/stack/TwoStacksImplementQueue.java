package com.cwblue.stack;

import java.util.Stack;

/*
* 用栈结构实现队列
*
* */

public class TwoStacksImplementQueue {
    public static class TwoStacksQueue {
        private Stack<Integer> stackPush;
        private Stack<Integer> stackPull;

        public TwoStacksQueue() {
            stackPull = new Stack<>();
            stackPush = new Stack<>();
        }

        // 第一个栈元素倒入第二个栈
        private void pushToPull() {
            if(stackPull.isEmpty()) {
                while (!stackPush.isEmpty()) {
                    stackPull.push(stackPush.pop());
                }
            }
        }

        // 进队列
        public void push(int data) {
            stackPush.push(data);
        }

        // 出队列
        public int pop() {
            if(stackPush == null && stackPull == null) {
                throw new RuntimeException("Your stack is Empty!");
            }
            pushToPull();
            return stackPull.pop();
        }

        // 返回队尾元素
        public int peek() {
            if(stackPush == null && stackPull == null) {
                throw new RuntimeException("Your stack is Empty!");
            }
            pushToPull();
            return stackPull.peek();
        }

    }

    public static void main(String[] args) {
        TwoStacksQueue q = new TwoStacksQueue();
        q.push(1);
        q.push(2);
        q.push(3);
        q.push(4);

        System.out.println(q.pop());
        System.out.println(q.pop());
        System.out.println(q.pop());

        q.push(5);
        q.push(6);
        q.push(7);

        System.out.println(q.pop());
        System.out.println(q.pop());
        System.out.println(q.pop());
        System.out.println(q.pop());
    }

}
