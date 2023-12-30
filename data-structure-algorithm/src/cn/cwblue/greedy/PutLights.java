package cn.cwblue.greedy;

import java.util.HashSet;

/**
 *给定一个字符串string，只由 'X' 和 '.' 两种字符构成
 * 'X'表示墙，不能放灯，也不需要点亮
 * '.'表示居民点，可以放灯，需要点亮
 * 如果灯放在 i 位置，可以让 i - 1、 i 和 i + 1 三个位置被点亮
 * 返回如果点亮string中所有需要点亮的位置，需要几盏灯
 *
 * @author wen
 */
public class PutLights {
    public static int putLights(String str) {
        if (str == null) {
            return 0;
        }
        return process(str.toCharArray(), 0, new HashSet<>());
    }

    private static int process(char[] str, int index, HashSet<Integer> lightIndex) {
        if (str.length == index) {
            for (int i = 0; i < str.length; i++) {
                if (str[i] != 'X') {
                    if (!lightIndex.contains(i) && !lightIndex.contains(i - 1) && !lightIndex.contains(i + 1)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lightIndex.size();
        }else {
            int no = process(str, index + 1, lightIndex);
            int yes = Integer.MAX_VALUE;
            if (str[index] != 'X') {
                lightIndex.add(index);
                yes = process(str, index + 1, lightIndex);
                lightIndex.remove(index);
            }
            return Math.min(yes, no);
        }
    }

    public static int putLight2(String str) {
        if (str == null) {
            return 0;
        }
        char[] strCharArray = str.toCharArray();
        int index = 0;
        int light = 0;
        while (index < strCharArray.length) {
            if (strCharArray[index] == 'X') {
                index++;
            }else {
                light++;
                if (index + 1 == strCharArray.length) {
                    break;
                }
                if (strCharArray[index + 1] == 'X') {
                    index = index + 2;
                }else {
                    index = index + 3;
                }
            }
        }
        return light;
    }

    public static void main(String[] args) {
        String str = "X.XX....";
        if (putLights(str) == putLight2(str)) {
            System.out.println("Finish!");
        }else {
            System.out.println("Oops!");
        }
    }
}
