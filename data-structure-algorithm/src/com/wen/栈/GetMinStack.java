package com.wen.栈;

import java.util.Stack;

/*
 * 实现一个特殊的栈：
 * pop、push、getMin操作的时间复杂度都是O（1）
 * 可以使用现成的栈结构
 *
 * */
public class GetMinStack {

    //第一种方法：
    // 压入：两个栈同步压入，只不过stackMin栈压入的是最小值
    // 出栈：同时出
    public static class MyStack1 {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack1() {
            stackData = new Stack<>();
            stackMin = new Stack<>();
        }

        // 进栈
        public void push(int data) {
            if(stackMin.isEmpty()) {
                stackMin.push(data);
            } else {
                stackMin.push(stackMin.peek());
            }
            stackData.push(data);
        }

        // 出栈
        public int pop() {
            if(stackData.isEmpty()){
                throw new RuntimeException("Your Stack is empty!");
            }
            stackMin.pop();
            return stackData.pop();
        }

        // 栈内最小值
        public int getMin() {
            if (stackData.isEmpty()) {
                throw new RuntimeException("Your Stack is empty!");
            }
            return stackMin.peek();
        }
    }

    //第二种方法：
    // 压入stackData栈的时候，如果元素 ≤ stackMin栈的栈顶元素就同步压入，否则不压入stackMin栈。
    // 出栈：当stackData栈顶元素等于stackMin栈顶元素的时候，stackMin才出栈
    public static class MyStack2 {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        // 构造方法
        public MyStack2() {
            stackData = new Stack<>();
            stackMin = new Stack<>();
        }

        // 入栈
        public void push(int data) {
            if(stackMin.isEmpty()) {
                stackMin.push(data);
            }else if (stackMin.peek() >= data) {
                stackMin.push(data);
            }
            stackData.push(data);
        }

        // 出栈
        public int pop() {
            if(stackData.isEmpty()) {
                throw new RuntimeException("Your stack is empty!");
            }
            if(stackData.peek().equals(stackMin.peek())) {
                stackMin.pop();
            }
            return stackData.pop();
        }

        // 栈内最小值
        public int getMin() {
            if(stackData.isEmpty()) {
                throw new RuntimeException("Your stack is empty!");
            }
            return stackMin.peek();
        }

    }


    public static void main(String[] args) {
        MyStack1 s = new MyStack1();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);

        System.out.println(s.pop());
        System.out.println(s.getMin());
    }
}
