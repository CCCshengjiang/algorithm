# 算法总结

## 1 排序算法

![image-20231107133459995](D:\AAA技术没有高低之分\picture\image-20231107133459995.png)

### 1.1 快速排序

#### 1.1.1 算法思想

- 先取一个随机数，然后和数组的最后一个数交换

- 进行`partition`过程，也就是比数组最后一个数小的放在数组左边，大的放在右边，相等的在数组中间，最后把数组的最后一个数也要放到中间位置，然后返回相等的那一批数的最左索引和最右索引。
- 递归前两个过程

#### 1.1.2 时间复杂度

```java
O (N * logN)
```

#### 1.1.3 代码实现

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

### 1.2 堆排序

#### 1.2.1 算法思想

- 给了一个数组，把数组看成完全二叉树结构，现在开始变成堆
- 从完全二叉树的最后一个值开始进行`heapify`过程，也就是把每一个值都要和子节点比较大小，把这个节点为顶的树变成堆结构
- 变成大根堆堆结构之后，将堆顶元素和数组最后一个元素互换，堆的长度减一的同时要进行`heapify`操作，把剩下元素的要恢复堆结构
- 重复第三步操作，每次都取一个最大值出来放到原堆的最后，数组有序

#### 1.2.2 时间复杂度

```java
O (N * logN)
```

#### 1.2.3 代码实现

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





### 1.3 桶排序

**不基于比较**的排序算法，，分为两类，但是都有约束条件

- 计数排序：要排序的数都要是0或正整数
- 基数排序：必须是十进制的数，而且是0或正整数

给的数越多，代价越大。一旦要升级的话，要付出的代价更加显而易见。

要排序的最大的数越大，计数排序需要的空间就越多，要排序比如{9，1，96656412}，那么此时就需要辅助数组的长度就要是96656413了，很明显性价比不高，就需要用基数排序，基数排序只用两个辅助数组，而且一个计数器数组长度长度固定为10，一个辅助数组长度和要排序的数组长度相同，浪费的空间小。但是如果要排序的数中最大数越小，那么此时就可以用计数排序。

#### 1.3.1 计数排序

##### 算法思想

- 给定数组，范围[0，intMax]
- 定义一个辅助数组，辅助数组的长度就是给定数组的长度
- 遍历数组，给定数组的值与辅助数组的索引对应，
- 每当有一个给定数组的值等于辅助数组的索引，那么这个索引上的值加一
- 遍历完成之后，再遍历辅助数组，
- 每当辅助数组的值不为0，就把辅助数组的索引覆盖在给定数组中
- 每覆盖一次，辅助数组的值减一，直到减为0才能遍历下一次

##### 时间复杂度

```java
O (N)
```

##### 代码实现

```java
public class CountSort {
    public static void countSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int[] bucket = new int[max + 1];
        for (int a : arr) {
            bucket[a]++;
        }
        int index = 0;
        for (int i = 0; i < bucket.length; i++) {
            while (bucket[i]-- > 0) {
                arr[index++] = i;
            }
        }
    }
	// 写比较器验证
    public static void comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        Arrays.sort(arr);
    }

    public static int[] generateRandomArr(int arrLen) {
        int[] ans = new int[arrLen];
        for (int i = 0; i < arrLen; i++) {
            int num = (int) (Math.random() * 1000 + 1);
            ans[i] = num;
        }
        return ans;
    }
    
    public static void main(String[] args) {
        int arrLength = 1000;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int arrLen = (int) (Math.random() * arrLength + 1);
            int[] arr1 = generateRandomArr(arrLen);
            int[] arr2 = new int[arr1.length];
            for (int j = 0; j < arr1.length; j++) {
                arr2[j] = arr1[j];
            }
            countSort(arr1);
            comparator(arr2);
            for (int j = 0; j < arr1.length; j++) {
                if (arr1[j] != arr2[j]) {
                    System.out.println("Oops!");
                }
            }
        }
        System.out.println("Finish!");
    }
}
```



#### 1.3.2 基数排序

##### 算法思想

**算法步骤：**

- 给定数组的最大数有几位就遍历几遍（最外层的遍历），先从个位数字开始遍历

  - 目的是从个位数字开始，先把数组中的每个数的个位数字排序，之后再排十位，以此类推。

  - 第一次遍历

    - 遍历每个数组，遍历的过程中，只看现在遍历的数的个位数字，也就是不管数组有多大，每次看的数字只有[0,9]

    - 定义一个计数器数组，每个数字出现一个，计数器数组的与这个数字相等的索引上的值加一

  - 第二次遍历

    - 遍历计数器数组，数组中的每个数等于前面自己的值和前一个数的值相加
    - 目的是找出比这个数小的数字有多少个

  - 第三次遍历

    - 定义一个辅助数组，长度和给定数组的长度相同
    - 从给定数组的末尾开始往前遍历
    - 看计数器数字中，和给定数组的末尾数字的个为数字相同的数字有几个，也就是找比这个数字小的或者等于有多少个
    - 这个数字减一就是辅助数组的索引，索引上放的数字就是给定数组的现在遍历到的数。
    - 这个理解就相当于队列，小的肯定在前面，相同的先进的先出（第一次遍历中，先遍历的在前面，后遍历的在后面，那么最后一个遍历的肯定在最后面）

  - 第四次遍历

    - 交换辅助数组和给定数组的每一个值
    - 接下来就要结束整个大的个位数的遍历，开始遍历十位上的数

##### 时间复杂度

```java
O(N)
```

##### 代码实现

```java
public class RadixSort {
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        radixSort(arr, 0, arr.length - 1, maxBits(arr));
    }

    // 求数组中最大的数有几位：1000  --->  4位
    private static int maxBits(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int ans = 0;
        while (max != 0) {
            max /= 10;
            ans++;
        }
        return ans;
    }

    // 基数排序的实现
    public static void radixSort(int[] arr, int left, int right, int digit) {
        final int radix = 10;
        int[] help = new int[right - left + 1];
        for (int d = 1; d <= digit; d++) {
            int[] count = new int[radix];
            int i = 0;
            int j = 0;
            for (i = left; i <= right; i++) {
                j= getDigit(arr[i], d);
                count[j]++;
            }
            for (i = 1; i < radix; i++) {
                count[i] += count[i - 1];
            }
            for (i = right; i >= left; i--) {
                j = getDigit(arr[i], d);
                help[count[j] - 1] = arr[i];
                count[j]--;
            }
            for (i = left, j = 0; i <= right; i++, j++) {
                arr[i] = help[j];
            }
        }
    }

    // num这个数的digit位的数字：(123，1) ---> 3
    private static int getDigit(int num, int digit) {
        return ((num / (int) Math.pow(10, digit - 1)) % 10);
    }

    // 对数器测试
    public static void comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        Arrays.sort(arr);
    }

    public static int generateRandomNum(int num) {
        return (int) (Math.random() * num + 1);
    }

    public static int[] generateRandomArr(int arrLen, int num) {
        int length = (int) (Math.random() * arrLen + 1);
        int[] ans = new int[length];
        for (int i = 0; i < length; i++) {
            ans[i] = generateRandomNum(num);
        }
        return ans;
    }
    public static void main(String[] args) {
        int num = 1000;
        int arrLen = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = generateRandomArr(arrLen, num);
            int[] arr2 = new int[arr1.length];
            for (int j = 0; j < arr1.length; j++) {
                arr2[j] = arr1[j];
            }
            radixSort(arr1);
            comparator(arr2);
            for (int j = 0; j < arr1.length; j++) {
                if (arr2[j] != arr1[j]) {
                    System.out.println("Oops!");
                }
            }
        }
        System.out.println("Finish!");
    }
}

```



### 排序算法总结

#### 1 总结

1. 不基于比较的排序（桶排序），对样本数据有严格的要求，不易改写
2. 基于比较的排序，只要规定好两个样本怎么比大小就可以直接复用
3. 基于比较的排序，时间复杂度的极限是`O(N*logN)`
4. 时间复杂度`O(N*logN)`，额外空间复杂度低于`O(N)`且稳定的基于比较的排序是**不存在**的
5. **为了绝对的速度选择快排，为了省空间选堆排，为了稳定性选归并**



#### 2 常见的坑

1. 归并排序的额外空间复杂度可以变成`O(1)`，“归并排序内部缓存法”，但是将变得不稳定。（还不如用堆排）
2. “原地归并排序”不好，会让时间复杂度变成`O(N*2)`。（不如用插入排序）
3. 快速排序稳定性改进，论文：“01 stable sort”，但是会对样本数据要求更多。（不如用桶排序）
   - 题目：在整型数组中，请把奇数放在数组左边，偶数放在数组右边，要求所有基数之间原始的相对次序不变，所有偶数之间原始相对次序不变，要求时间复杂度`O(N)`,额外空间复杂度`O(1)`。



#### 3 排序优化

1. 稳定性的考虑：数据是值传递直接快排，引用传递用归并排序
2. 充分利用`O(N*logN)`和`O(N^2)`排序各自的优势：小样本量直接插入排序













## 2 算法结构

### 2.1 堆

#### 2.1.1 堆结构

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

#### 2.1.2 改进的堆结构

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





### 2.2 前缀树

可以完成前缀相关的查询

#### 2.2.1 前缀树的结构

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



## 3 链表

### 3.1 回文链表

#### 3.1.1 判断方法

1. 第一种（笔试）：
   - 链表从中间分开，把后半部分的节点放到栈中
   - 从链表的头结点开始，依次和弹出的节点比较
2. 第二种（面试）：
   - 反转链表的后半部分，中间节点的下一节点指向空
   - 两个指针分别从链表的头和尾出发比较
   - 直到左边节点到空或两个节点都到空停止
   - 判断结果出来后，要把链表后半部分反转回去。

#### 3.1.2 代码实现

```java
ublic class IsPalindromList {
    public static class Node {
        public int val;
        public Node next;
        public Node(int data) {
            val = data;
            next = null;
        }
    }

    public static boolean isPalindrom1(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Stack<Node> stack = new Stack<>();
        Node mid = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            mid = mid.next;
            fast = fast.next.next;
        }
        while (mid.next != null) {
            mid = mid.next;
            stack.add(mid);
        }
        Node i = head;
        boolean ans = true;
        while (stack.size() != 0) {
            if (i.val != stack.pop().val) {
                ans = false;
                break;
            }
            i = i.next;
        }
        return ans;
    }
    public static boolean isPalindrom2(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        // 找到中间节点
        Node mid = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            mid = mid.next;
            fast = fast.next.next;
        }
        Node cur = mid;
        // 后边段链表反转
        Node pre = null;
        Node next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        Node left = head;
        Node right = pre;
        boolean ans = true;
        while (left != null && right != null) {
            if (left.val != right.val) {
                ans = false;
                break;
            }
            left = left.next;
            right = right.next;
        }

        cur = pre;
        pre = null;
        next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return ans;
    }


    public static void main(String[] args) {
        Node head = new Node(0);
        head.next = new Node(1);
        head.next.next = new Node(2);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        head.next.next.next.next.next = new Node(0);

        System.out.println(isPalindrom2(head));
        System.out.println(isPalindrom1(head));
    }

}
```



### 3.2 荷兰国旗

链表的荷兰国旗问题，给定一个链表头节点，一个划分值

- 把链表中小于划分值的放在左边
- 等于划分值的放在中间
- 大于划分值的放在右边。

#### 3.2.1 解决方法

（其实我第一次写的时候第二种方法比第一种方法写的快，因为思路简单，不绕。）

1. 第一种（笔试）：
   - 把链表的所有节点放在一个节点数组中
   - 对这个数组做`partition`过程
   - 之后把数组每个节点连起来
2. 第二种（面试）：
   - 定义有六个节点，分别代表：大于、等于和小于区域的头和尾
   - 链表开始遍历，比划分值小的连在小于区域下方，同时断开节点和之前链表的关系，指向空
   - 等于和大于同理
   - 遍历完链表之后，开始把小于区域的尾巴和等于区域的头连接，等于区域的尾巴和大于区域的头连接

#### 3.2.2 代码实现

```java
public class SmallEqualBig {
    public static class Node {
        public int val;
        public Node next;
        public Node(int val) {
            this.val = val;
            next = null;
        }
    }

    public static Node listPartition1(Node head, int pivot) {
        if (head == null || head.next == null) {
            return head;
        }
        int index = 1;
        Node cur = head;
        while (cur.next != null) {
            index++;
            cur = cur.next;
        }
        Node[] arr = new Node[index];
        cur = head;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = cur;
            cur = cur.next;
        }
        partition(arr,pivot);
        for (index = 1; index != arr.length; index++) {
            arr[index - 1].next = arr[index];
        }
        arr[index - 1].next = null;
        return arr[0];
    }

    public static Node listPartition2(Node head, int pivot) {
        if (head == null || head.next == null) {
            return head;
        }
        Node smallHead = null;
        Node smallTail = null;
        Node equalHead = null;
        Node equalTail = null;
        Node bigHead = null;
        Node bigTail = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.val < pivot) {
                if (smallHead == null) {
                    smallHead = head;
                    smallTail = head;
                }else {
                    smallTail.next = head;
                    smallTail = smallTail.next;
                }
            } else if (head.val == pivot) {
                if (equalHead == null) {
                    equalHead = head;
                    equalTail = head;
                }else {
                    equalTail.next = head;
                    equalTail = equalTail.next;
                }
            }else {
                if (bigHead == null) {
                    bigHead = head;
                    bigTail = head;
                }else {
                    bigTail.next = head;
                    bigTail = bigTail.next;
                }
            }
            head = next;
        }
        if (smallTail != null) {
            smallTail.next = equalHead;
            equalTail = equalTail == null ? smallTail : equalTail;
        }
        if (equalTail != null) {
            equalTail.next = bigHead;
        }
        return smallHead == null ? (equalHead == null ? bigHead : equalHead) : smallHead;
    }

    private static void partition(Node[] arr, int pivot) {
        int small = -1;
        int big = arr.length;
        int index = 0;
        while (index < big) {
            if(arr[index].val < pivot) {
                swap(arr, index++, ++small);
            } else if (arr[index].val > pivot) {
                swap(arr, index++, --big);
            }else {
                index++;
            }
        }
    }

    private static void swap(Node[] arr, int i, int j) {
        Node temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Node head = new Node(9);
        head.next = new Node(1);
        head.next.next = new Node(2);
        head.next.next.next = new Node(6);
        head.next.next.next.next = new Node(4);
        head.next.next.next.next.next = new Node(5);
        head.next.next.next.next.next.next = new Node(2);

        Node node = listPartition1(head, 2);

        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();

        Node head2 = new Node(9);
        head2.next = new Node(1);
        head2.next.next = new Node(2);
        head2.next.next.next = new Node(6);
        head2.next.next.next.next = new Node(4);
        head2.next.next.next.next.next = new Node(5);
        head2.next.next.next.next.next.next = new Node(2);

        Node node1 = listPartition2(head2, 2);
        while (node1 != null) {
            System.out.print(node1.val + " ");
            node1 = node1.next;
        }

    }
}
```







































## 算法的稳定性

稳定性是指同样大小的样本在排序之后不会改变相对次序。

- 对基础类型来说，稳定性毫无意义
- 对非基础类型来说，稳定性有重要意义

有些排序算法可以实现成稳定的，而有些排序算法无论如何都实现不了稳定性。

![image-20231107133832725](D:\AAA技术没有高低之分\picture\image-20231107133832725.png)



























































# Attention：

## 1、特别的计算

1. 求一个数num的第d位数：

   ```java
   (num / (int) Math.pow(10, d - 1)) % 10; 
   ```

   







## 2、比较器原理

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





## 3、对数器

对数器用来验证自己写的算法是否严丝合缝的正确。

1. 一个是自己实现的算法（要考虑时间复杂度或者空间复杂度）
2. 用来比较的是Java已有的或者暴力写出来的算法（不用在乎空间复杂度和时间复杂度，但是一定是正确的）
3. 两个算法进行大量的随机的同步的比较
4. 比较完之后全部相同输出`finish`，只要有一处不一样就输出一个`Oops`。



步骤：

1. 先定义一个比较次数，比如100万次（大量的比较下才能确定算法是严丝合缝的正确）
2. 定义一个概率，比如概率小于0.25进行插入，小于0.5进行删除操作，小于0.75进行查询操作，剩下的概率进行其他操作
3. 确保每次遍历时，概率和要进行操作的数据都是随机的
4. 每进行一次比较就要判断一下，不一样打印
5. 100万次遍历完，打印完成

