package com.example.bootcamp;

import java.util.*;

public class StriverSheet {

    // ==================== ARRAYS ====================

    // Q1. 2Sum
    // Return indices of two numbers adding up to target, else [-1, -1].
    // Ex: [2, 6, 5, 8, 11], target 14 -> [1, 3]
    // Approach: HashMap of value -> index. O(n) time, O(n) space.
    public int[] twoSum(int[] arr, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int comp = target - arr[i];
            if (map.containsKey(comp)) {
                return new int[] { map.get(comp), i };
            }
            map.put(arr[i], i);
        }
        return new int[] { -1, -1 };
    }

    // Q2. Second Largest Element
    // Return the second largest distinct element, or -1 if none.
    // Ex: [12, 35, 1, 10, 34, 1] -> 34
    // Approach: one pass tracking large & secondLarge. O(n) time, O(1) space.
    public int secondLargest(int[] arr) {
        int large = Integer.MIN_VALUE;
        int secondLarge = Integer.MIN_VALUE;

        System.out.println("large: " + large);
        System.out.println("secondLarge: " + secondLarge);

        for (int num : arr) {
            if (num > large) {
                secondLarge = large;
                large = num;
            } else if (num > secondLarge && num < large) {
                secondLarge = num;
            }
        }

        return (secondLarge == Integer.MIN_VALUE) ? -1 : secondLarge;

    }

    // Q3. Check if Array Is Sorted and Rotated
    // Return true if nums is a non-decreasing array rotated by some amount
    // (including zero). Duplicates allowed.
    // Ex: [3, 4, 5, 1, 2] -> true, [2, 1, 3, 4] -> false
    // Approach: count drops (nums[i] > nums[i + 1]), treating the array as
    // circular (last -> first). At most one drop means true. O(n) time, O(1) space.
    public static boolean checkSortedRotated(int[] nums) {
        int mid = checkPoint(nums);

        if (mid != -1) {
            boolean res2 = f2(nums, mid);
            int last = nums[nums.length - 1];
            int start = nums[0];
            if (res2 && last <= start) {
                return true;
            } else
                return false;

        }

        return true;
    }

    public static int checkPoint(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return i;
            }

        }
        return -1;
    }

    public static boolean f2(int[] nums, int mid) {
        boolean op = true;

        for (int k = mid + 1; k < nums.length - 1; k++) {
            if (nums[k] > nums[k + 1])
                return op = false;

        }
        return op;

    }

    // ==================== BINARY SEARCH ====================

    // ==================== STRINGS ====================

    // ==================== LINKED LIST ====================

    // ==================== RECURSION ====================

    // ==================== STACK & QUEUES ====================

}
