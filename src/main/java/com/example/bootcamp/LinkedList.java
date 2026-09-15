package com.example.bootcamp;

public class LinkedList {

    private class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public Node removeDuplicates(Node head) {
        Node current = head;

        while (current != null && current.next != null) {

            if (current.data == current.next.data) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }

        return head;
    }

    public Node mergeTwoLists(Node list1, Node list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        Node mergedHead;

        if (list1.data <= list2.data) {
            mergedHead = list1;
            mergedHead.next = mergeTwoLists(list1.next, list2);
        } else {
            mergedHead = list2;
            mergedHead.next = mergeTwoLists(list1, list2.next);
        }

        return mergedHead;

    }

    public boolean hasCycle(Node head) {

        if (head == null || head.next == null) {
            return false;
        }

        Node slow = head;
        Node fast = head.next;

        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        return true;

    }

    public int cycleLength(Node head) {
        if (head == null || head.next == null) {
            return 0;
        }

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                int length = 1;
                Node current = slow.next;
                while (current != slow) {
                    length++;
                    current = current.next;
                }
                return length;
            }
        }

        return 0; // No cycle found
    }

    public Node detectCycle(Node head) {

        if (hasCycle(head)) {
            Node slow = head;
            Node fast = head;

            do {
                slow = slow.next;
                fast = fast.next.next;
            } while (slow != fast);

            slow = head;

            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }

            return slow; // Start of the cycle
        }
        return null; // No cycle found
    }

    public boolean isHappy(int n) {

        int slow = n;
        int fast = n;

        do {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        } while (slow != fast);

        return slow == 1;

    }

    private int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    public Node sortList(Node head) {

        Node node = head;
        if (head == null || head.next == null) {
            return head;
        }

        while (node.next != null) {
            if (node.data > node.next.data) {
                int temp = node.data;
                node.data = node.next.data;
                node.next.data = temp;
            }
            node = node.next;

        }
        return head;
    }

}
