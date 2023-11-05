package com.wen.AAA自练习.前缀树;

import java.util.HashMap;

public class Node2 {
        public int pass;
        public int end;
        public Node2[] next;
        public Node2() {
            pass = 0;
            end = 0;
            next = new Node2[26];
        }
}