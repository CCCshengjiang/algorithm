package cn.cwblue.bruteforce;

/**
 * 小虎去买苹果，商店只提供两种类型的塑料袋，每种类型都有任意数量
 * 1)能装下6个苹果的袋子
 * 2)能装下8个苹果的袋子
 * 小虎可以自由使用两种袋子来装苹果，但是小虎有强迫症，他要求自己使用的袋子数量必须最少，且使用的每个袋子必须装满。
 * 给定一个正整数N，返回至少使用多少袋子。如果N无法让使用的每个袋子必须装满，返回-1
 *
 * @author wen
 */
public class AppleMinBags {
    // 暴力打表
    private static int minBags(int apple) {
        if (apple <= 0) {
            return 0;
        }
        int bag6 = -1;
        int bag8 = apple / 8;
        int rest = apple % 8;
        while (rest % 6 != 0) {
            bag8--;
            rest += 8;
            if (bag8 == -1) {
                return -1;
            }
        }
        bag6 = rest / 6;
        return bag6 == -1 ? -1 : bag8 + bag6;

    }

    // 优化代码
    public static int minBagsAwesome(int apple) {
        if ((apple & 1) != 0) {
            return -1;
        }
        if (apple < 17) {
            return (apple == 6 || apple == 8) ? 1 :
                    (apple == 12 || apple == 14 || apple == 16) ? 2 :
                            -1;
        }
        return (apple - 18) / 8 + 3;
    }

    public static void main(String[] args) {
        for (int apple = 1; apple <= 100; apple++) {
            System.out.println(apple + ":" + minBags(apple));
        }
    }
}
