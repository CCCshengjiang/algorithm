package cn.cwblue.binarytree;

/**
 * 给定一颗二叉树的头节点head，返回这颗二叉树中最大的二叉搜索子树的节点数量
 *
 * @author wen
 */

public class MaxSubBSTNode {
    public static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    public static class Info {
        public boolean isAllBST;
        public int maxBSTSize;
        public int max;
        public int min;

        public Info(boolean isAllBST, int maxBSTSize, int max, int min) {
            this.isAllBST = isAllBST;
            this.maxBSTSize = maxBSTSize;
            this.max = max;
            this.min = min;
        }
    }

    public static int getMaxSubBSTNode(Node head) {
        if (head == null) {
            return 0;
        }
        return BSTNodeProcess(head).maxBSTSize;
    }

    private static Info BSTNodeProcess(Node head) {
        if (head == null) {
            return null;
        }
        Info leftInfo = BSTNodeProcess(head.left);
        Info rightInfo = BSTNodeProcess(head.right);
        int min = head.val;
        int max = head.val;
        int maxBSTSize = 0;
        if (leftInfo != null) {
            min = Math.min(leftInfo.min, min);
            max = Math.max(leftInfo.max, max);
            maxBSTSize = Math.max(maxBSTSize, leftInfo.maxBSTSize);
        }
        if (rightInfo != null) {
            min = Math.min(rightInfo.min, min);
            max = Math.max(rightInfo.max, max);
            maxBSTSize = Math.max(maxBSTSize, rightInfo.maxBSTSize);
        }
        boolean leftIsBST = leftInfo == null || leftInfo.isAllBST;
        boolean rightIsBST = rightInfo == null || rightInfo.isAllBST;
        boolean leftIsBigger = leftInfo == null || leftInfo.max < head.val;
        boolean rightIsBigger = rightInfo == null || rightInfo.min > head.val;
        boolean isAllBST = false;
        if (leftIsBST && rightIsBST && leftIsBigger && rightIsBigger) {
            maxBSTSize = (leftInfo == null ? 0 : leftInfo.maxBSTSize)
                    +
                    (rightInfo == null ? 0 : rightInfo.maxBSTSize)
                    +
                    1;
            isAllBST = true;
        }
        return new Info(isAllBST, maxBSTSize, max, min);
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);
        head.right.right = new Node(3);

        System.out.println(getMaxSubBSTNode(head));
    }
}
