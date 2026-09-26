package com;

import java.util.Arrays;

import com.example.bootcamp.Array;
import com.example.bootcamp.LinkedList;
import com.example.bootcamp.StriverSheet;
import com.example.bootcamp.Strings;
import com.example.bootcamp.TwoPointer;

public class Main {

    public static void main(String[] args) {
        // Uncomment the topic you want to run
        // runArray();
        // runStackAndQueues();
        // runLinkedList();
        // runStrings();
        // runTwoPointer();
        runStriverSheet();
    }

    // --------------------------------Array------------------------------------------------------//
    static void runArray() {
        Array array = new Array();

        System.out.println("Search Range: " +
                Arrays.toString(array.searchRange(new int[] { 5, 7, 7, 8, 8, 10 }, 8))); // [3, 4]
    }

    // --------------------------------Stack and Queues------------------------------------------//
    static void runStackAndQueues() {
        StackAndQueues sq = new StackAndQueues();

        StackAndQueues.MinStack stack = sq.new MinStack();
        stack.push(-2);
        stack.push(0);
        stack.push(-1);
        System.out.println("Get min: " + stack.getMin()); // -2
        stack.pop();
        System.out.println("Top: " + stack.top()); // 0
        System.out.println("Get min: " + stack.getMin()); // -2

        String[] tokens = { "2", "1", "+", "3", "*" };
        System.out.println("evalRPN: " + sq.evalRPN(tokens)); // 9

        String[] tokens2 = { "10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+" };
        System.out.println("evalRPN: " + sq.evalRPN(tokens2)); // 22

        int[] temperatures = { 73, 74, 75, 71, 69, 72, 76, 73 };
        System.out.println("Daily Temperatures: " + Arrays.toString(sq.dailyTemperatures(temperatures)));

        int[] prices = { 8, 4, 6, 2, 3 };
        System.out.println("Final Prices: " + Arrays.toString(sq.finalPrices(prices)));

        System.out.println("Next Greater Elements: " +
                Arrays.toString(sq.nextGreaterElement(new int[] { 4, 1, 2 }, new int[] { 1, 3, 4, 2 })));

        System.out.println("Calculated Points: " + sq.calPoints(new String[] { "5", "2", "C", "D", "+" }));

        int[] asteroids = { 5, 10, -5 };
        System.out.println("Asteroid Collision: " + Arrays.toString(sq.asteroidCollision(asteroids)));

        System.out.println("Clear Digits: " + sq.clearDigits("cb34")); // ""
    }

    // --------------------------------Linked List-----------------------------------------------//
    static void runLinkedList() {
        LinkedList list = new LinkedList();

        LinkedList.ListNode l1 = list.createLinkedList(new int[] { 2, 4, 3 }); // 342
        LinkedList.ListNode l2 = list.createLinkedList(new int[] { 5, 6, 4 }); // 465
        list.printList(list.addTwoNumbers(l1, l2)); // 7 -> 0 -> 8 (807)

        LinkedList.ListNode head = list.createLinkedList(new int[] { 1, 2, 3, 2, 1 });
        System.out.println("Is Palindrome: " + list.isPalindrome(head));

        head = list.createLinkedList(new int[] { 1, 2, 3, 4, 5 });
        list.reorderList(head);
        list.printList(head); // 1 -> 5 -> 2 -> 4 -> 3

        head = list.createLinkedList(new int[] { 1, 2, 3, 4, 5 });
        list.printList(list.removeNthFromEnd(head, 2)); // 1 -> 2 -> 3 -> 5

        // A: 4 -> 1 -> 8 -> 4 -> 5, B: 5 -> 6 -> 1 -> 8 -> 4 -> 5 (shared tail starts at 8)
        LinkedList.ListNode common = list.createLinkedList(new int[] { 8, 4, 5 });
        LinkedList.ListNode headA = list.createLinkedList(new int[] { 4, 1 });
        LinkedList.ListNode headB = list.createLinkedList(new int[] { 5, 6, 1 });
        headA.next.next = common;
        headB.next.next.next = common;

        LinkedList.ListNode intersection = list.getIntersectionNode(headA, headB);
        System.out.println("Intersection Node: " + (intersection == null ? "none" : intersection.data)); // 8

        LinkedList.ListNode noIntersection = list.getIntersectionNode(l1, l2);
        System.out.println("Intersection Node: " + (noIntersection == null ? "none" : noIntersection.data)); // none
    }

    // --------------------------------Strings---------------------------------------------------//
    static void runStrings() {
        Strings strings = new Strings();

        System.out.println("Length of longest substring: " + strings.lengthOfLongestSubstring("pwwkew")); // 3
        System.out.println("Longest palindrome: " + strings.longestPalindrome("babad"));
        System.out.println("Longest consecutive: " +
                strings.longestConsecutive(new int[] { 2, 20, 4, 10, 3, 4, 5 })); // 4
    }

    // --------------------------------Two Pointer-----------------------------------------------//
    static void runTwoPointer() {
        TwoPointer twoPointer = new TwoPointer();

        System.out.println("Is Palindrome: " + twoPointer.isPalindrome("A man, a plan, a canal: Panama")); // true
        System.out.println("Reverse Str: " + twoPointer.reverseStr("abcdefg", 2)); // bacdfeg
    }

    // --------------------------------Striver Sheet---------------------------------------------//
    static void runStriverSheet() {
        StriverSheet striver = new StriverSheet();

        // Arrays: Medium
        System.out.println("2Sum: " + Arrays.toString(striver.twoSum(new int[] { 2, 6, 5, 8, 11 }, 14))); // [1, 3]
        System.out.println("2Sum: " + Arrays.toString(striver.twoSum(new int[] { 2, 6, 5, 8, 11 }, 15))); // [-1, -1]
    }
}
