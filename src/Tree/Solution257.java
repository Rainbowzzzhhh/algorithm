package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author rainbow
 * @time 2025-02-24 10:52 AM
 * @description ...
 */
public class Solution257 {
    ArrayList<String> res = new ArrayList<>();
    LinkedList<Integer> temp = new LinkedList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) return null;
        backTracing(root);
        return res;
    }

    public void backTracing(TreeNode node) {
        //叶子节点未加入temp
        if (node.left == null && node.right == null) {
            StringBuilder sb = new StringBuilder();
            for (Integer i : temp) {
                sb.append(i);
                sb.append("->");
            }
            sb.append(node.val);    //加入叶子节点
            String path = sb.toString();
            res.add(path);
            return;
        }

        temp.addLast(node.val); //处理节点;

        if (node.left != null) {    //非空防止单孩子空值进入
            backTracing(node.left);
        }
        if (node.right != null) {
            backTracing(node.right);
        }

        temp.removeLast();  //回溯，撤销处理结果
    }
}
