# 算法总结

## 1、排序算法

### 1.1、快速排序

#### 1.1.1、算法思想

- 先取一个随机数，然后和数组的最后一个数交换

- 进行`partition`过程，也就是比数组最后一个数小的放在数组左边，大的放在右边，相等的在数组中间，最后把数组的最后一个数也要放到中间位置，然后返回相等的那一批数的最左索引和最右索引。
- 递归前两个过程

#### 1.1.2、时间复杂度

```java
O (N * logN)
```

#### 1.1.3、代码实现

```java
public class QuickSort {
    private static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        swap(arr, left + (int)(Math.random() * (right - left + 1)), right);
        int[] partition = partition(arr, left, right);
        process(arr, left, partition[0] - 1);
        process(arr, partition[1] + 1, right);
    }

    private static int[] partition(int[] arr, int left, int right) {
        int index = left;
        int small = left - 1;
        int big = right;
        while (index < big) {
            if (arr[index] == arr[right]) {
                index++;
            } else if (arr[index] < arr[right]) {
                swap(arr, index++, ++small);
            }else {
                swap(arr, index, --big);
            }
        }
        swap(arr, right, big);
        return new int[] {++small, big};
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,8,9,5};
        quickSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
```

### 1.2、堆排序

#### 1.2.1、算法思想

- 给了一个数组，把数组看成完全二叉树结构，现在开始变成堆
- 从完全二叉树的最后一个值开始进行`heapify`过程，也就是把每一个值都要和子节点比较大小，把这个节点为顶的树变成堆结构
- 变成大根堆堆结构之后，将堆顶元素和数组最后一个元素互换，堆的长度减一的同时要进行`heapify`操作，把剩下元素的要恢复堆结构
- 重复第三步操作，每次都取一个最大值出来放到原堆的最后，数组有序

#### 1.2.2、时间复杂度

```java
O (N * logN)
```

#### 1.2.3、代码实现

```java
public class HeapSort {
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int heapSize = arr.length;
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, heapSize);
        }
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    private static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize ? (arr[left] < arr[left + 1] ? left + 1 : left) : left;
            largest = arr[index] < arr[largest] ? largest : index;
            if (largest == index) {
                return;
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {1,9,7,3,6,4,8,2,5};
        heapSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
```





















## 2、算法结构

### 2.1、堆

#### 2.1.1、堆结构

1. 堆是用数组实现的完全二叉树结构
2. 完全二叉树中如果每棵树的最大值都在顶部就是大根堆，最小值在顶部就是小根堆
3. 堆结构的`heapInsert`就是插入操作，`heapify`是取出数组后进行堆结构调整的操作
4. 优先级队列结构就是堆结构

```java
public class Heap {
    // 大根堆结构
    public static class MyMaxHeap {
        private int[] heap;
        private final int limit;
        private int heapSize;
        public MyMaxHeap(int limit) {
            heap = new int[limit];
            this.limit = limit;
            heapSize = 0;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        public void push(int value) {
            if(heapSize == limit) {
                throw new RuntimeException("Heap is full!");
            }
            heap[heapSize] = value;
            heapInsert(heap, heapSize++);
        }

        public int pop() {
            if(heapSize == 0) {
                throw new RuntimeException("Heap is empty!");
            }
            int ans = heap[0];
            swap(heap, 0, --heapSize);
            heapify(heap, 0, heapSize);
            return ans;
        }

        private void heapify(int[] heap, int index, int heapSize) {
            int left = index * 2 + 1;
            while (left < heapSize) {
                int largest = left + 1 > heapSize ? left : (heap[left] > heap[left + 1] ? left : left + 1);
                largest = heap[largest] > heap[index] ? largest : index;
                if (largest == index) {
                    return;
                }
                swap(heap, index, largest);
                index = largest;
                left = index * 2 - 1;
            }
        }

        private void heapInsert(int[] heap, int index) {
            while (heap[index] > heap[(index - 1) / 2]) {
                swap(heap, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void swap(int[] heap, int i, int j) {
            int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }
    }

    // 参照组：暴力
    public static class RightMaxHeap {
        private int[] heap;
        private final int limit;
        private int heapSize;

        public RightMaxHeap(int limit) {
            heap = new int[limit];
            this.limit = limit;
            heapSize = 0;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        public void push(int value) {
            if(heapSize == limit) {
                throw new RuntimeException("Heap is full!");
            }
            heap[heapSize++] = value;
        }

        public int pop() {
            if(heapSize == 0) {
                throw new RuntimeException("Heap is empty!");
            }
            int maxIndex = 0;
            for (int i = 1; i < heap.length; i++) {
                if(heap[maxIndex] < heap[i]) {
                    maxIndex = i;
                }
            }
            int ans = heap[maxIndex];
            heap[maxIndex] = heap[--heapSize];
            return ans;
        }
    }

    public static void main(String[] args) {
        // 写对数器验证堆结构
        int value = 1000;
        int limit = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int curLimit = (int)(Math.random() * limit) + 1;
            RightMaxHeap test = new RightMaxHeap(curLimit);
            MyMaxHeap my = new MyMaxHeap(curLimit);
            int curOpTimes = (int)(Math.random() * limit);
            for (int j = 0; j < curOpTimes; j++) {
                if (my.isEmpty() != test.isEmpty()) {
                    System.out.println("Oops!");
                }
                if (my.isFull() != test.isFull()) {
                    System.out.println("Oops!");
                }
                if (my.isEmpty()) {
                    int curValue = (int)(Math.random() * value);
                    my.push(value);
                    test.push(value);
                }else if (my.isFull()) {
                    if(my.pop() != test.pop()) {
                        System.out.println("Oops!");
                    }
                }else {
                    if(Math.random() < 0.5) {
                        int curValue = (int)(Math.random() * value);
                        my.push(value);
                        test.push(value);
                    }else {
                        if(my.pop() != test.pop()) {
                            System.out.println("Oops!");
                        }
                    }
                }
            }
        }
        System.out.println("Finish!");
    }
}
```

#### 2.1.2、改进的堆结构

为什么要改进？

- 原始堆，传进去的东西比如`Student`类，我不修改这个类的任何值，用原始堆没问题。
- 但是要是想把传进去的`Student`类修改一下，比如修改年龄或者id，那么就必须要使用改进的堆。
- 改进的堆增加`resign`方法，可以在修改已经在堆里面的值之后还能形成堆结构。



改进的堆：

```java
public class MyHeap<T> {
    private ArrayList<T> heap;
    private HashMap<T, Integer> indexMap;
    private int heapSize;
    private Comparator<? super T> comparator;

    public MyHeap(Comparator<? super T> comparator) {
        heap = new ArrayList<>();
        indexMap = new HashMap<>();
        heapSize = 0;
        this.comparator = comparator;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int getHeapSize() {
        return heapSize;
    }

    public void push(T value) {
        heap.add(value);
        indexMap.put(value, heapSize);
        heapInsert(heapSize++);
    }

    public T poll() {
        T ans = heap.get(0);
        int end = heapSize - 1;
        swap(0, end);
        heap.remove(end);
        indexMap.remove(ans);
        heapify(0, --heapSize);
        return ans;
    }

    public void resign(T value) {
        int valueIndex = indexMap.get(value);
        heapify(valueIndex, heapSize);
        heapInsert(valueIndex);
    }

    private void heapify(int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = (left + 1 < heapSize) && (comparator.compare(heap.get(left + 1), heap.get(left)) < 0) ? left + 1 : left;
            largest = (comparator.compare(heap.get(largest), heap.get(index)) < 0) ? largest : index;
            if (largest == index) {
                return;
            }
            swap(largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    private void heapInsert(int index) {
        while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void swap(int i, int j) {
        T o1 = heap.get(i);
        T o2 = heap.get(j);
        heap.set(i, o2);
        heap.set(j, o1);
        indexMap.put(o1, j);
        indexMap.put(o2, i);
    }
}
```



学生类：

```java
public class Student {
    private int id;
    private String name;
    private int age;
    private String address;


    public Student() {
    }

    public Student(int id, String name, int age, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    /**
     * 获取
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * 设置
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 获取
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    public String toString() {
        return "Student{id = " + id + ", name = " + name + ", age = " + age + ", address = " + address + "}";
    }
}
```



测试：

```java
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
                return o2.getId() - o1.getId();
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
```





### 2.2、前缀树

可以完成前缀相关的查询

#### 2.2.1、前缀树的结构

1. 单个字符串中的字符从前到后加到一颗多叉树上
2. 字符放在路上，节点上有专属的数据项（常见的是pass和end）
3. 所有样本都这样添加，如果没有路就新建，如果有路就复用
4. 沿途所有的经过的节点的pass值加1，每个字符串结束时来到的节点的end值加1

```java
// Node和TrieTree结合使用，Node2和TrieTree2结合使用。
// TrieTree2只能加入小写字符组成的字符串，有局限性
// TrieTree可以都加入
public class TrieTreeSearch {
    public static class Node {
        public int pass;
        public int end;
        public HashMap<Integer, Node> next;

        public Node() {
            pass = 0;
            end = 0;
            next = new HashMap<>();
        }
    }

    public static class TrieTree {
        private final Node root;

        public TrieTree() {
            root = new Node();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            Node node = root;
            node.pass++;
            char[] chars = word.toCharArray();
            int index = 0;
            for (char aChar : chars) {
                index = aChar;
                if (!node.next.containsKey(index)) {
                    node.next.put(index, new Node());
                }
                node = node.next.get(index);
                node.pass++;
            }
            node.end++;
        }

        public void delete(String word) {
            if (search(word) != 0) {
                Node node = root;
                node.pass--;
                int index = 0;
                char[] chars = word.toCharArray();
                for (char aChar : chars) {
                    index = aChar;
                    if (node.next.containsKey(index)) {
                        node = node.next.get(index);
                    }
                    node.pass--;
                }
                node.end--;
            }
        }

        public int search(String word) {
            return research(word).end;
        }

        public int prefixNum(String word) {
            return research(word).pass;
        }

        private Node research(String word) {
            if (word == null) {
                return new Node();
            }
            Node node = root;
            char[] chars = word.toCharArray();
            int index = 0;
            for (char aChar : chars) {
                index = aChar;
                if (!node.next.containsKey(index)) {
                    return new Node();
                }
                node = node.next.get(index);
            }
            return node;
        }
    }

    public static class Node2 {
        public int pass;
        public int end;
        public Node2[] next;

        public Node2() {
            pass = 0;
            end = 0;
            next = new Node2[26];
        }
    }

    public static class TrieTree2 {
        private final Node2 root;

        // 无参构造
        public TrieTree2() {
            root = new Node2();
        }

        // 添加字符串
        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] chars = word.toCharArray();
            Node2 node = root;
            node.pass++;
            int index = 0;
            for (char aChar : chars) {
                index = aChar - 'a';
                if (node.next[index] == null) {
                    node.next[index] = new Node2();
                }
                node = node.next[index];
                node.pass++;
            }
            node.end++;
        }

        // 查找字符串有多少个
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] chars = word.toCharArray();
            Node2 node = root;
            int index = 0;
            for (char aChar : chars) {
                index = aChar - 'a';
                if (node.next[index] == null) {
                    return 0;
                }
                node = node.next[index];
            }
            return node.end;
        }

        // 删除字符串
        public void delete(String word) {
            if (search(word) != 0) {
                Node2 node = root;
                node.pass--;
                int index = 0;
                char[] chars = word.toCharArray();
                for (char aChar : chars) {
                    index = aChar - 'a';
                    if (--node.next[index].pass == 0) {
                        node.next[index] = null;
                        return;
                    }
                    node = node.next[index];
                }
                node.end--;
            }
        }

        // 有几个字符串前缀是word
        public int prefixNum(String word) {
            if (word == null) {
                return 0;
            }
            char[] chars = word.toCharArray();
            Node2 node = root;
            int index = 0;
            for (char aChar : chars) {
                index = aChar - 'a';
                if (node.next[index] == null) {
                    return 0;
                }
                node = node.next[index];
            }
            return node.pass;
        }

    }

    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 26);
            ans[i] = (char) (value + 97);
        }
        return String.valueOf(ans);
    }

    public static String[] generateRandomString(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }


    // 写对数器进行测试
    public static void main(String[] args) {
        int strLen = 20;
        int arrLen = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            String[] strings = generateRandomString(arrLen, strLen);
            TrieTree my = new TrieTree();
            TrieTree2 test = new TrieTree2();
            for (String word : strings) {
                double decide = Math.random();
                if (decide < 0.25) {
                    my.insert(word);
                    test.insert(word);
                } else if (decide < 0.5) {
                    my.delete(word);
                    test.delete(word);
                } else if (decide < 0.75) {
                    int ans1 = my.search(word);
                    int ans2 = test.search(word);
                    if(ans1 != ans2) {
                        System.out.println("Oops!");
                    }
                }else {
                    int ans1 = my.prefixNum(word);
                    int ans2 = test.prefixNum(word);
                    if (ans1 != ans2) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        System.out.println("Finish!");
    }
}

```

























































































# Attention：

## 1、比较器原理

```java
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
```

- 返回负数的时候，第一个参数排在前面
- 返回正数的时候，第二个参数排在前面
- 返回0，谁排前面无所谓



我的理解：做减法（注释的那一行代码），第一个参数减第二个参数

- 如果是负数，说明第一个参数的值小，此时就是返回小的那个值，就是从小到大正序排列
- 如果是正数，说明第一个参数的值大，此时就是返回大的那个值，就是从大到小逆序排列
- 但是如果是第二个参数减第一个参数，那么排序结果就是反的