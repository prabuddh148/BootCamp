package com.example.bootcamp;

import java.util.ArrayList;
import java.util.Arrays;
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
        // 30
        // / \
        // 20 40
        // / \ \
        // 10 25 50
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

    public static class SegmentTree {

        private static class SegmentTreeNode {
            int start, end;
            int sum;
            SegmentTreeNode left, right;

            public SegmentTreeNode(int start, int end) {
                this.start = start;
                this.end = end;
                this.sum = 0;
                this.left = null;
                this.right = null;
            }
        }

        SegmentTreeNode root;

        public SegmentTree(int[] nums) {
            root = buildTree(nums, 0, nums.length - 1);
        }

        private SegmentTreeNode buildTree(int[] nums, int start, int end) {
            if (start > end) {
                return null;
            }

            SegmentTreeNode node = new SegmentTreeNode(start, end);
            if (start == end) {
                node.sum = nums[start];
            } else {
                int mid = start + (end - start) / 2;
                node.left = buildTree(nums, start, mid);
                node.right = buildTree(nums, mid + 1, end);
                node.sum = node.left.sum + node.right.sum;
            }
            return node;
        }

        // Sum of nums[qStart..qEnd] (both inclusive)
        public int query(int qStart, int qEnd) {
            return query(root, qStart, qEnd);
        }

        private int query(SegmentTreeNode node, int qStart, int qEnd) {
            // No overlap
            if (node == null || qEnd < node.start || qStart > node.end) {
                return 0;
            }

            // Complete overlap: node's range lies fully inside the query
            if (qStart <= node.start && node.end <= qEnd) {
                return node.sum;
            }

            // Partial overlap: ask both children
            return query(node.left, qStart, qEnd) + query(node.right, qStart, qEnd);
        }

        // Prints the tree top-down, each node as [start-end]=sum, e.g. for {1, 2, 3}
        //         [0-2]=6
        //       /         \
        //   [0-1]=3     [2-2]=3
        //   /    \
        // [0-0]=1 [1-1]=2
        public void display() {
            if (root == null) {
                System.out.println("Tree is empty");
                return;
            }

            int h = height(root);
            int cell = maxWidth(root) + 2;
            int width = (1 << (h - 1)) * cell;

            List<SegmentTreeNode> level = new ArrayList<>();
            level.add(root);

            for (int depth = 0; depth < h; depth++) {
                int slots = 1 << depth;
                int slotWidth = width / slots;
                char[] values = blank(width);
                char[] branches = blank(width);
                List<SegmentTreeNode> next = new ArrayList<>();

                for (int i = 0; i < slots; i++) {
                    SegmentTreeNode node = level.get(i);
                    int center = i * slotWidth + slotWidth / 2;

                    if (node != null) {
                        String s = label(node);
                        int start = Math.max(0, center - s.length() / 2);
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

        private String label(SegmentTreeNode node) {
            return "[" + node.start + "-" + node.end + "]=" + node.sum;
        }

        private int height(SegmentTreeNode node) {
            if (node == null) {
                return 0;
            }
            return Math.max(height(node.left), height(node.right)) + 1;
        }

        private int maxWidth(SegmentTreeNode node) {
            if (node == null) {
                return 0;
            }
            int w = label(node).length();
            return Math.max(w, Math.max(maxWidth(node.left), maxWidth(node.right)));
        }

        private static char[] blank(int n) {
            char[] row = new char[n];
            Arrays.fill(row, ' ');
            return row;
        }

    }

}
