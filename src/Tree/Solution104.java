package Tree;

/**
 * @author rainbow
 * @time 2025-02-21 16:12
 * @description ...
 */
public class Solution104 {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int leftDep = 0;
        int rightDep = 0;
        if(root.left != null) leftDep = maxDepth(root.left);
        if(root.right != null) rightDep = maxDepth(root.right);
        return 1 + Math.max(leftDep,rightDep);
    }
}
