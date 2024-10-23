
import java.util.LinkedList;
import java.util.Queue;

/**
 * Cousin_Binary_Tree_II
 * APPROACH - 2 : ONE PASS SOLUTION 
 */

public class Cousin_Binary_Tree_II {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static TreeNode replaceValueInTree(TreeNode root) {
        if (root == null) return null;

       // 1 pass Solution 

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int levelSum = root.val;


        // adjust the values based on cousin sums
        while (!queue.isEmpty()) {
            int size = queue.size();
            int nextLevelSum = 0;

            for (int i = 0; i < size; i++) {
                TreeNode currNode = queue.poll();

                currNode.val = levelSum - currNode.val;

                int siblingsSum = (currNode.left != null ? currNode.left.val : 0) +
                                  (currNode.right != null ? currNode.right.val : 0);

                if (currNode.left != null) {
                    nextLevelSum += currNode.left.val;
                    currNode.left.val = siblingsSum;
                    queue.offer(currNode.left);
                }

                if (currNode.right != null) {
                    nextLevelSum += currNode.right.val;
                    currNode.right.val = siblingsSum;
                    queue.offer(currNode.right);
                }
            }

            levelSum = nextLevelSum;
        }

        return root;
    }

    // Utility function to create a binary tree from an array of values
    public static TreeNode createBinaryTree(Integer[] values) {
        if (values.length == 0 || values[0] == null) return null;

        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        for (int i = 1; i < values.length; i++) {
            TreeNode current = queue.poll();

            if (values[i] != null) {
                current.left = new TreeNode(values[i]);
                queue.offer(current.left);
            }
            i++; // Move to the next value
            if (i < values.length && values[i] != null) {
                current.right = new TreeNode(values[i]);
                queue.offer(current.right);
            }
        }

        return root;
    }

    // Utility function to print the binary tree in level order
    public static void printTree(TreeNode root) {
        if (root == null)
            return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            System.out.print(current.val + " ");

            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Test case: root = [5, 4, 9, 1, 10, null, 7]
        Integer[] values = {5, 4, 9, 1, 10, null, 7};
        TreeNode root = createBinaryTree(values);

        System.out.println("Original tree (level order):");
        printTree(root);

        // Replace values according to cousin sums
        TreeNode modifiedRoot = replaceValueInTree(root);

        System.out.println("Modified tree (level order):");
        printTree(modifiedRoot);
    }
}
