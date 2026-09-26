package com.example.bootcamp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Strings {

    public int lengthOfLongestSubstring(String s) {

        int maxlength = 0;

        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (!set.contains(s.charAt(i))) {
                set.add(s.charAt(i));
                maxlength = Math.max(maxlength, set.size());
            } else {
                set.clear();
                set.add(s.charAt(i));
            }
        }

        return maxlength;
    }

    public String longestPalindrome(String s) {
        for (int length = s.length(); length > 0; length--) {
            for (int start = 0; start <= s.length() - length; start++) {
                if (check(start, start + length, s)) {
                    return s.substring(start, start + length);
                }
            }
        }

        return "";
    }

    private boolean check(int i, int j, String s) {
        int left = i;
        int right = j - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);

        int max = 1;
        int current = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                continue; // duplicate, the streak neither grows nor breaks
            }

            if (nums[i] == nums[i - 1] + 1) {
                current++;
            } else {
                current = 1; // chain broke, start counting a new run
            }

            max = Math.max(max, current);
        }

        return max;
    }

}
