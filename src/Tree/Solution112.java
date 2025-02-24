package Tree;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author rainbow
 * @time 2025-02-24 2:33 PM
 * @description ...
 */
public class Solution112 {
    LinkedList<Integer> temp = new LinkedList<>();

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        return backTracing(root,targetSum);
    }

    public boolean backTracing(TreeNode node, int target) {
        //叶子节点未加入temp
        if (node.left == null && node.right == null) {
            int sum = 0;
            for (Integer i : temp) {
                sum += i;
            }
            sum += node.val;    //加入叶子节点
            return sum == target;
        }

        temp.addLast(node.val); //处理节点;

        if (node.left != null) {    //非空防止单孩子空值进入
            boolean boolLeft = backTracing(node.left, target);
            if(boolLeft) return true;
        }
        if (node.right != null) {
            boolean boolRight = backTracing(node.right, target);
            if(boolRight) return true;
        }

        temp.removeLast();  //回溯，撤销处理结果

        return false;
    }
}
