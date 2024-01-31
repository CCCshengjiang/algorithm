package cn.cwblue.dp;

import java.util.Stack;

/**
 * 使用递归逆转一个栈
 *
 * @author wen
 */
public class ReverseStackUsingRecursive {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(2);
        stack.push(1);
        reserve(stack);
        System.out.println(1);
    }
    public static void reserve(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int last = func(stack);
        reserve(stack);
        stack.push(last);
    }

    private static int func(Stack<Integer> stack) {
        int res = stack.pop();
        if (stack.isEmpty()) {
            return res;
        } else {
            int last = func(stack);
            stack.push(res);
            return last;
        }
    }
}
