package com;

import java.util.Arrays;
import java.util.Scanner;

import com.example.bootcamp.Array;
import com.example.bootcamp.LinkedList;
import com.example.bootcamp.SlidingWindow;
import com.example.bootcamp.StriverSheet;
import com.example.bootcamp.Strings;
import com.example.bootcamp.Trees;
import com.example.bootcamp.TwoPointer;

public class Main {

    public static void main(String[] args) {
        // Uncomment the topic you want to run
        // runArray();
        // runStackAndQueues();
        // runLinkedList();
        runStrings();
        // runTwoPointer();
        // runSlidingWindow();
        // runStriverSheet();
        // runTrees();
    }

    // --------------------------------Array------------------------------------------------------//
    static void runArray() {
        Array array = new Array();

        System.out.println("Search Range: " +
                Arrays.toString(array.searchRange(new int[] { 5, 7, 7, 8, 8, 10 }, 8))); // [3, 4]
    }

    // --------------------------------Stack and
    // Queues------------------------------------------//
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

    // --------------------------------Linked
    // List-----------------------------------------------//
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

        // A: 4 -> 1 -> 8 -> 4 -> 5, B: 5 -> 6 -> 1 -> 8 -> 4 -> 5 (shared tail starts
        // at 8)
        LinkedList.ListNode common = list.createLinkedList(new int[] { 8, 4, 5 });
        LinkedList.ListNode headA = list.createLinkedList(new int[] { 4, 1 });
        LinkedList.ListNode headB = list.createLinkedList(new int[] { 5, 6, 1 });
        headA.next.next = common;
        headB.next.next.next = common;

        LinkedList.ListNode intersection = list.getIntersectionNode(headA, headB);
        System.out.println("Intersection Node: " + (intersection == null ? "none" : intersection.val)); // 8

        LinkedList.ListNode noIntersection = list.getIntersectionNode(l1, l2);
        System.out.println("Intersection Node: " + (noIntersection == null ? "none" : noIntersection.val)); // none

        LinkedList.BrowserHistory history = new LinkedList.BrowserHistory("leetcode.com");
        history.visit("google.com");
        history.visit("facebook.com");
        history.visit("youtube.com");
        System.out.println("Back 1: " + history.back(1)); // facebook.com
        System.out.println("Back 1: " + history.back(1)); // google.com
        System.out.println("Forward 1: " + history.forward(1)); // facebook.com
        history.visit("linkedin.com"); // clears forward history (youtube.com)
        System.out.println("Forward 2: " + history.forward(2)); // linkedin.com
        System.out.println("Back 2: " + history.back(2)); // google.com
        System.out.println("Back 7: " + history.back(7)); // leetcode.com
    }

    // --------------------------------Strings---------------------------------------------------//
    static void runStrings() {
        Strings strings = new Strings();

        System.out.println("Length of longest substring: " + strings.lengthOfLongestSubstring("pwwkew")); // 3
        System.out.println("Longest palindrome: " + strings.longestPalindrome("babad"));
        System.out.println("Longest consecutive: " +
                strings.longestConsecutive(new int[] { 2, 20, 4, 10, 3, 4, 5 })); // 4

        System.out.println("Pangram: " + strings.checkIfPangram("thequickbrownfoxjumpsoverthelazydog")); // true
        System.out.println("Pangram: " + strings.checkIfPangram("leetcode")); // false
        System.out.println("Pangram: " + strings.checkIfPangram("abcdefghijklmnopqrstuvwxyz")); // true
        System.out.println("Pangram: " + strings.checkIfPangram("abcdefghijklmnopqrstuvwxy")); // false (no z)
        System.out.println("Pangram: " + strings.checkIfPangram("a")); // false
    }

    // --------------------------------Two
    // Pointer-----------------------------------------------//
    static void runTwoPointer() {
        TwoPointer twoPointer = new TwoPointer();

        System.out.println("Is Palindrome: " + twoPointer.isPalindrome("A man, a plan, a canal: Panama")); // true
        System.out.println("Reverse Str: " + twoPointer.reverseStr("abcdefg", 2)); // bacdfeg
    }

    // --------------------------------Sliding
    // Window--------------------------------------------//
    static void runSlidingWindow() {
        SlidingWindow slidingWindow = new SlidingWindow();

        System.out.println("Char Replacement: " + slidingWindow.characterReplacement("ABAB", 2)); // 4
        System.out.println("Char Replacement: " + slidingWindow.characterReplacement("AABABBA", 1)); // 4
        System.out.println("Char Replacement: " + slidingWindow.characterReplacement("AAAA", 0)); // 4
        System.out.println("Char Replacement: " + slidingWindow.characterReplacement("ABCDE", 1)); // 2
        System.out.println("Char Replacement: " + slidingWindow.characterReplacement("BAAA", 0)); // 3
        System.out.println("Char Replacement: " + slidingWindow.characterReplacement("AAAB", 1)); // 4

    }

    // --------------------------------Striver
    // Sheet---------------------------------------------//
    static void runStriverSheet() {
        StriverSheet striver = new StriverSheet();

        // Arrays
        // System.out.println("2Sum: " + Arrays.toString(striver.twoSum(new int[] { 2,
        // 6, 5, 8, 11 }, 14))); // [1, 3]
        // System.out.println("2Sum: " + Arrays.toString(striver.twoSum(new int[] { 2,
        // 6, 5, 8, 11 }, 14))); // [1, 3]
        // System.out.println("2Sum: " + Arrays.toString(striver.twoSum(new int[] { 2,
        // 6, 5, 8, 11 }, 15))); // [-1, -1]
        // System.out.println("Second Largest: " + striver.secondLargest(new int[] { 12,
        // 35, 1, 10, 34, 1 })); // 34
        // System.out.println("Second Largest: " + striver.secondLargest(new int[] { 10,
        // 10, 10 })); // -1

        // Sorted & Rotated: { input, expected (1 = true, 0 = false) }
        int[][][] cases = {
                { { 3, 4, 5, 1, 2 }, { 1 } }, // normal rotation
                { { 2, 1, 3, 4 }, { 0 } }, // not a rotation
                { { 1, 2, 3 }, { 1 } }, // already sorted (0 rotation)
                { { 1 }, { 1 } }, // single element
                { { 2, 1 }, { 1 } }, // two elements, drop at index 0
                { { 5, 1, 2, 3, 4 }, { 1 } }, // drop right at the start
                { { 3, 4, 5, 1 }, { 1 } }, // drop right at the end
                { { 1, 1, 1 }, { 1 } }, // all duplicates
                { { 2, 2, 1, 2 }, { 1 } }, // duplicates around the drop
                { { 6, 10, 6 }, { 1 } }, // first == last
                { { 5, 6, 1, 2 }, { 1 } }, // first != last + 1
                { { 2, 3, 1, 4 }, { 0 } }, // one drop but last > first
                { { 1, 3, 2 }, { 0 } }, // one drop, wraps wrong
                { { 3, 1, 2, 1 }, { 0 } }, // two drops
                { { 1, 2, 3, 1, 2, 3 }, { 0 } }, // repeated sorted block
        };
        for (int[][] c : cases) {
            boolean expected = c[1][0] == 1;
            boolean got = StriverSheet.checkSortedRotated(c[0]);
            System.out.println((got == expected ? "PASS " : "FAIL ") + Arrays.toString(c[0])
                    + " -> got " + got + ", expected " + expected);
        }
    }

    // --------------------------------Trees------------------------------------------------------//
    static void runTrees() {
        Trees tree = new Trees();
        Scanner sc = new Scanner(System.in);

        // tree.insert(sc);
        // tree.display();
        sc.close();

        Trees.BST bst = new Trees.BST();
        for (int x : new int[] { 50, 30, 70, 20, 40, 60, 80, 30 }) // last 30 is a duplicate
            bst.insert(x);
        bst.display();
        System.out.println("Height: " + bst.height()); // 3
    }
}
