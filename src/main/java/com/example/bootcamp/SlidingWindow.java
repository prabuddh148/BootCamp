package com.example.bootcamp;

import java.util.HashMap;
import java.util.Map;

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

    // Q2. Count Occurrences of Anagrams (GFG)
    // Count how many substrings of txt are anagrams of pat.
    // Ex: txt = "forxxorfxdofr", pat = "for" -> 3, txt = "aabaabaa", pat = "aaba"
    // -> 4
    // Approach: fixed window of size pat.length(). Keep counts of pat's chars
    // and how many distinct chars are still unmatched (distinct). Entering char
    // decrements its count, leaving char increments it. distinct == 0 -> anagram.
    // O(n) time, O(1) space (at most 26 keys).
    public int search(String pat, String txt) {
        int k = pat.length();

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < k; i++) {
            map.put(pat.charAt(i), map.getOrDefault(pat.charAt(i), 0) + 1);
        }

        int distinct = map.size();
        int left = 0;
        int count = 0;

        for (int right = 0; right < txt.length(); right++) {
            char c = txt.charAt(right);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0) {
                    distinct--;
                }
            }

            if (right - left + 1 == k) {
                if (distinct == 0) {
                    count++;
                }

                char out = txt.charAt(left);
                if (map.containsKey(out)) {
                    map.put(out, map.get(out) + 1);
                    if (map.get(out) == 1) {
                        distinct++;
                    }
                }
                left++;
            }
        }

        return count;
    }

}
