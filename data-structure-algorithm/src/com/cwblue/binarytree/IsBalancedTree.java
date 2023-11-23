package com.cwblue.binarytree;

/**
 * 给一个树的头节点，返回这棵树是否是平衡二叉树
 *
 * @author wen
 */
public class IsBalancedTree {
    public static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    public static class Info {
        public int height;
        public boolean balancedFlag;

        public Info(int height, boolean balancedFlag) {
            this.height = height;
            this.balancedFlag = balancedFlag;
        }
    }

    public static boolean isBalanced(Node head) {
        return balancedProcess(head).balancedFlag;
    }

    private static Info balancedProcess(Node head) {
        if (head == null) {
            return new Info(0, true);
        }
        Info leftInfo = balancedProcess(head.left);
        Info rightInfo = balancedProcess(head.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean flag = leftInfo.balancedFlag && rightInfo.balancedFlag && Math.abs(leftInfo.height - rightInfo.height) <= 1;
        return new Info(height, flag);
    }

    // 测试
    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        head.right.right.right = new Node(8);
        head.right.right.right.right = new Node(9);

        System.out.println(isBalanced(head));
    }
}
