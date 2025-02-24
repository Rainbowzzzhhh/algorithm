package Tree;

/**
 * @author rainbow
 * @time 2025-02-21 15:22
 * @description ...
 */


/**
 * 前序遍历即先使用节点，
 * 后序遍历即后使用节点。
 * 设计算法时，遍历顺序即节点使用时刻
 */
public class TreeNode {
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
