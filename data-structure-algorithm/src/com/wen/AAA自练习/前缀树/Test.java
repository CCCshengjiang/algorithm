package com.wen.AAA自练习.前缀树;

import java.util.Random;

// 写对数器进行测试
public class Test {
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 26);
            ans[i] = (char) (value + 97);
        }
        return String.valueOf(ans);
    }

    public static String[] generateRandomString(int arrLen,int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

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
                    my.search(word);
                    test.search(word);
                    if(my.search(word) != test.search(word)) {
                        System.out.println("Oops!");
                    }
                }else {
                    my.prefixNum(word);
                    test.prefixNum(word);
                    if (my.prefixNum(word) != test.prefixNum(word)) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        System.out.println("Finish!");
    }
}
