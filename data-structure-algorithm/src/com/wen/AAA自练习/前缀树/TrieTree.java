package com.wen.AAA自练习.前缀树;

import java.util.HashMap;

public class TrieTree {
    public static class Node {
        public int pass;
        public int end;
        public HashMap<Integer, Node> next;

        public Node() {
            pass = 0;
            end = 0;
            next = new HashMap<>();
        }
    }

    public static class Tree {
        private final Node root;
        public Tree() {
            root = new Node();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            Node node = root;
            node.pass++;
            char[] chars = word.toCharArray();
            int index = 0;
            for (char aChar : chars) {
                index = aChar;
                if(!node.next.containsKey(index)) {
                    node.next.put(index, new Node());
                }
                node = node.next.get(index);
                node.pass++;
            }
            node.end++;
        }

        public int search(String word) {
            if (word == null) {
                return 0;
            }
            Node node = root;
            char[] chars = word.toCharArray();
            int index = 0;
            for (char aChar : chars) {
                index = aChar;
                if (!node.next.containsKey(index)) {
                    return 0;
                }
                node = node.next.get(index);
            }
            return node.end;
        }

    }





}
