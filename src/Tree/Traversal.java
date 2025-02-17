package Tree;

import java.util.*;

/**
 * @author rainbow
 * @time 2025-02-12 10:38 PM
 * @description ...
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
}

class Traversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return result;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {//一直到左节点
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                result.add(cur.val);
                cur = cur.right;
            }
        }
        return result;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        Collections.reverse(result);
        return result;
    }
}

class TraversalSameModule {
    //就是要处理的节点放入栈之后，紧接着放入一个空指针作为标记。 这种方法可以叫做空指针标记法。
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if (node != null) {
                stack.pop();//节点弹出避免重复处理
                if (node.right != null) stack.push(node.right);
                if (node.left != null) stack.push(node.left);
                stack.push(node);
                stack.push(null);
            } else {
                stack.pop();
                res.add(stack.pop().val);
            }
        }
        return res;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if (node != null) {
                stack.pop();
                if (node.right != null) stack.push(node.right);
                stack.push(node);
                stack.push(null);
                if (node.left != null) stack.push(node.left);
            } else {
                stack.pop();
                res.add(stack.pop().val);
            }
        }
        return res;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if (node != null) {
                stack.pop();
                stack.push(node);
                stack.push(null);
                if (node.right != null) stack.push(node.right);
                if (node.left != null) stack.push(node.left);
            } else {
                stack.pop();
                res.add(stack.pop().val);
            }
        }
        return res;
    }
}

class LayerTraversal {
    public List<List<Integer>> resList = new ArrayList<List<Integer>>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        //checkFun01(root,0);
        checkFun02(root);

        return resList;
    }

    public void checkFun01(TreeNode node, Integer deep) {
        if (node == null) return;
        deep++;
        if (resList.size() < deep) {
            List<Integer> arrayList = new ArrayList<>();
            resList.add(arrayList);
        }
        resList.get(deep-1).add(node.val);
        checkFun01(node.left,deep);
        checkFun01(node.right,deep);
    }

    public void checkFun02(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> arrayList = new ArrayList<>();
            int len = queue.size();
            while (len > 0) {
                TreeNode node = queue.poll();
                arrayList.add(node.val);

                queue.offer(node.left);
                queue.offer(node.right);
                len--;
            }
            resList.add(arrayList);
        }
    }
}