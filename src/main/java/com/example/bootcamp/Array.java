package com.example.bootcamp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Array {
    public int[] twoSum(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] { i, j };
                }
            }
        }
        return new int[] { -1, -1 };

    }

    public int maxArea(int[] heights) {
        int left = 0;
        int right = heights.length - 1;
        int small = 0;
        int prod = 0;
        while (left < right) {
            if (heights[left] < heights[right]) {
                small = heights[left];

            } else {
                small = heights[right];
            }
            int newProd = small * (right - left);
            if (prod < newProd) {
                prod = newProd;
            }
            if (heights[left] < heights[right])
                left++;
            else
                right--;

        }

        return prod;

    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int l = i + 1;
            int r = nums.length - 1;
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    list.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    l++;
                    r--;
                    while (l < r && nums[l] == nums[l - 1]) {
                        l++;
                    }

                    while (l < r && nums[r] == nums[r + 1]) {
                        r--;
                    }
                } else if (sum < 0) {
                    l++;
                } else if (sum > 0) {
                    r--;

                }
            }

        }
        return list;

    }

    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    public int removeElement(int[] nums, int val) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (val == nums[i]) {
                continue;
            } else {
                nums[k] = nums[i];
                k++;
            }

        }
        return k;

    }

    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            // Target found
            if (nums[mid] == target) {
                return mid;
            }

            // Left half is sorted
            if (nums[start] <= nums[mid]) {

                // Target lies in the left half
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }

            }
            // Right half is sorted
            else {

                // Target lies in the right half
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }

            }
        }

        return -1;
    }

    public int[] searchRange(int[] nums, int target) {
        return new int[] { findBound(nums, target, true), findBound(nums, target, false) };
    }

    // Binary search for the first (or last) index of target, -1 if absent
    private int findBound(int[] nums, int target, boolean first) {
        int start = 0;
        int end = nums.length - 1;
        int index = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) {
                index = mid;
                // Keep searching left for first, right for last
                if (first) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return index;
    }
}
