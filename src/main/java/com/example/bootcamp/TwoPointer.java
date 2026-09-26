package com.example.bootcamp;

public class TwoPointer {

    public boolean isPalindrome(String s) {

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }

            left++;
            right--;
        }

        return true;

    }

    public String reverseStr(String s, int k) {

        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i += 2 * k) {
            int left = i;
            int right = Math.min(i + k - 1, chars.length - 1);

            while (left < right) {
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                left++;
                right--;
            }
        }

        return new String(chars);
    }

    // public String reverseWords(String s) {
    // String[] words = s.trim().split("\\s+");
    // StringBuilder reversed = new StringBuilder();
    // for (int i = words.length - 1; i >= 0; i--) {
    // reversed.append(words[i]);
    // if (i != 0) {
    // reversed.append(" ");
    // }
    // }
    // return reversed.toString();
    // }

}
