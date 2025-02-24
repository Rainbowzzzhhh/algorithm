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


/**
 * 递归算法的三个要素。每次写递归，都按照这三要素来写，可以保证大家写出正确地递归算法！
 *
 * 1.确定递归函数的参数和返回值： 确定哪些参数是递归的过程中需要处理的，那么就在递归函数里加上这个参数，并且还要明确每次递归的返回值是什么进而确定递归函数的返回类型。
 *
 * 2.确定终止条件： 写完了递归算法, 运行的时候，经常会遇到栈溢出的错误，就是没写终止条件或者终止条件写的不对，操作系统也是用一个栈的结构来保存每一层递归的信息，如果递归没有终止，操作系统的内存栈必然就会溢出。
 *
 * 3.确定单层递归的逻辑： 确定每一层递归需要处理的信息。在这里也就会重复调用自己来实现递归的过程。
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
