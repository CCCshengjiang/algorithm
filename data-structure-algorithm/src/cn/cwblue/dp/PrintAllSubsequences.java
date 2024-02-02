package cn.cwblue.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印一个字符串的全部子序列
 *
 * @author wen
 */
public class PrintAllSubsequences {
    public static void main(String[] args) {
        String str = "abc";
        List<String> subs = subs(str);
        for (String sub : subs) {
            System.out.print(sub + " ");
        }
    }

    public static List<String> subs(String str) {
        char[] chars = str.toCharArray();
        List<String> ans = new ArrayList<>();
        func(chars, 0, ans, "");
        return ans;
    }

    private static void func(char[] chars, int index, List<String> ans, String path) {
        if (index == chars.length) {
            ans.add(path);
            return;
        }
        func(chars, index + 1, ans, path);
        String yes = path + chars[index];
        func(chars, index + 1, ans, yes);

    }
}
