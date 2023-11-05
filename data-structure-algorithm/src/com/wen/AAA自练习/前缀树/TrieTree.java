package com.wen.AAA自练习.前缀树;

import java.util.HashMap;


public class TrieTree {
    private final Node root;

    public TrieTree() {
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
            if (!node.next.containsKey(index)) {
                node.next.put(index, new Node());
            }
            node = node.next.get(index);
            node.pass++;
        }
        node.end++;
    }

    public void delete(String word) {
        if (search(word) != 0) {
            Node node = root;
            node.pass--;
            int index = 0;
            char[] chars = word.toCharArray();
            for (char aChar : chars) {
                index = aChar;
                if (node.next.containsKey(index)) {
                    node = node.next.get(index);
                }
                node.pass--;
            }
            node.end--;
        }
    }

    public int search(String word) {
        return research(word).end;
    }

    public int prefixNum(String word) {
        return research(word).pass;
    }

    private Node research(String word) {
        if (word == null) {
            return new Node();
        }
        Node node = root;
        char[] chars = word.toCharArray();
        int index = 0;
        for (char aChar : chars) {
            index = aChar;
            if (!node.next.containsKey(index)) {
                return new Node();
            }
            node = node.next.get(index);
        }
        return node;
    }
}




