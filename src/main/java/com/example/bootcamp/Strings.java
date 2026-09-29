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

    public boolean checkIfPangram(String sentence) {
        Set<Character> set = new HashSet<>();

        for (char c : sentence.toCharArray()) {
            set.add(c);
        }

        return set.size() == 26;

    }

    public int lengthOfLastWord(String s) {

        String[] words = s.trim().split(" ");
        return words[words.length - 1].length();

    }

    public String convertToTitle(int columnNumber) {

        StringBuilder sb = new StringBuilder();

        while (columnNumber > 0) {
            columnNumber--;
            int remainder = columnNumber % 26;
            sb.append((char) (remainder + 'A'));
            columnNumber /= 26;
        }

        return sb.reverse().toString();

    }

    public int titleToNumber(String columnTitle) {

        int result = 0;

        for (char c : columnTitle.toCharArray()) {
            int value = c - 'A' + 1; // A=1, B=2, ..., Z=26
            result = result * 26 + value;
        }

        return result;

    }

}
