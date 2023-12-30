package cn.cwblue.bruteforce;

/**
 *给定一个正整数N，表示有N份青草统一堆放在仓库里有一只牛和一只羊，牛先吃，羊后吃
 * 它俩轮流吃草不管是牛还是羊，每一轮能吃的草量必须是1，4，16，64..(4的某次方)，
 * 谁最先把草吃完，谁获胜。假设牛和羊都绝顶聪明，都想赢，都会做出理性的决定。
 * 根据唯一的参数N，返回谁会赢
 *
 * @author wen
 */
public class EatGrass {
    public static String winner(int num) {
        if (num < 5) {
            return (num == 0 || num == 2) ? "后手" : "先手";
        }
        // 假定先手要吃的数量
        int base = 1;
        while (base <= num) {
            if (winner(num - base).equals("后手")) {
                return "先手";
            }
            //防止溢出
            if (base > num / 4) {
                break;
            }
            base *= 4;
        }
        return "后手";
    }

    public static String winnerAwesome(int num) {
        if (num % 5 == 0 || num % 5 == 2) {
            return "后手";
        }
        return "先手";
    }

    public static void main(String[] args) {
        for (int num = 0; num <= 100; num++) {
            System.out.println(num + ":" + winner(num));
        }
    }
}
