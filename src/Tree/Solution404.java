package Tree;

import jdk.jfr.internal.tool.Main;

/**
 * @author rainbow
 * @time 2025-02-24 1:09 PM
 * @description ...
 */
public class Solution404 {
    public static int res = 0;

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        if (root.left != null) preOrder(root.left, 1);
        if (root.right != null) preOrder(root.right, 0);
        return res;
    }

    public void preOrder(TreeNode node, int flag) {
        if (node.left == null && node.right == null) {  //找到叶子
            if (flag == 1) res += node.val; //为左叶子就加
            return;
        }
        if (node.left != null) preOrder(node.left, 1);
        if (node.right != null) preOrder(node.right, 0);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, null, null);
        int i = new Solution404().sumOfLeftLeaves(root);
        System.out.print(i);
    }
}
