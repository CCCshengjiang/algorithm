package cn.cwblue.linkedlist;

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
