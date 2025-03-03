package Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author rainbow
 * @time 2025-02-26 10:42
 * @description ...
 */
public class Solution98 {
    public boolean isValidBST1(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return true;
        List<Integer> list = inOrderTraversal(root);
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) return false;
        }
        return true;
    }

    public List<Integer> inOrderTraversal(TreeNode root) {
        if (root == null) return null;

        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        while (!s.isEmpty()) {
            TreeNode node = s.pop();
            if (node != null) {
                if (node.right != null) s.push(node.right);
                s.push(node);
                s.push(null);
                if (node.left != null) s.push(node.left);
            } else {
                res.add(s.pop().val);
            }
        }
        return res;
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return true;
        Stack<TreeNode> s = new Stack<>();
        s.push(root);

        TreeNode pre = null;

        while (!s.isEmpty()) {
            TreeNode node = s.pop();
            if (node != null) {
                if (node.right != null) s.push(node.right);
                s.push(node);
                s.push(null);
                if (node.left != null) s.push(node.left);
            } else {
                TreeNode node1 = s.pop();
                if (pre != null && pre.val >= node1.val) return false;
                pre = node1;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2, new TreeNode(2), new TreeNode(2));
        boolean bool = new Solution98()
                .isValidBST1(root);
        System.out.println(bool);
    }
}
