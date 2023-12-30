package cn.cwblue.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
 * 给你每一个项目开始的时间和结束的时间
 * 你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。
 * 返回最多的宣讲的场次。
 *
 * @author wen
 */
public class BestArrange {
    public static class Programs {
        public int start;
        public int end;
        public Programs(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    
    public static int bestArrange(Programs[] programs) {
        if (programs == null || programs.length == 0) {
            return 0;
        }
        return process(programs, 0, 0);
    }

    /**
     * 暴力递归求解
     * @param programs 还剩多少会议
     * @param done 之前已经安排的会议数量
     * @param timeLine 当前的时间
     * @return 返回能安排的最多的会议数量
     */
    private static int process(Programs[] programs, int done, int timeLine) {
        if (programs.length == 0) {
            return done;
        }
        int max = done;
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].start >= timeLine) {
                Programs[] next = copyButExcept(programs, i);
                max = Math.max(max, process(next, done + 1, programs[i].end));
            }
        }
        return max;
    }

    private static Programs[] copyButExcept(Programs[] programs, int i) {
        Programs[] ans = new Programs[programs.length - 1];
        int index = 0;
        for (int j = 0, programsLength = programs.length; j < programsLength; j++) {
            if (j != i) {
                ans[index++] = programs[j];
            }
        }
        return ans;
    }

    /**
     * 贪心算法
     * @param programs 总会议数
     * @return 然会能安排的最多的会议数量
     */
    public static int bestArrange2(Programs[] programs) {
        Arrays.sort(programs, Comparator.comparingInt(o -> o.end));
        int timeLine = 0;
        int res = 0;
        for (Programs program : programs) {
            if (program.start >= timeLine) {
                timeLine = program.end;
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Programs[] programs = new Programs[6];
        programs[0] = new Programs(8, 10);
        programs[1] = new Programs(8, 9);
        programs[2] = new Programs(10, 12);
        programs[3] = new Programs(12, 13);
        programs[4] = new Programs(10, 13);
        programs[5] = new Programs(9, 10);

        if (bestArrange(programs) == bestArrange2(programs)) {
            System.out.println("Finish!");
        }else {
            System.out.println("Oops!");
        }
    }
}
