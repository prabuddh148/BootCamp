package com.example.bootcamp;

import java.util.ArrayList;

public class Recursion {

    public static void main(String[] args) {
        printPattern(5);
    }

    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factorial(n - 1);

    }

    public static void printRange(int n) {

        if (n == 0) {
            return;
        }

        System.out.println(n);
        printRange(n - 1);
    }

    public static void printRangeRev(int n) {

        if (n == 0) {
            return;
        }

        printRangeRev(n - 1);
        System.out.println(n);
    }

    public static int sumRange(int n) {

        if (n == 0) {
            return 0;
        }

        return n + sumRange(n - 1);
    }

    public static int productRange(int n) {

        if (n == 0) {
            return 1;
        }

        return n * productRange(n - 1);
    }

    public static int numberReverse(int n) {

        if (n == 0) {
            return 0;
        }

        int rem = n % 10;
        System.out.print(rem);

        return numberReverse(n / 10);
    }

    public static int countZeros(int n) {

        if (n == 0) {
            return 0;
        }

        int rem = n % 10;
        if (rem == 0) {
            return 1 + countZeros(n / 10);
        }
        return countZeros(n / 10);

    }

    public static boolean isSortedArray(int[] arr, int index) {

        if (index == arr.length - 1) {
            return true;
        }

        if (arr[index] > arr[index + 1]) {
            return false;
        }
        return isSortedArray(arr, index + 1);
    }

    public static int linearSearch(int[] arr, int target, int index) {

        if (index == arr.length) {
            return -1;
        }

        if (arr[index] == target) {
            return index;
        }

        return linearSearch(arr, target, index + 1);
    }

    public static ArrayList<Integer> findAllIndices(int[] arr, int target, int index, ArrayList<Integer> list) {

        if (index == arr.length) {
            return list;
        }

        if (arr[index] == target) {
            list.add(index);
        }

        return findAllIndices(arr, target, index + 1, list);
    }

    public static ArrayList<Integer> findAllIndicesWithoutParamsArrayList(int[] arr, int target, int index) {
        ArrayList<Integer> list = new ArrayList<>();

        if (index == arr.length) {
            return list;
        }

        if (arr[index] == target) {
            list.add(index);
        }

        list.addAll(findAllIndicesWithoutParamsArrayList(arr, target, index + 1));
        return list;
    }

    public static void printPattern(int n) {

        /*
         * Pattern:
         * *****
         * ****
         * ***
         * **
         * *
         */ if (n == 0) {
            return;
        }

        for (int i = 0; i < n; i++) {
            System.out.print("*");
        }
        System.out.println();
        printPattern(n - 1);
    }

}