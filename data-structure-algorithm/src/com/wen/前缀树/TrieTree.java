package com.wen.前缀树;

import java.util.HashMap;

public class TrieTree {
    public static class Node1 {
        public int pass;
        public int end;
        public Node1[] next;
        public Node1() {
            pass = 0;
            end = 0;
            next = new Node1[26];
        }
    }

    public static class Tree1 {
        private final Node1 root;

        // 无参构造
        public Tree1() {
            root = new Node1();
        }

        // 添加字符串
        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] chars = word.toCharArray();
            Node1 node = root;
            node.pass++;
            int index = 0;
            for (char aChar : chars) {
                index = aChar - 'a';
                if (node.next[index] == null) {
                    node.next[index] = new Node1();
                }
                node = node.next[index];
                node.pass++;
            }
            node.end++;
        }

        // 查找字符串有多少个
        public int searchWord(String word) {
            if (word == null) {
               return 0;
            }
            char[] chars = word.toCharArray();
            Node1 node = root;
            int index = 0;
            for (char aChar : chars) {
                index = aChar - 'a';
                if(node.next[index] == null) {
                    return 0;
                }
                node = node.next[index];
            }
            return node.end;
        }

        // 删除字符串
        public void deleteWord(String word) {
            System.out.println(searchWord(word));
            if (searchWord(word) != 0) {
                Node1 node = root;
                node.pass--;
                int index = 0;
                char[] chars = word.toCharArray();
                for (char aChar : chars) {
                    index = aChar - 'a';
                    if(--node.next[index].pass == 0) {
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
            if(word == null) {
                return 0;
            }
            char[] chars = word.toCharArray();
            Node1 node = root;
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

    public static class Node2 {
        public int pass;
        public int end;
        public HashMap<Integer, Node2> next;

        public Node2() {
            pass = 0;
            end = 0;
            next = new HashMap<>();
        }
    }

    public static class Tree2 {
        private final Node2 root;

        public Tree2() {
            root = new Node2();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            Node2 node = root;
            node.pass++;
            char[] chars = word.toCharArray();
            int index = 0;
            for (char aChar : chars) {
                index = aChar;
                if (!node.next.containsKey(index)) {
                    node.next.put(index, new Node2());
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
            Node2 node = root;
            char[] chars = word.toCharArray();
            int index = 0;
            for (char aChar: chars) {
                index = aChar;
                if (!node.next.containsKey(index)) {
                    return 0;
                }
                node = node.next.get(index);
            }
            return node.end;
        }

        public void delete(String word) {
            if (search(word) != 0) {
                Node2 node = root;
                root.pass--;
                char[] chars = word.toCharArray();
                int index = 0;
                for (char aChar : chars) {
                    index = aChar;
                    if (node.next.containsKey(index)) {
                        node.pass--;
                    }
                    node = node.next.get(index);
                }
                node.end--;
            }
        }

        public int prefixNum(String word) {
            if (word == null) {
                return 0;
            }
            Node2 node = root;
            char[] chars = word.toCharArray();
            int index = 0;
            for (char aChar : chars) {
                index = aChar;
                if (!node.next.containsKey(index)) {
                    return 0;
                }
                node = node.next.get(index);
            }
            return node.pass;
        }

    }

    // 测试
    public static void main(String[] args) {
        Tree1 tree1 = new Tree1();
        tree1.insert("ab");
        tree1.insert("abcd");

        Tree2 tree2 = new Tree2();
        tree2.insert("ab");
        tree2.insert("abcde");

        System.out.println(tree2.search("a"));
        System.out.println(tree2.search("ab"));
        System.out.println(tree2.search("abcde"));
        System.out.println("===============================");
        tree2.delete("a");
        tree2.delete("ab");
        System.out.println(tree2.search("a"));
        System.out.println(tree2.search("ab"));
        System.out.println(tree2.search("abcde"));
        System.out.println("========================");
        tree2.insert("ab");
        tree2.insert("abcd");
        tree2.insert("a");
        System.out.println(tree2.search("ab"));
        System.out.println(tree2.prefixNum("ab"));
    }
}
