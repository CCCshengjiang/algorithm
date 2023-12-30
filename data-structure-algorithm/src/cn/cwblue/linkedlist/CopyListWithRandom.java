package cn.cwblue.linkedlist;

/*
* 复制特殊链表
* 单链表中加了个rand指针，可能指向任意一个节点，也可能指向null
* 给一个链表的头节点，完成链表的复制，并返回复制的新链表的头节点
* 时间复杂度 O(N),额外空间复杂度 O(1)
*
* */

import java.util.HashMap;

public class CopyListWithRandom {
    public static class Node {
        public int val;
        public Node next;
        public Node rand;
        public Node(int val) {
            this.val = val;
        }
    }

    // map方法
    public static Node copyListWithRandom(Node head) {
        if (head == null) {
            return null;
        }
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        Node ans = map.get(head);
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return ans;
    }

    // 不借助容器
    public static Node copyListWithRandom2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = next;
            cur = next;
        }
        Node copyNode = null;
        cur = head;
        while (cur != null) {
            copyNode = cur.next;
            copyNode.rand = cur.rand == null ? null : cur.rand.next;
            cur = copyNode.next;
        }
        Node ans = head.next;
        cur = head;
        while (cur != null) {
            next = cur.next.next;
            copyNode = cur.next;
            copyNode.next = next == null ? null : next.next;
            cur.next = next;
            cur = next;
        }
        return ans;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        head.next = node2;
        node2.next = node3;
        head.rand = node3;
        node2.rand = head;

        Node node = copyListWithRandom(head);
        while (node != null) {
            System.out.println(node.val + " ");
            System.out.println(node == head);
            node = node.next;
            head = head.next;
        }
    }
}
