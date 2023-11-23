package com.cwblue.binarytree;

/*
* 二叉树的遍历
*
* */

import java.util.Stack;

public class RecursiveTraversalBT {
    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node(int val){
            this.val = val;
        }
    }

    // 递归遍历
    public static void pre1(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.val + " ");
        pre1(head.left);
        pre1(head.right);
    }

    public static void in1(Node head) {
        if (head == null) {
            return;
        }
        in1(head.left);
        System.out.print(head.val + " ");
        in1(head.right);
    }

    public static void pos1(Node head) {
        if (head == null) {
            return;
        }
        pos1(head.left);
        pos1(head.right);
        System.out.print(head.val + " ");
    }

    // 非递归遍历（栈）
    public static void pre2(Node head) {
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            stack.push(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.print(head.val + " ");
                if (head.right != null) {
                    stack.push(head.right);
                }
                if (head.left != null) {
                    stack.push(head.left);
                }
            }
        }
    }

    public static void in2(Node head) {
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                }else {
                    head = stack.pop();
                    System.out.print(head.val + " ");
                    head = head.right;
                }
            }
        }
    }

    public static void pos2(Node head) {
        if (head != null) {
            Stack<Node> stack1 = new Stack<>();
            Stack<Node> stack2 = new Stack<>();
            stack1.push(head);
            while (!stack1.isEmpty()){
                head = stack1.pop();
                stack2.push(head);
                if (head .left != null) {
                    stack1.push(head.left);
                }
                if (head .right != null) {
                    stack1.push(head.right);
                }
            }
            while (!stack2.isEmpty()) {
                System.out.print(stack2.pop().val + " ");
            }
        }
    }

    // 后序遍历的第三种写法（只用一个栈）
    public static void pos3(Node head) {
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            stack.push(head);
            Node help = null;
            while (!stack.isEmpty()) {
                help = stack.peek();
                if (help.left !=null && head != help.left && head != help.right){
                    stack.push(help.left);
                } else if (help.right != null && head != help.right) {
                    stack.push(help.right);
                }else {
                    System.out.print(stack.pop().val + " ");
                    head = help;
                }
            }
        }

    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        pos2(head);
        System.out.println();
        pos3(head);
        System.out.println();

    }

}
