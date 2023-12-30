package cn.cwblue.linkedlist;

/*
* 快慢指针
* （1）输入链表头节点，奇数长度返回中点，偶数长度返回上中点
* （2）输入链表头节点，奇数长度返回中点，偶数长度返回下中点
* （3）输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
* （4）输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
*
* */

public class LinkedListMid {
    public static class Node {
        public int val;
        public Node next;
        public Node(int data) {
            val = data;
            next = null;
        }
    }
    public static Node midOrUpMid(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node midOrDownMid(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node slow = head.next;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node midPreOrUpMidPre(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        Node slow = head;
        Node fast = head.next.next;
        while (fast.next !=null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node midPreOrDownMidPre(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        Node slow = head;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    public static void main(String[] args) {
        Node head = new Node(0);
        head.next = new Node(1);
        head.next.next = new Node(2);
        head.next.next.next = new Node(3);
        head.next.next.next.next = new Node(4);
        head.next.next.next.next.next = new Node(5);

        Node node = midPreOrDownMidPre(head);
        System.out.println(node.val);
    }
}
