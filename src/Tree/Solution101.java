package Tree;

import java.util.ArrayList;

/**
 * @author rainbow
 * @time 2025-02-21 15:11
 * @description ...
 */
public class Solution101 {
    ArrayList<Integer> left = new ArrayList<>();
    ArrayList<Integer> right = new ArrayList<>();

    public boolean isSymmetric(TreeNode root) {
        methodLeft(root.left);
        methodRight(root.right);
        if (left.equals(right)) return true;
        else return false;
    }

    public void methodLeft(TreeNode root) {
        if (root == null) {
            left.add(null);
            return;
        }
        left.add(root.val);
        methodLeft(root.left);
        methodLeft(root.right);
    }

    public void methodRight(TreeNode root) {
        if (root == null) {
            right.add(null);
            return;
        }
        right.add(root.val);
        methodRight(root.right);
        methodRight(root.left);
    }
}
