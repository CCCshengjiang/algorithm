package com.cwblue.binarytree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class TreeMaxWidth {
    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node(int val) {
            this.val = val;
            left = null;
            right = null;
        }
    }

    public static int maxWidthUseMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        HashMap<Node, Integer> hashMap = new HashMap<>();
        hashMap.put(head, 1);
        int curLevelNodes = 0;
        int curLevel = 1;
        int max = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int curNodeLevel = hashMap.get(cur);
            if (cur.left != null) {
                queue.add(cur.left);
                hashMap.put(cur.left, curNodeLevel + 1);
            }
            if (cur.right != null) {
                queue.add(cur.right);
                hashMap.put(cur.right, curNodeLevel + 1);
            }
            if (curLevel == curNodeLevel) {
                curLevelNodes++;
            }else {
                max = Math.max(max, curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
        }
        max = Math.max(max, curLevelNodes);
        return max;
    }

    public static int maxWidthNoMap (Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node curEnd = head;
        Node nextEnd = null;
        int max = 0;
        int curLevelNodes = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.left != null) {
                queue.add(cur.left);
                nextEnd = cur.left;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            curLevelNodes++;
            if (cur == curEnd) {
                max = Math.max(max, curLevelNodes);
                curLevelNodes = 0;
                curEnd = nextEnd;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        head.left.right.left = new Node(8);
        head.right.left.right = new Node(9);

        System.out.println(maxWidthNoMap(head));
    }
}
