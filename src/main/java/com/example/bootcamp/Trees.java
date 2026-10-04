package com.example.bootcamp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.w3c.dom.Node;

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

    public static class AVL {
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

            return rotate(node);
        }

        private Node rotate(Node node) {
            if (node == null) {
                return null;
            }

            if (height(node.left) - height(node.right) > 1) {
                if (height(node.left.left) >= height(node.left.right)) {
                    // Left Left Case
                    node = rightRotate(node);
                } else {
                    // Left Right Case
                    node.left = leftRotate(node.left);
                    node = rightRotate(node);
                }
            }

            if (height(node.right) - height(node.left) > 1) {
                if (height(node.right.right) >= height(node.right.left)) {
                    // Right Right Case
                    node = leftRotate(node);
                } else {
                    // Right Left Case
                    node.right = rightRotate(node.right);
                    node = leftRotate(node);
                }
            }
            return node;
        }

        private Node rightRotate(Node node) {

            Node newRoot = node.left;
            node.left = newRoot.right;
            newRoot.right = node;
            return newRoot;
        }

        private Node leftRotate(Node node) {
            Node newRoot = node.right;
            node.right = newRoot.left;
            newRoot.left = node;
            return newRoot;
        }

        public int height() {
            return height(root);
        }

        private int height(Node node) {
            if (node == null) {
                return 0;
            }

            // No logging here: rotate() calls height() many times per insert
            return Math.max(height(node.left), height(node.right)) + 1;
        }

        // Prints the tree top-down, e.g.
        //        30
        //     /      \
        //    20      40
        //   /  \       \
        //  10  25      50
        public void display() {
            if (root == null) {
                System.out.println("Tree is empty");
                return;
            }

            int h = height(root);
            int cell = maxWidth(root) + 2;
            int width = (1 << (h - 1)) * cell * 2;

            List<Node> level = new ArrayList<>();
            level.add(root);

            for (int depth = 0; depth < h; depth++) {
                int slots = 1 << depth;
                int slotWidth = width / slots;
                char[] values = blank(width);
                char[] branches = blank(width);
                List<Node> next = new ArrayList<>();

                for (int i = 0; i < slots; i++) {
                    Node node = level.get(i);
                    int center = i * slotWidth + slotWidth / 2;

                    if (node != null) {
                        String s = String.valueOf(node.data);
                        int start = center - s.length() / 2;
                        s.getChars(0, s.length(), values, start);

                        if (node.left != null) {
                            branches[center - slotWidth / 4] = '/';
                        }
                        if (node.right != null) {
                            branches[center + slotWidth / 4 - 1] = '\\';
                        }
                    }

                    next.add(node == null ? null : node.left);
                    next.add(node == null ? null : node.right);
                }

                System.out.println(new String(values).stripTrailing());
                if (depth < h - 1) {
                    System.out.println(new String(branches).stripTrailing());
                }
                level = next;
            }
        }

        private int maxWidth(Node node) {
            if (node == null) {
                return 0;
            }
            int w = String.valueOf(node.data).length();
            return Math.max(w, Math.max(maxWidth(node.left), maxWidth(node.right)));
        }

        private static char[] blank(int n) {
            char[] row = new char[n];
            Arrays.fill(row, ' ');
            return row;
        }

    }

}
