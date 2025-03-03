package Tree;

import java.util.Arrays;

/**
 * @author rainbow
 * @time 2025-02-25 10:36
 * @description ...
 */
public class Solution106 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return traversal(inorder, 0, inorder.length, postorder, 0, postorder.length);
    }

    //为左开右闭，end对应数组实际取数位置的下一位
    public TreeNode traversal(int[] inorder, int inBegin, int inEnd, int[] postorder, int postBegin, int postEnd) {
        if (postEnd == postBegin) return null;

        int rootValue = postorder[postEnd - 1];
        TreeNode root = new TreeNode(rootValue);

        if ((postEnd - postBegin) == 1) return root;

        int indexOfIn = inBegin;    //为根节点值的位置
        for (; indexOfIn < inEnd; indexOfIn++) {
            if (inorder[indexOfIn] == rootValue) break;
        }

        int leftInEnd = indexOfIn;
        int rightInBegin = leftInEnd + 1;

        int leftPostEnd = postBegin + leftInEnd - inBegin;
        int rightPostEnd = postEnd - 1;

        root.left = traversal(inorder, inBegin, leftInEnd, postorder, postBegin, leftPostEnd);
        root.right = traversal(inorder, rightInBegin, inEnd, postorder, leftPostEnd, rightPostEnd);

        return root;
    }

    public TreeNode buildTreeCopyArray(int[] inorder, int[] postorder) {
        if (postorder.length == 0) return null; //空

        int rootValue = postorder[postorder.length - 1];
        TreeNode root = new TreeNode(rootValue);

        if (postorder.length == 1) return root; //叶子

        //找中序的中间点
        int index = 0;
        while (index < inorder.length) {
            if (inorder[index++] == rootValue) break;   //index指向根节点后一位
        }

        //切割中序（左闭右开）
        int[] leftInOrder = Arrays.copyOfRange(inorder, 0, index - 1);
        int[] rightInOrder = Arrays.copyOfRange(inorder, index, inorder.length);

        //切割后序
        int[] leftPostOrder = Arrays.copyOfRange(postorder, 0, leftInOrder.length);
        int[] rightPostOrder = Arrays.copyOfRange(postorder, leftInOrder.length, postorder.length - 1); //最后一位是根节点不要


        root.left = buildTree(leftInOrder, leftPostOrder);
        root.right = buildTree(rightInOrder, rightPostOrder);

        return root;
    }


    public static void main(String[] args) {
        int[] in = {9, 3, 15, 20, 7};
        int[] post = {9, 15, 7, 20, 3};
        TreeNode treeNode = new Solution106()
                .buildTree(in, post);
    }
}
