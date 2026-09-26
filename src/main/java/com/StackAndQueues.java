package com;

import java.util.*;

public class StackAndQueues {

    private class MyStack {

        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();

        public void push(int x) {

            q2.offer(x);

            while (!q1.isEmpty()) {
                q2.offer(q1.poll());
            }

            Queue<Integer> temp = q1;
            q1 = q2;
            q2 = temp;
        }

        public int pop() {
            return q1.poll();
        }

        public int top() {
            return q1.peek();
        }

        public boolean empty() {
            return q1.isEmpty();
        }
    }

    class MyQueue {

        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        public void push(int x) {
            stack1.push(x);
        }

        public int pop() {

            while (stack1.size() > 1) {
                stack2.push(stack1.pop());
            }

            int ans = stack1.pop();

            while (stack2.size() > 0) {
                stack1.push(stack2.pop());
            }

            return ans;
        }

        public int peek() {

            while (stack1.size() > 1) {
                stack2.push(stack1.pop());
            }

            int ans = stack1.peek();

            while (stack2.size() > 0) {
                stack1.push(stack2.pop());
            }

            return ans;
        }

        public boolean empty() {
            return stack1.isEmpty();
        }
    }

    class MinStack {

        Stack<Integer> stack = new Stack<>();
        Stack<Integer> minStack = new Stack<>(); // minStack.peek() is the min of everything in stack

        public void push(int value) {
            stack.push(value);

            if (minStack.isEmpty() || value <= minStack.peek()) {
                minStack.push(value);
            } else {
                minStack.push(minStack.peek());
            }
        }

        public void pop() {
            stack.pop();
            minStack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    public int evalRPN(String[] tokens) {

        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            if (token.equals("+")) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a + b);
            } else if (token.equals("-")) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a - b);
            } else if (token.equals("*")) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a * b);
            } else if (token.equals("/")) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a / b);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }

    public int[] dailyTemperatures(int[] temperatures) {

        int[] result = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {

            for (int j = i + 1; j < temperatures.length; j++) {

                if (temperatures[j] > temperatures[i]) {
                    result[i] = j - i;
                    break;
                } else {
                    result[i] = 0;
                }
            }
        }

        return result;
    }

    public int[] finalPrices(int[] prices) {
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] <= prices[i]) {
                    prices[i] -= prices[j];
                    break;
                }
            }
        }
        return prices;
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && nums2[i] > stack.peek()) {
                map.put(stack.pop(), nums2[i]);
            }
            stack.push(nums2[i]);
        }

        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = map.getOrDefault(nums1[i], -1);
        }

        return nums1;
    }

    public int calPoints(String[] operations) {
        Stack<Integer> stack = new Stack<>();
        for (String op : operations) {

            switch (op) {
                case "+":
                    int top = stack.pop();
                    int newtop = top + stack.peek();
                    stack.push(top);
                    stack.push(newtop);
                    break;

                case "D":
                    stack.push(2 * stack.peek());
                    break;

                case "C":
                    stack.pop();
                    break;

                default:
                    stack.push(Integer.valueOf(op));
            }
        }

        return stack.stream().mapToInt(Integer::intValue).sum();
    }

    public int[] asteroidCollision(int[] asteroids) {

        Stack<Integer> Stack = new Stack<>();
        for (int ast : asteroids) {
            if (!Stack.isEmpty() && ast < 0) {
                Stack.push(Math.max(Stack.pop(), ast));
            } else if (!Stack.isEmpty() && ast == Stack.peek() * -1) {
                Stack.pop();
            }

            else {
                Stack.push(ast);
            }
        }

        return Stack.stream().mapToInt(Integer::intValue).toArray();
    }

    public String clearDigits(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && Character.isDigit(c)) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.reverse().toString();

    }
}
