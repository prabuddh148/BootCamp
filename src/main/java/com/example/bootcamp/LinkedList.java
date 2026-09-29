package com.example.bootcamp;

import java.util.List;

public class LinkedList {

    public static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int val) {
            this.val = val;
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
            sb.append(current.val);
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

            if (current.val == current.next.val) {
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

        if (list1.val <= list2.val) {
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
            if (node.val > node.next.val) {
                int temp = node.val;
                node.val = node.next.val;
                node.next.val = temp;
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
                sum += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            carry = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;
        }

        return ans.next;

    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        if (headA == null || headB == null) {
            return null;

        }

        ListNode a = headA;
        ListNode b = headB;

        while (a != b) {
            a = (a == null) ? headB : a.next;
            b = (b == null) ? headA : b.next;
        }
        return a;
    }

    public ListNode removeElements(ListNode head, int val) {

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;

        while (current.next != null) {
            if (current.next.val == val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }

        return dummy.next;
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        // Build a reversed copy with new nodes, so the original list stays intact
        ListNode reversed = null;
        for (ListNode node = head; node != null; node = node.next) {
            ListNode copy = new ListNode(node.val);
            copy.next = reversed;
            reversed = copy;
        }

        ListNode temp = head;
        ListNode curr = reversed;

        while (curr != null) {
            System.out.println("Temp: " + temp.val);
            System.out.println("Curr: " + curr.val);
            if (temp.val != curr.val) {
                return false;
            }
            temp = temp.next;
            curr = curr.next;
        }

        return true;
    }

    public void deleteNode(ListNode node) {
        if (node == null || node.next == null) {
            return;
        }
        node.val = node.next.val;
        node.next = node.next.next;

    }

    public static class BrowserHistory {

        private static class Node {
            String url;
            Node prev, next;

            public Node(String url) {
                this.url = url;
            }

        }

        public Node curr;

        public BrowserHistory(String homepage) {
            curr = new Node(homepage);

        }

        public void visit(String url) {
            Node newNode = new Node(url);
            curr.next = newNode;
            newNode.prev = curr;
            curr = newNode;

        }

        public String back(int steps) {
            while (steps > 0 && curr.prev != null) {
                curr = curr.prev;
                steps--;
            }
            return curr.url;

        }

        public String forward(int steps) {
            while (steps > 0 && curr.next != null) {
                curr = curr.next;
                steps--;
            }
            return curr.url;
        }

    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode first = prev.next;
            ListNode second = prev.next.next;

            first.next = second.next;
            second.next = first;
            prev.next = second;

            prev = first;
        }

        return dummy.next;

    }

    public ListNode rotateRight(ListNode head, int k) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode oldTail = head;
        int length = 1;
        while (oldTail.next != null) {
            oldTail = oldTail.next;
            length++;
        }
        k = k % length;
        if (k == 0) {
            return head;
        }
        ListNode newTail = head;
        for (int i = 0; i < length - k - 1; i++) {
            newTail = newTail.next;
        }
        ListNode newHead = newTail.next;
        newTail.next = null;
        oldTail.next = head;
        return newHead;

    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;

        while (current.next != null && current.next.next != null) {
            if (current.next.val == current.next.next.val) {
                int dup = current.next.val;
                while (current.next != null && current.next.val == dup) {
                    current.next = current.next.next; // skip every node with this value
                }
            } else {
                current = current.next;
            }
        }
        return dummy.next;
    }

}
