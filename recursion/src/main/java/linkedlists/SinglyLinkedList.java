/*
 * Copyright (C) 2022 Manjosh Ramesh. - All Rights Reserved
 *
 */

package linkedlists;

public class SinglyLinkedList {
    private ListNode head;


    //Simple Node
    private static class ListNode {
        int data;
        ListNode next;

        public ListNode(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public void printNodes(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.data + " ->");
            current = current.next;
        }
        System.out.println("null");
    }

    public void printNodes() {
        printNodes(head);
    }

    //count the length of the linkedList
    public int length() {
        int count = 0;
        ListNode current = head;
        while (current != null) {
            current = current.next;
            count++;
        }
        return count;
    }

    //insert a new node at the begining of the linkedList
    public void insertAtBegning(int value) {
        ListNode newNode = new ListNode(value);
        newNode.next = head;
        head = newNode;
    }

    // insert a new node to the linkedList
    public void add(int value) {
        ListNode newNode = new ListNode(value);
        if (head == null) {
            head = newNode;
            return;
        }
        ListNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    public void insertAt(int value, int index) {
        ListNode node = new ListNode(value);
        if (index == 0) {
            node.next = head;
            head = node;
        } else {
            ListNode prev = null;
            ListNode current = head;
            int count = 0;
            while (count < index) {
                if (current == null) {
                    throw new IllegalArgumentException("Index out of bound");
                }
                prev = current;
                current = current.next;
                count++;
            }
            prev.next = node;
            node.next = current;
        }
    }

    public ListNode deleteFirst() {
        if (head == null) return null;
        ListNode temp = head;
        this.head = head.next;
        temp.next = null;
        return temp;
    }

    public ListNode deleteLast() {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode prev = null;
        ListNode current = head;
        while (current.next != null) {
            prev = current;
            current = current.next;
        }
        prev.next = null;
        return current;
    }

    public ListNode deleteAt(int index) {
        if (head == null) return null;
        ListNode prev = null;
        ListNode current = head;
        int count = 0;
        while (count < index) {
            if (current == null) throw new IllegalArgumentException("Index out of bounds");
            prev = current;
            current = current.next;
            count++;
        }
        if (prev == null) {
            head = current.next;
            current.next = null;
            return current;
        }
        prev.next = current.next;
        current.next = null;
        return current;
    }

    public ListNode getNthNodeFromEnd(int n) {
        if (head == null) return null;
        if (n < 0) throw new IllegalArgumentException("Invalid value of n :" + n);
        ListNode ref = head;
        ListNode main = head;
        int count = 0;
        while (count < n) {
            if (ref == null) throw new IndexOutOfBoundsException("Invalid value of n :" + n);
            ref = ref.next;
            count++;
        }
        while (ref != null) {
            main = main.next;
            ref = ref.next;
        }
        return main;
    }

    public ListNode getMiddleElement() {
        if (head == null) return head;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode reverse() {
        return reverse(head);
    }

    private ListNode reverse(ListNode head) {
        if (head == null) return null;
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public boolean isPalindrome() {
        if (head == null) return true;
        ListNode start = head;
        ListNode middle = getMiddleElement();
        ListNode reversed = reverse(middle);
        while (reversed != null) {
            if (start.data != reversed.data) return false;
            start = start.next;
            reversed = reversed.next;
        }
        return true;
    }

    public ListNode reverseBetween(int m, int n) {
        if (m < 1 && n < 1 || n < m || head == null) return null;
        int count = 0;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode leftPrev = dummy;
        ListNode current = head;
        //find start
        while (count < m - 1) {
            leftPrev = current;
            current = current.next;
            count++;
        }

        ListNode prev = null;

        //count = n-count+1;
        while (count < n) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            count++;
        }
        leftPrev.next.next = current;
        leftPrev.next = prev;
        ListNode print = dummy.next;
        while (print != null) {
            System.out.print(print.data + " ->");
            print = print.next;
        }
        System.out.print("null");
        return dummy.next;
    }

    public ListNode reverseInGroups(int k, boolean all) {
        int length = 0;
        ListNode current = head;
        if (all) {
            while (current != null) {
                length++;
                current = current.next;
            }
        }
        ListNode reversed = reverseInGroups(head, k, length, !all);
        System.out.println();
        while (reversed != null) {
            System.out.print(reversed.data + " ->");
            reversed = reversed.next;
        }
        System.out.print("null");
        return reversed;
    }

    public ListNode reverseInGroups(ListNode head, int k, int length, boolean all) {
        if (head == null) return null;
        int count = 0;
        ListNode prev = null;
        ListNode current = head;
        if (length >= k || all) {
            while (current != null && count++ < k) {
                ListNode next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }
        } else {
            return head;
        }
        head.next = reverseInGroups(current, k, length - k, all);
        return prev;
    }

    public ListNode mergeTwoSortedLists(ListNode A, ListNode B) {
        if (A == null) return B;
        if (B == null) return A;
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (A != null && B != null) {
            if (A.data < B.data) {
                tail.next = A;
                A = A.next;
            } else {
                tail.next = B;
                B = B.next;
            }
            tail = tail.next;
        }
        if (A != null) {
            tail.next = A;
        }
        if (B != null) {
            tail.next = B;
        }
        System.out.println();
        printNodes(dummy.next);
        return dummy.next;
    }

    public ListNode zipperList(ListNode A, ListNode B) {
        if (A == null) return B;
        if (B == null) return A;

        ListNode dummyNode = new ListNode(-1);
        ListNode tail = dummyNode;
        int count = 0;
        while (A != null && B != null) {
            if (count % 2 == 0) {
                tail.next = A;
                A = A.next;
            } else {
                tail.next = B;
                B = B.next;
            }
            count++;
            tail = tail.next;
        }
        if (A != null) tail.next = A;
        if (B != null) tail.next = B;
        System.out.println();
        printNodes(dummyNode.next);
        return dummyNode.next;
    }

    //add two numbers represented by a linked list;

    public ListNode addLists(ListNode start1, ListNode start2) {
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        if (start1 == null) return start2;
        if (start2 == null) return start1;
        ListNode a = reverse(start1);
        ListNode b = reverse(start2);
        int carry = 0;
        while (a != null || b != null) {
            int x = a != null ? a.data : 0;
            int y = b != null ? b.data : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            ListNode newNode = new ListNode(sum % 10);
            newNode.next = tail.next;
            tail.next = newNode;
            if (a != null) a = a.next;
            if (b != null) b = b.next;
        }
        if (carry > 0) {
            ListNode newNode = new ListNode(carry);
            newNode.next = tail.next;
            tail.next = newNode;
        }
        return dummy.next;

    }

    //remove duplicates from unsorted list
    public ListNode removeDuplicates(ListNode head) {
        // Your code here
        if (head == null) return head;
        ListNode dummy = new ListNode(-1);
        int[] count = new int[1000000];
        ListNode ptr = head;
        while (ptr != null) {
            count[ptr.data]++;
            ptr = ptr.next;
        }
        ptr = head;
        ListNode ptr2 = dummy;
        while (ptr != null) {
            int i = ptr.data;
            if (count[i] > 0) {
                count[i] = 0;
                ptr2.next = ptr;
                ptr2 = ptr2.next;
            }
            ptr = ptr.next;
            ptr2.next = null;
        }
        printNodes(dummy.next);
        return dummy.next;
    }
    //pairwise swap:

    public ListNode pairWiseSwap(ListNode head) {
        if (head == null) return null;

        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        ListNode ptr1 = head;
        ListNode ptr2 = head.next;
        while (true) {
            ListNode next = ptr2.next;
            tail.next = ptr2;
            tail = tail.next;
            tail.next = ptr1;
            tail = tail.next;
            ptr1 = next;
            if (next == null || next.next == null) {
                break;
            }
            ptr2 = ptr1.next;
        }
        if (ptr1 == null)
            tail.next = null;
        else
            tail.next = ptr1;
        printNodes(dummy.next);
        return dummy.next;
    }

    public int sumOfLastNElements(ListNode head, int n) {
        /*int sum = 0;
        if(head == null || n <0) return sum;
        ListNode ptr = head;
        while( n >0 && ptr !=null){
            sum = sum+ptr.data;
            ptr = ptr.next;
            n--;
        }
        if(ptr == null && n >0) return 0;

        if(ptr ==null){
            return sum;
        }
        ListNode curr = head;
        while (ptr != null){
            ptr = ptr.next;
            curr = curr.next;
        }
        sum = 0;
        while (curr != null){
            sum =sum+curr.data;
            curr = curr.next;
        }
        return sum;*/
        int k = n;
        if (head == null || k < 0) return 0;
        ListNode ptr = head;
        int sum = 0;
        while (k > 0 && ptr != null) {
            sum = sum + ptr.data;
            ptr = ptr.next;
            k--;
        }
        if (ptr == null && k > 0) return 0;

        if (ptr == null) return sum;

        ListNode curr = head;
        while (ptr != null) {
            ptr = ptr.next;
            curr = curr.next;
        }
        sum = 0;
        while (curr != null) {
            sum = sum + curr.data;
            curr = curr.next;
        }
        return sum;

    }

    //merge sort of a linkedList
    public ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode curr = head;
        ListNode first = new ListNode(-1);
        ListNode second = new ListNode(-1);
        findMiddle(curr, first, second);
        ListNode A = mergeSort(first.next);
        ListNode B = mergeSort(second.next);
        head = merge(A, B);
        return head;
    }

    private ListNode merge(ListNode first, ListNode second) {
        if (first == null) return second;
        if (second == null) return first;
        if (first.data < second.data) {
            first.next = merge(first.next, second);
            return first;
        } else {
            second.next = merge(first, second.next);
            return second;
        }
    }

    private void findMiddle(ListNode curr, ListNode first, ListNode second) {
        ListNode slow = curr;
        ListNode fast = curr.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        first.next = curr;
        second.next = slow.next;
        slow.next = null;
    }

    public ListNode findMiddle(ListNode head) {
        if (head == null) return head;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode reOrderList(ListNode head) {
        if(head == null) return head;
        ListNode dummy = new ListNode(-1);
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode second = reverse(slow.next);
        ListNode first = head;
        slow.next = null;
        printNodes(first);
        printNodes(second);
        int count =1;
        ListNode tail = dummy;
        while (first!= null && second != null){
            if(count % 2 ==0){
                //even
                tail.next = new ListNode(second.data);
                second = second.next;
            }else{
                tail.next = new ListNode(first.data);
                first = first.next;
            }
            tail = tail.next;
            count++;
        }
        if(first != null) tail.next = first;
        if(second!= null)tail.next = second;
        printNodes(dummy.next);
        return dummy.next;
    }

    public ListNode union(ListNode node1, ListNode node2) {
        int[] a = new int[10000];
        if (node1 == null) return node2;
        if (node2 == null) return node1;
        ListNode curr = node1;
        ListNode dummy = new ListNode(-1);
        int end = 0;
        while (curr != null) {
            a[curr.data] = a[curr.data] + 1;
            end = Math.max(end, curr.data);
            curr = curr.next;
        }
        curr = node2;

        while (curr != null) {
            a[curr.data] = a[curr.data] + 1;
            end = Math.max(end, curr.data);
            curr = curr.next;
        }
        a[end + 1] = -1;
        ListNode tail = dummy;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > 0) {
                //create node
                tail.next = new ListNode(i);
                tail = tail.next;
            } else if (a[i] < 0) {
                break;
            }
        }
        return dummy.next;
    }

    void rearrangeEvenOdd(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        if (head == null) return;
        ListNode oddDummy = new ListNode(-1);
        ListNode tailOdd = oddDummy;
        ListNode evenDummy = new ListNode(-2);
        ListNode tailEven = evenDummy;
        ListNode curr = head;

        while (curr != null) {
            if (curr.data % 2 == 0) {
                //even
                tailEven.next = new ListNode(curr.data);
                tailEven = tailEven.next;
            } else {
                tailOdd.next = new ListNode(curr.data);
                tailOdd = tailOdd.next;
            }
            curr = curr.next;
        }
        //connect both nodes
        tailOdd.next = evenDummy.next;
        dummy.next = oddDummy.next;
        ListNode result = dummy.next;
        curr = head;
        while (curr != null) {
            curr.data = result.data;
            curr = curr.next;
            result = result.next;
        }

        printNodes(head);
    }

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        list.head = new ListNode(10);
        ListNode second = new ListNode(1);
        ListNode third = new ListNode(8);
        ListNode fourth = new ListNode(11);

        //connect
        list.head.next = second;
        second.next = third;
        third.next = fourth;

        list.printNodes();
        System.out.println(list.length());
        list.insertAtBegning(5);
        list.printNodes();

        list.add(15);
        list.printNodes();
        list.insertAt(16, 5);
        list.printNodes();
        list.deleteFirst();
        list.deleteLast();
        list.printNodes();

        // list.deleteAt(4);
        list.printNodes();
        System.out.println(list.getNthNodeFromEnd(2).data);

        System.out.println(list.getMiddleElement().data);
        list.reverse();

        SinglyLinkedList newList = new SinglyLinkedList();
        newList.add(1);
        newList.add(2);
        newList.add(3);
        newList.add(3);
        newList.add(2);
        newList.add(15);

        System.out.println(newList.isPalindrome());

        list = new SinglyLinkedList();
        list.add(96);
        list.add(90);
        list.add(12);
        list.printNodes();
        list.reverseBetween(1, 2);

        //testing reverse in grps
        list = new SinglyLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        list.add(11);
        list.reverseInGroups(4, true);

        //merging two sortedLinked list:
        SinglyLinkedList aList = new SinglyLinkedList();
        aList.add(1);
        aList.add(3);
        aList.add(5);
        aList.add(7);
        aList.add(9);

        SinglyLinkedList bList = new SinglyLinkedList();
        bList.add(2);
        bList.add(4);
        bList.add(6);
        // aList.mergeTwoSortedLists(aList.head,bList.head);
        aList.zipperList(aList.head, bList.head);

        SinglyLinkedList start1 = new SinglyLinkedList();
        start1.add(3);
        start1.add(4);
        start1.add(5);
        SinglyLinkedList start2 = new SinglyLinkedList();
        start2.add(4);
        start2.add(5);
        start1.printNodes(start1.addLists(start1.head, start2.head));

        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
        singlyLinkedList.add(2);
        singlyLinkedList.add(2);
        singlyLinkedList.add(2);
        singlyLinkedList.add(2);

        singlyLinkedList.removeDuplicates(singlyLinkedList.head);
        System.out.println();

        //pairSwap

        list = new SinglyLinkedList();
        list.add(4);
        list.add(4);
        list.add(15);
        list.pairWiseSwap(list.head);
        list = new SinglyLinkedList();
        list.add(3);
        list.add(5);
        list.add(2);
        list.add(1);
        list.add(4);
        list.add(1);
        System.out.println(list.sumOfLastNElements(list.head, 5));
        list.printNodes(list.mergeSort(list.head));
        System.out.println();
        list.printNodes();

        SinglyLinkedList list1 = new SinglyLinkedList();
        SinglyLinkedList list2 = new SinglyLinkedList();
        list1.add(9);
        list1.add(6);
        list1.add(4);
        list1.add(2);
        list1.add(3);
        list1.add(8);

        list2.add(1);
        list2.add(2);
        list2.add(8);
        list2.add(6);
        list2.add(2);
        list1.printNodes(list1.union(list1.head, list2.head));
        System.out.println("rearrange");
        list = new SinglyLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.rearrangeEvenOdd(list.head);


        list = new SinglyLinkedList();
        list.add(75);
        list.add(122);
        list.add(59);
        list.add(196);
        list.add(30);
        list.add(38);
        list.add(36);
        list.add(194);
       // list.add(5);
        //int result = list.findMiddle(list.head).data;
       // System.out.println(result);

        System.out.println("reorder");
        list.reOrderList(list.head);
    }
}
