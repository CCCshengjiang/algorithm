package com.cwblue.heap.improved;

import java.util.Comparator;

public class test {
    public static void main(String[] args) {
        Student s1 = new Student(1, "张三", 18, "西安");
        Student s2 = new Student(2, "李四", 20, "重庆");
        Student s3 = new Student(3, "王五", 19, "成都");
        Student s4 = new Student(4, "赵六", 22, "深圳");
        Student s5 = new Student(5, "钱七", 21, "北京");

        MyHeap<Student> myHeap = new MyHeap<>(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if(o1.getId() < o2.getId()) {
                    return -1;
                } else if (o1.getId() > o2.getId()) {
                    return 1;
                }else {
                    return 0;
                }

                //  return o1.getId() - o2.getId();
            }
        });
        myHeap.push(s1);
        myHeap.push(s2);
        myHeap.push(s3);
        myHeap.push(s4);
        myHeap.push(s5);

        System.out.println(myHeap.isEmpty());
        System.out.println(myHeap.getHeapSize());
        System.out.println("====================");
        s1.setId(15);
        myHeap.resign(s1);
        while (!myHeap.isEmpty()) {
            System.out.println(myHeap.poll().toString());
        }

    }
}
