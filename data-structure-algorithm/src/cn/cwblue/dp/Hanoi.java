package cn.cwblue.dp;

/**
 * 汉诺塔递归
 *
 * @author wen
 */
public class Hanoi {
    public static void main(String[] args) {
        hanoi(3);
    }

    public static void hanoi(int n) {
        if (n == 0) {
            return;
        }
        func(n, "left", "right", "mid");
    }

    private static void func(int n, String left, String right, String other) {
        if (n == 1) {
            System.out.println("move " + n + " from " + left + " to " + right);
            return;
        }
        func(n - 1, left, other, right);
        System.out.println("move " + n + " from " + left + " to " + right);
        func(n - 1, other, right, left);
    }
}
