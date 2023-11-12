package com.wen.二叉树;

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
        }
    }

    public static int maxWidthUseMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        HashMap<Node,Integer> nodeLevelMap = new HashMap<>();
        nodeLevelMap.put(head, 1);
        int curLevel = 1;
        int curLevelNodes = 0;
        int max = 0;
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            int curNodeLevel = nodeLevelMap.get(curNode);
            if (curNode.left != null) {
                queue.add(curNode.left);
                nodeLevelMap.put(curNode.left, curNodeLevel + 1);
            }
            if (curNode.right != null) {
                queue.add(curNode.right);
                nodeLevelMap.put(curNode.right, curNodeLevel + 1);
            }
            if (curNodeLevel == curLevel) {
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

        System.out.println(maxWidthUseMap(head));
    }
}
