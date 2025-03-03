package Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author rainbow
 * @time 2025-02-24 1:48 PM
 * @description ...
 */
public class Solution513 {
    public int findBottomLeftValue(TreeNode root) {
        //if (root == null) return 0;
        //假设二叉树中至少有一个节点。
        Queue<TreeNode> q = new LinkedList<>();
        int res = 0;

        q.offer(root);

        while (!q.isEmpty()) {
            res = q.peek().val;   //每一层最左边标识为1
            int size = q.size();
            while (size-- > 0) {    //每一层开始遍历
                TreeNode node = q.poll();
                //left
                if (node.left != null) q.offer(node.left);
                //right
                if (node.right != null) q.offer(node.right);

            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2, new TreeNode(1, null, null), new TreeNode(3, null, null));
        int res = new Solution513().findBottomLeftValue(root);
        System.out.print(res);

    }
}
