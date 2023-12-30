package cn.cwblue.binarytree;

/**
 * 二叉树节点结构定义如下：
 *     public static class Node {
 *         public int cal;
 *         public Node left;
 *         public Node right;
 *         public Node parent;
 *     }
 * 给你二叉树中的某个节点，返回该节点的后继节点
 *
 * @author wen
 */

public class SuccessorNode {
    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
        public Node(int val) {
            this.val = val;
        }
    }

    public static Node getSuccessorNode(Node node) {
        if (node == null) {
            return node;
        }
        if (node.right != null) {
            return getLeftMost(node.right);
        }else{
            Node cur = node;
            Node parent = node.parent;
            while (parent != null && parent.left != node) {
                cur = parent;
                parent = cur.parent;
            }
            return parent;
        }
    }

    private static Node getLeftMost(Node node) {
        Node cur = node;
        if (cur.left != null) {
            cur = cur.left;
        }
        return cur;
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n2.parent = n1;
        n3.left = n6;
        n3.right = n7;
        n3.parent = n1;
        n4.parent = n2;
        n5.parent = n2;
        n6.parent = n3;
        n7.parent = n3;

        System.out.println(getSuccessorNode(n6).val);
    }
}
