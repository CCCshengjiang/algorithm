package com.wen.前缀树;

import java.util.HashMap;

public class TrieTreeSearch {
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

    public static class TrieTree {
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

    public static class Node2 {
        public int pass;
        public int end;
        public Node2[] next;

        public Node2() {
            pass = 0;
            end = 0;
            next = new Node2[26];
        }
    }

    public static class TrieTree2 {
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

    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 26);
            ans[i] = (char) (value + 97);
        }
        return String.valueOf(ans);
    }

    public static String[] generateRandomString(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }


    // 写对数器进行测试
    public static void main(String[] args) {
        int strLen = 20;
        int arrLen = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            String[] strings = generateRandomString(arrLen, strLen);
            TrieTree my = new TrieTree();
            TrieTree2 test = new TrieTree2();
            for (String word : strings) {
                double decide = Math.random();
                if (decide < 0.25) {
                    my.insert(word);
                    test.insert(word);
                } else if (decide < 0.5) {
                    my.delete(word);
                    test.delete(word);
                } else if (decide < 0.75) {
                    int ans1 = my.search(word);
                    int ans2 = test.search(word);
                    if(ans1 != ans2) {
                        System.out.println("Oops!");
                    }
                }else {
                    int ans1 = my.prefixNum(word);
                    int ans2 = test.prefixNum(word);
                    if (ans1 != ans2) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        System.out.println("Finish!");
    }
}
