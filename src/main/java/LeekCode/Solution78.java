package LeekCode;

import java.util.*;

/**
 * @author rainbow
 * @time 2025-03-05 09:31
 * @description 78 子集
 */

public class Solution78 {
    LinkedList<Integer> path = new LinkedList<>();
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        backTracing(nums,nums.length,0);
        return res;
    }

    public void backTracing(int[] nums, int length, int indexNow) {
        res.add(new ArrayList<>(path));
        if (indexNow == length) return;

        for (int i = indexNow; i < length; i++) {
            path.addLast(nums[i]);
            backTracing(nums, length, i + 1);
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 4, 6};
        List<List<Integer>> subsets = new Solution78().subsets(nums);
        System.out.println(subsets);
    }
}
