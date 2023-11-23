package com.cwblue.linkedlist;

/*
* 给定两个可能有环也可能无环的单链表，头节点head1和head2.
* 请实现一个函数，如果两个链表相交，请返回相交的第一个节点，如果不相交，返回null
* 时间复杂度O(N),额外空间复杂度O(1)
*
* */

public class FindFirstIntersectNode {
    public static class Node {
        public int val;
        public Node next;
        public Node(int val) {
            this.val = val;
        }
    }

    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        return null;
    }

    private static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = head1;
        Node cur2 = head2;
        if (loop1 == loop2) {
            int n = 0;
            while (cur1.next != null) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2.next != null) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        }else {
            cur1 = loop1.next;
            while (cur1 != loop1) {
                if (cur1 == loop2) {
                    return loop2;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }

    private static Node noLoop(Node head1, Node head2) {
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        while (cur1.next != null) {
            n++;
            cur1 = cur2.next;
        }
        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }
        if (cur1 != cur2) {
            return null;
        }
        cur1 = n < 0 ? head2 : head1;
        cur2 = cur1 == head2 ? head1 : head2;
        n = Math.abs(n);
        while (n != 0) {
            n--;
            cur1 = cur1.next;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    private static Node getLoopNode(Node head) {
        if (head.next == null || head.next.next ==null) {
            return null;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (fast != slow) {
            if (fast == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }


    public static void main(String[] args) {
        Node head = new Node(0);
        head.next = new Node(1);
        head.next.next = new Node(2);
        head.next.next.next = new Node(3);
        head.next.next.next.next = new Node(4);
        head.next.next.next.next.next = head.next.next;

        Node head1 = new Node(0);
        head1.next = new Node(1);
        head1.next.next = new Node(2);
        head1.next.next.next = new Node(3);
        head1.next.next.next.next = new Node(4);
        head1.next.next.next.next.next = head.next.next.next;

        Node intersectNode = getIntersectNode(head, head1);
        System.out.println(intersectNode.val);
    }
}
