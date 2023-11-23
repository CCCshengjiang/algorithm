package com.cwblue.linkedlist;

import java.util.Stack;

/*
* 判断是否是回文链表
* 第一个方法使用栈来实现
* 第二个方法两次反转链表，第一个反转后，两个指针分别在头和尾向中间走，走完再把链表反转回去
* */
public class IsPalindromList {
    public static class Node {
        public int val;
        public Node next;
        public Node(int data) {
            val = data;
            next = null;
        }
    }

    public static boolean isPalindrom1(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Stack<Node> stack = new Stack<>();
        Node mid = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            mid = mid.next;
            fast = fast.next.next;
        }
        while (mid.next != null) {
            mid = mid.next;
            stack.add(mid);
        }
        Node i = head;
        boolean ans = true;
        while (stack.size() != 0) {
            if (i.val != stack.pop().val) {
                ans = false;
                break;
            }
            i = i.next;
        }
        return ans;
    }
    public static boolean isPalindrom2(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        // 找到中间节点
        Node mid = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            mid = mid.next;
            fast = fast.next.next;
        }
        Node cur = mid;
        // 后边段链表反转
        Node pre = null;
        Node next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        Node left = head;
        Node right = pre;
        boolean ans = true;
        while (left != null && right != null) {
            if (left.val != right.val) {
                ans = false;
                break;
            }
            left = left.next;
            right = right.next;
        }

        cur = pre;
        pre = null;
        next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return ans;
    }


    public static void main(String[] args) {
        Node head = new Node(0);
        head.next = new Node(1);
        head.next.next = new Node(2);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        head.next.next.next.next.next = new Node(0);

        System.out.println(isPalindrom2(head));
        System.out.println(isPalindrom1(head));
    }

}
