package LeekCode;

import sun.security.util.Length;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author rainbow
 * @time 2025-03-03 16:12
 * @description ...
 */
public class Solution43 {
    LinkedList<Integer> path = new LinkedList<>();
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        backTracing(nums, nums.length, new boolean[nums.length], 0);
        return res;
    }

    public void backTracing(int[] nums, int length, boolean[] visited, int num) {
        if (num == length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < length; i++) {
            if (visited[i]) continue;
            path.addLast(nums[i]);
            visited[i] = true;
            backTracing(nums, length, visited, num + 1);
            visited[i] = false;
            path.removeLast();
        }
    }
}
