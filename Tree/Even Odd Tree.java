/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isEvenOddTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int level = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            // int prev = -1;
            int prev = (level % 2 == 0) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            for(int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();

                // if(level > 0 || true) {
                    // if level index is Even 
                    if(level % 2 == 0) {
                        if(prev == -1) {
                            // First element 
                            prev = curr.val;
                        }else {
                            if(prev >= curr.val || curr.val % 2 == 0) {
                                return false;
                            }
                            prev = curr.val;
                        }    
                    }

                    // if level index is Odd
                    if(level % 2 == 1) {
                        if(prev == -1) {
                            // First element 
                            prev = curr.val;
                        }else {
                            if(prev <= curr.val || curr.val % 2 == 1) {
                                return false;
                            }
                            prev = curr.val;
                        }   
                    }

                // }

                if(curr.left != null) {
                    queue.offer(curr.left);
                }

                if(curr.right != null) {
                    queue.offer(curr.right);
                }
            }
            level++;
        }

        return true;
    }
}