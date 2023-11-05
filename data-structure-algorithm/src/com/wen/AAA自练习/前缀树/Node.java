package com.wen.AAA自练习.前缀树;

import java.util.HashMap;

public class Node {
    public int pass;
    public int end;
    public HashMap<Integer, Node> next;

    public Node() {
        pass = 0;
        end = 0;
        next = new HashMap<>();
    }
}