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

}
