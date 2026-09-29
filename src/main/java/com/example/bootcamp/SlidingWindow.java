package com.example.bootcamp;

public class SlidingWindow {

    // Q1. Longest Repeating Character Replacement (LeetCode 424)
    // Change at most k chars (uppercase A-Z) to get the longest substring of
    // one repeated letter. Return its length.
    // Ex: "ABAB", k = 2 -> 4, "AABABBA", k = 1 -> 4
    // Approach: window is valid if (window size - most frequent letter) <= k.
    // Grow right, shrink left while invalid. O(n) time, O(1) space.
    public int characterReplacement(String s, int k) {
        int[] freq = new int[26];
        int length = 0;
        int left = 0;
        int maxFreq = 0;

        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'A']++;
            maxFreq = Math.max(maxFreq, freq[s.charAt(i) - 'A']);

            // chars that need changing = window size - most frequent letter
            while ((i - left + 1) - maxFreq > k) {
                freq[s.charAt(left) - 'A']--;
                left++;
            }

            length = Math.max(length, i - left + 1);
        }

        return length;
    }

}
