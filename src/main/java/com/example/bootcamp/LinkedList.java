package com.example.bootcamp;

public class LinkedList {

    public static class ListNode {
        int data;
        ListNode next;

        ListNode(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public ListNode createLinkedList(int[] values) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        for (int value : values) {
            current.next = new ListNode(value);
            current = current.next;
        }

        return dummy.next;
    }

    public void printList(ListNode head) {
        StringBuilder sb = new StringBuilder();
        ListNode current = head;

        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }

        System.out.println(sb);
    }

    public ListNode removeDuplicates(ListNode head) {
        ListNode current = head;

        while (current != null && current.next != null) {

            if (current.data == current.next.data) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }

        return head;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        ListNode mergedHead;

        if (list1.data <= list2.data) {
            mergedHead = list1;
            mergedHead.next = mergeTwoLists(list1.next, list2);
        } else {
            mergedHead = list2;
            mergedHead.next = mergeTwoLists(list1, list2.next);
        }

        return mergedHead;

    }

    public boolean hasCycle(ListNode head) {

        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head.next;

        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        return true;

    }

    public int cycleLength(ListNode head) {
        if (head == null || head.next == null) {
            return 0;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                int length = 1;
                ListNode current = slow.next;
                while (current != slow) {
                    length++;
                    current = current.next;
                }
                return length;
            }
        }

        return 0; // No cycle found
    }

    public ListNode detectCycle(ListNode head) {

        if (hasCycle(head)) {
            ListNode slow = head;
            ListNode fast = head;

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

    public ListNode sortList(ListNode head) {

        ListNode node = head;
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

    public ListNode reverseList(ListNode head) {

        ListNode prev = null;
        ListNode current = head;

        while (current != null) {
            ListNode nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;

        }

        return prev;
    }

    public void reorderList(ListNode head) {

        if (head == null || head.next == null) {
            return;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Cut the list in two, so the halves share no node
        ListNode second = slow.next;
        slow.next = null;

        ListNode secondHalf = reverseList(second);

        ListNode firstHalf = head;

        while (secondHalf != null) {
            ListNode temp1 = firstHalf.next;
            ListNode temp2 = secondHalf.next;

            firstHalf.next = secondHalf;
            secondHalf.next = temp1;

            firstHalf = temp1;
            secondHalf = temp2;
        }

    }

    public ListNode removeNthFromEnd(ListNode head, int n) {

        if (head == null || head.next == null) {
            return null;
        }

        int count = 1;
        ListNode last = head;
        while (last.next != null) {
            last = last.next;
            count++;
        }

        int target = count - n;

        if (target == 0) {
            return head.next;
        }

        ListNode temp = head;

        while (target > 1) {
            temp = temp.next;
            target--;

        }

        temp.next = temp.next.next;

        return head;

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode ans = new ListNode(0);
        ListNode current = ans;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;

            if (l1 != null) {
                sum += l1.data;
                l1 = l1.next;
            }

            if (l2 != null) {
                sum += l2.data;
                l2 = l2.next;
            }

            carry = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;
        }

        return ans.next;

    }

}
