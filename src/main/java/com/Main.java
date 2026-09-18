package com;

import java.util.Arrays;

import com.example.bootcamp.LinkedList;
import com.example.bootcamp.Strings;

public class Main {

    public static void main(String[] args) {
        // StackAndQueues sq = new StackAndQueues();

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

        // LinkedList list = new LinkedList();
        // LinkedList.ListNode head = list.createLinkedList(new int[] { 1, 2, 3, 2, 1
        // });

        // LinkedList.ListNode l1 = list.createLinkedList(new int[] { 2, 4, 3 }); // 342
        // LinkedList.ListNode l2 = list.createLinkedList(new int[] { 5, 6, 4 }); // 465

        // list.printList(list.addTwoNumbers(l1, l2)); // 7 -> 0 -> 8 (807)

        // list.printList(head);
        // list.reorderList(head);
        // list.printList(head);

        // list.removeNthFromEnd(head, 2);
        // list.printList(head);

        // A: 4 -> 1 -> 8 -> 4 -> 5, B: 5 -> 6 -> 1 -> 8 -> 4 -> 5 (shared tail starts
        // at 8)
        // LinkedList.ListNode common = list.createLinkedList(new int[] { 8, 4, 5 });
        // LinkedList.ListNode headA = list.createLinkedList(new int[] { 4, 1 });
        // LinkedList.ListNode headB = list.createLinkedList(new int[] { 5, 6, 1 });
        // headA.next.next = common;
        // headB.next.next.next = common;

        // LinkedList.ListNode intersection = list.getIntersectionNode(headA, headB);
        // System.out.println("Intersection Node: " + (intersection == null ? "none" :
        // intersection.data)); // 8

        // LinkedList.ListNode noIntersection = list.getIntersectionNode(l1, l2);
        // System.out.println("Intersection Node: " + (noIntersection == null ? "none" :
        // noIntersection.data)); // none

        // System.out.println("Is Palindrome: " + list.isPalindrome(head));

        // --------------------------------Strings------------------------------------------------------//
        Strings strings = new Strings();
        System.out.println("Length of longest substring: " + strings.lengthOfLongestSubstring("pwwkew"));

    }
}
