package com.wen.AAA自练习.前缀树;

public class TrieTree2 {
    private final Node2 root;

    // 无参构造
    public TrieTree2() {
        root = new Node2();
    }

    // 添加字符串
    public void insert(String word) {
        if (word == null) {
            return;
        }
        char[] chars = word.toCharArray();
        Node2 node = root;
        node.pass++;
        int index = 0;
        for (char aChar : chars) {
            index = aChar - 'a';
            if (node.next[index] == null) {
                node.next[index] = new Node2();
            }
            node = node.next[index];
            node.pass++;
        }
        node.end++;
    }

    // 查找字符串有多少个
    public int search(String word) {
        if (word == null) {
            return 0;
        }
        char[] chars = word.toCharArray();
        Node2 node = root;
        int index = 0;
        for (char aChar : chars) {
            index = aChar - 'a';
            if (node.next[index] == null) {
                return 0;
            }
            node = node.next[index];
        }
        return node.end;
    }

    // 删除字符串
    public void delete(String word) {
        if (search(word) != 0) {
            Node2 node = root;
            node.pass--;
            int index = 0;
            char[] chars = word.toCharArray();
            for (char aChar : chars) {
                index = aChar - 'a';
                if (--node.next[index].pass == 0) {
                    node.next[index] = null;
                    return;
                }
                node = node.next[index];
            }
            node.end--;
        }
    }

    // 有几个字符串前缀是word
    public int prefixNum(String word) {
        if (word == null) {
            return 0;
        }
        char[] chars = word.toCharArray();
        Node2 node = root;
        int index = 0;
        for (char aChar : chars) {
            index = aChar - 'a';
            if (node.next[index] == null) {
                return 0;
            }
            node = node.next[index];
        }
        return node.pass;
    }

}