package Tree;

import java.util.Arrays;

/**
 * @author rainbow
 * @time 2025-02-25 15:17
 * @description ...
 */
public class Solution654 {
    //TODO 直接复制数组
    public TreeNode constructMaximumBinaryTreeCopyArray(int[] nums) {
        int length = nums.length;

        if (length == 0) return null;   //空节点

        if (length == 1) return new TreeNode(nums[0]);  //叶子节点

        int maxIndex = 0;
        int maxNum = Integer.MIN_VALUE;
        for (int i = 0; i < length; i++) {
            if (nums[i] > maxNum) {
                maxNum = nums[i];
                maxIndex = i;
            }
        }

        TreeNode root = new TreeNode(maxNum);

        //切割根节点左右的数组
        int[] leftNums = Arrays.copyOfRange(nums, 0, maxIndex);
        int[] rightNums = Arrays.copyOfRange(nums, maxIndex + 1, length);

        root.left = constructMaximumBinaryTreeCopyArray(leftNums);
        root.right = constructMaximumBinaryTreeCopyArray(rightNums);

        return root;
    }

    //TODO 使用下标(左闭右开)
    public TreeNode constructMaximumBinaryTree(int[] nums){
        return traversal(nums,0, nums.length);
    }
    public TreeNode traversal(int[] nums, int begin, int end) {

        if (begin >= end) return null;   //空节点

        if (end - begin == 1) return new TreeNode(nums[begin]);  //叶子节点

        int maxIndex = begin;
        int maxNum = Integer.MIN_VALUE;
        for (int i = begin; i < end; i++) {
            if (nums[i] > maxNum) {
                maxNum = nums[i];
                maxIndex = i;
            }
        }

        TreeNode root = new TreeNode(maxNum);

        //切割根节点左右的数组
        //int[] leftNums = Arrays.copyOfRange(nums, begin, maxIndex);
        //int[] rightNums = Arrays.copyOfRange(nums, maxIndex + 1, end);

        root.left = traversal(nums, begin, maxIndex);
        root.right = traversal(nums, maxIndex + 1, end);

        return root;
    }

}
