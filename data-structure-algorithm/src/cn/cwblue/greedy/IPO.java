package cn.cwblue.greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 输入：正整数数组costs、正数数组profits、正数K、正数M
 * costs[i] 标识 i 号项目的花费
 * profits[i] 标识 i 号项目在扣除花费之后还能挣到的钱（利润）
 * K 表示你只能串行的最多做 K 个项目
 * M 表示你初始的资金
 * 说明：每做完一个项目，马上获得的收益，可以支持你去做下一个项目。不能并行的做项目
 * 输出：你最后获得的最大钱数
 *
 * @author wen
 */
public class IPO {
    public static class Program {
        public int cost;
        public int profits;
        public Program(int cost, int profits) {
            this.cost = cost;
            this.profits = profits;
        }
    }

    public static int findMaximizedCapital(int k, int w, int[] costs, int[] profits) {
        PriorityQueue<Program> minCostQueue = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Program> maxProfitsQueue = new PriorityQueue<>(new MaxProfitsComparator());
        for (int i = 0; i < costs.length; i++) {
            minCostQueue.add(new Program(costs[i], profits[i]));
        }
        for (int i = 0; i < k; i++) {
            while (!minCostQueue.isEmpty() && minCostQueue.peek().cost <= w) {
                maxProfitsQueue.add(minCostQueue.poll());
            }
            if (minCostQueue.isEmpty()) {
                return w;
            }
            w += maxProfitsQueue.poll().profits;
        }
        return w;
    }

    public static class MinCostComparator implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o1.cost - o2.cost;
        }
    }

    public static class MaxProfitsComparator implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o2.profits - o1.profits;
        }
    }
}
