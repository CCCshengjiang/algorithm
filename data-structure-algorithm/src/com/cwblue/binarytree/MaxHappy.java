package com.cwblue.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * 派对的最大快乐值
 * 这个公司现在要办party，你可以决定哪些员工来，哪些员工不来，规则:
 * 1.如果某个员工来了，那么这个员工的所有直接下级都不能来
 * 2.派对的整体快乐值是所有到场员工快乐值的累加
 * 3.你的目标是让派对的整体快乐值尽量大给定一棵多叉树的头节点boss，请返回派对的最大快乐值
 *
 * @author wen
 */
public class MaxHappy {
    public static class Employee{
        public int happy;
        List<Employee> next;
        public Employee(int happy){
            this.happy = happy;
            next = new ArrayList<>();
        }
    }

    public static class Info {
        public int yes;
        public int no;
        public Info(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }
    public static int getMaxHappy(Employee boss) {
        if (boss == null) {
            return 0;
        }
        Info allHappy = process(boss);
        return Math.max(allHappy.yes, allHappy.no);
    }

    private static Info process(Employee node) {
        // 基层员工的信息
        if (node.next.isEmpty()) {
            return new Info(node.happy, 0);
        }
        int yes = node.happy;
        int no = 0;
        for (Employee next : node.next) {
            // 递归
            Info nextInfo = process(next);
            // 父节点去的话，子节点都不去 的最大快乐值
            yes += nextInfo.no;
            // 父节点不去，子节点在去或不去的快乐值中选最大的
            no += Math.max(nextInfo.yes, nextInfo.no);
        }
        return new Info(yes, no);
    }

    public static void main(String[] args) {
        Employee boss = new Employee(10);
        Employee employee0 = new Employee(10);
        Employee employee1 = new Employee(5);
        Employee employee2 = new Employee(6);
        Employee employee3 = new Employee(7);
        Employee employee4 = new Employee(3);
        Employee employee5 = new Employee(2);
        Employee employee6 = new Employee(4);
        Employee employee7 = new Employee(1);
        Employee employee8 = new Employee(2);
        Employee employee9 = new Employee(3);

        boss.next.add(employee0);
        employee0.next.add(employee1);
        employee0.next.add(employee2);
        employee0.next.add(employee3);
        employee1.next.add(employee4);
        employee2.next.add(employee5);
        employee3.next.add(employee6);
        employee4.next.add(employee7);
        employee5.next.add(employee8);
        employee6.next.add(employee9);

        System.out.println(getMaxHappy(boss));
    }
}
