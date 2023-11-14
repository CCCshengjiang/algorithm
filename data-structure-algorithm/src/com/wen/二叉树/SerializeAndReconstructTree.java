package com.wen.二叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的序列化与反序列化
 *
 * @author wen
 */
public class SerializeAndReconstructTree {
    public static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    /**
     * 二叉树的序列化（前序遍历实现）
     *
     * @param head 头节点
     * @return 返回一个队列
     */
    public static Queue<String> preSerial(Node head) {
        Queue<String> queue = new LinkedList<>();
        pres(queue, head);
        return queue;
    }

    private static void pres(Queue<String> queue, Node head) {
        if (head == null) {
            queue.add(null);
        } else {
            queue.add(String.valueOf(head.val));
            pres(queue, head.left);
            pres(queue, head.right);
        }
    }

    /**
     * 二叉树的反序列化（前序遍历实现）
     *
     * @param preQueue 存着二叉树序列化的队列
     * @return 返回，反序列化的二叉树头节点
     */
    public static Node buildByPreQueue(Queue<String> preQueue) {
        if (preQueue == null || preQueue.isEmpty()) {
            return null;
        }
        return preBuild(preQueue);
    }

    private static Node preBuild(Queue<String> preQueue) {
        String val = preQueue.poll();
        if (val == null) {
            return null;
        }
        Node head = new Node(Integer.parseInt(val));
        head.left = preBuild(preQueue);
        head.right = preBuild(preQueue);
        return head;
    }

    /**
     * 二叉树序列化（层序遍历实现）
     *
     * @param head 二叉树头节点
     * @return 返回序列化后的队列
     */
    public static Queue<String> levelSerial(Node head) {
        Queue<String> ans = new LinkedList<>();
        if (head == null) {
            ans.add(null);
        } else {
            ans.add(String.valueOf(head.val));
            Queue<Node> help = new LinkedList<>();
            help.add(head);
            while (!help.isEmpty()) {
                Node cur = help.poll();
                if (cur.left != null) {
                    ans.add(String.valueOf(cur.left.val));
                    help.add(cur.left);
                } else {
                    ans.add(null);
                }
                if (cur.right != null) {
                    ans.add(String.valueOf(cur.right.val));
                    help.add(cur.right);
                } else {
                    ans.add(null);
                }
            }
        }
        return ans;
    }

    /**
     * 反序列化（层序遍历实现）
     *
     * @param levelQueue 序列化存入的队列
     * @return 返回，反序列化的二叉树头节点
     */
    public static Node buildByLevelQueue(Queue<String> levelQueue) {
        if (levelQueue == null || levelQueue.isEmpty()) {
            return null;
        }
        Node head = generateNode(levelQueue.poll());
        Queue<Node> queue = new LinkedList<>();
        if (head != null) {
            queue.add(head);
        }
        Node node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            node.left = generateNode(levelQueue.poll());
            node.right = generateNode(levelQueue.poll());
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return head;
    }

    private static Node generateNode(String val) {
        if (val == null) {
            return null;
        }
        return new Node(Integer.parseInt(val));
    }
}
