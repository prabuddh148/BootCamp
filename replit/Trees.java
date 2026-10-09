import java.util.*;

public class Trees {

    // LeetCode's binary tree node
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // ==================== Apna code yahan likho ====================

    static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                level.add(current.val);
                if (current.left != null) queue.offer(current.left);
                if (current.right != null) queue.offer(current.right);
            }
            result.add(level);
        }
        return result;
    }

    public static void main(String[] args) {
        // LeetCode jaisa input: null = wahan node nahi hai
        TreeNode root = build(3, 9, 20, null, null, 15, 7);
        print(root);

        System.out.println("Level order: " + levelOrder(root));
    }

    // ==================== Helpers (inhe chhedne ki zaroorat nahi) ====================

    // LeetCode array se tree banata hai, e.g. build(3, 9, 20, null, null, 15, 7)
    static TreeNode build(Integer... values) {
        if (values.length == 0 || values[0] == null) {
            return null;
        }
        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while (!queue.isEmpty() && i < values.length) {
            TreeNode node = queue.poll();
            if (i < values.length && values[i] != null) {
                node.left = new TreeNode(values[i]);
                queue.offer(node.left);
            }
            i++;
            if (i < values.length && values[i] != null) {
                node.right = new TreeNode(values[i]);
                queue.offer(node.right);
            }
            i++;
        }
        return root;
    }

    // Tree ko print karta hai
    static void print(TreeNode root) {
        if (root == null) {
            System.out.println("(empty tree)");
            return;
        }
        System.out.println(root.val);
        print(root, "");
    }

    private static void print(TreeNode node, String prefix) {
        if (node.left != null) {
            boolean last = node.right == null;
            System.out.println(prefix + (last ? "`-- " : "|-- ") + "L: " + node.left.val);
            print(node.left, prefix + (last ? "    " : "|   "));
        }
        if (node.right != null) {
            System.out.println(prefix + "`-- R: " + node.right.val);
            print(node.right, prefix + "    ");
        }
    }
}
