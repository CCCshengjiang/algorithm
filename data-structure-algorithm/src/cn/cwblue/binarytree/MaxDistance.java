package cn.cwblue.binarytree;

/**
 * 给定一棵二叉树的头节点head，任何两个节点之间都存在距离，返回二叉树的最大距离
 *
 * @author wen
 */

public class MaxDistance {
    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node(int val) {
            this.val = val;
        }
    }

    public static class Info {
        public int maxDistance;
        public int height;
        public Info(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }

    public static int getMaxDistance(Node head) {
        return distanceProcess(head).maxDistance;
    }

    private static Info distanceProcess(Node head) {
        if (head == null) {
            return new Info(0, 0);
        }
        Info leftInfo = distanceProcess(head.left);
        Info rightInfo = distanceProcess(head.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int maxDistance = Math.max(Math.max(leftInfo.maxDistance, rightInfo.maxDistance), leftInfo.height + rightInfo.height + 1);
        return new Info(maxDistance, height);
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        System.out.println(getMaxDistance(head));
    }
}
