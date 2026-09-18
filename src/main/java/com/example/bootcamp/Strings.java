package com.example.bootcamp;

public class Strings {

    public int lengthOfLongestSubstring(String s) {

        System.out.println("Input string length: " + s.length());

        for (int i = 0; i < s.length(); i++) {
            int[] charCount = new int[1];
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    return j - i;
                }

            }

        }
        return 0;
    }
}
