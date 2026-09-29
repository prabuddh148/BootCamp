package com.example.bootcamp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Trees {

    private static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
            left = right = null;
        }

    }

    private Node root;

    public void insert(Scanner sc) {

        System.out.print("Enter Root node: ");
        int data = sc.nextInt();
        root = new Node(data);
        insert(sc, root);

    }

    private void insert(Scanner sc, Node node) {
        System.out.println("You want to insert left or right of " + node.data + "? (l/r)");
        char choice = sc.next().charAt(0);

        if (choice == 'l') {
            System.out.print("Enter left node of " + node.data + ": ");
            int data = sc.nextInt();
            node.left = new Node(data);
            insert(sc, node.left);
        } else if (choice == 'r') {
            System.out.print("Enter right node of " + node.data + ": ");
            int data = sc.nextInt();
            node.right = new Node(data);
            insert(sc, node.right);
        }
    }

    public void display() {
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }
        System.out.println(root.data);
        display(root, "");
    }

    private static void display(Node node, String prefix) {
        if (node.left != null) {
            boolean last = node.right == null;
            System.out.println(prefix + (last ? "`-- " : "|-- ") + "L: " + node.left.data);
            display(node.left, prefix + (last ? "    " : "|   "));
        }
        if (node.right != null) {
            System.out.println(prefix + "`-- " + "R: " + node.right.data);
            display(node.right, prefix + "    ");
        }
    }

    public List<Integer> preorderTraversal() {

        List<Integer> result = new ArrayList<>();
        preorderHelper(root, result);
        return result;

    }

    private void preorderHelper(Node node, List<Integer> result) {
        if (node != null) {
            result.add(node.data);
            preorderHelper(node.left, result);
            preorderHelper(node.right, result);
        }
    }

    // ==================== BST ====================
    public static class BST {
        private Node root;

        public void insert(int data) {
            root = insertRec(root, data);
        }

        private Node insertRec(Node node, int data) {
            if (node == null) {
                return new Node(data);
            }

            if (data < node.data) {
                node.left = insertRec(node.left, data);
            } else if (data > node.data) {
                node.right = insertRec(node.right, data);
            }

            return node;
        }

        public int height() {
            return height(root);
        }

        private int height(Node node) {
            if (node == null) {
                return 0;
            }

            int leftHeight = height(node.left);
            int rightHeight = height(node.right);

            int height = Math.max(leftHeight, rightHeight) + 1;
            System.out.println("Height of node " + node.data + ": " + height);

            return height;
        }

        public void display() {
            if (root == null) {
                System.out.println("Tree is empty");
                return;
            }
            System.out.println(root.data);
            Trees.display(root, "");
        }

    }

}
