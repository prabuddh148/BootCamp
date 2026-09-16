package com;

import java.util.Arrays;

import com.example.bootcamp.LinkedList;

public class Main {

    public static void main(String[] args) {
        StackAndQueues sq = new StackAndQueues();

        // StackAndQueues.MinStack stack = sq.new MinStack();
        // stack.push(-2);
        // stack.push(0);
        // stack.push(-1);

        // System.out.println("Get min: " + stack.getMin()); // -2

        // stack.pop();

        // System.out.println("Top: " + stack.top()); // 0
        // System.out.println("Get min: " + stack.getMin()); // -2

        // String[] tokens = { "2", "1", "+", "3", "*" };
        // System.out.println("evalRPN: " + sq.evalRPN(tokens)); // 9

        // String[] tokens2 = { "10", "6", "9", "3", "+", "-11", "*", "/", "*", "17",
        // "+", "5", "+" };
        // System.out.println("evalRPN: " + sq.evalRPN(tokens2)); // 22

        // int[] temperatures = { 73, 74, 75, 71, 69, 72, 76, 73 };
        // System.out.println("Daily Temperatures: " +
        // Arrays.toString(sq.dailyTemperatures(temperatures)));

        // -------------------------------LL------------------------------------------------------//

        LinkedList list = new LinkedList();
        LinkedList.ListNode head = list.createLinkedList(new int[] { 1, 2, 3, 4, 5
        });

        LinkedList.ListNode l1 = list.createLinkedList(new int[] { 2, 4, 3 }); // 342
        LinkedList.ListNode l2 = list.createLinkedList(new int[] { 5, 6, 4 }); // 465

        list.printList(list.addTwoNumbers(l1, l2)); // 7 -> 0 -> 8 (807)

        // list.printList(head);
        // list.reorderList(head);
        // list.printList(head);

        // list.removeNthFromEnd(head, 2);
        // list.printList(head);

    }
}
