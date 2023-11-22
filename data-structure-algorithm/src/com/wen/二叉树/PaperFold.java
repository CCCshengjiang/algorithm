package com.wen.二叉树;

import java.util.Scanner;

/**
 * 请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。
 * 此时折痕是凹下去的，即折痕突起的方向指向纸条的背面。 如果从纸条的下边向上方连续对折2次，
 * 压出折痕后展开，此时有三条折痕，从上到下依次是下折痕、下折痕和上折痕。
 * 给定一个输入参数N，代表纸条都从下边向上方连续对折N次。 请从上到下打印所有折痕的方向。
 * 例如:N=1时，打印: down     N=2时，打印: down down up
 *
 * @author wen
 */
public class PaperFold {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        printAllFolds(N);
    }

    private static void printAllFolds(int n) {
        printProcess(1, n, true);
    }

    private static void printProcess(int i, int n, boolean flag) {
        if (i > n) {
            return;
        }
        printProcess(i + 1, n, true);
        System.out.print(flag ? "down " : "up ");
        printProcess(i + 1, n, false);
    }
}