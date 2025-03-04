package LeekCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author rainbow
 * @time 2025-03-04 11:40
 * @description ...
 */
public class Solution15 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int length = nums.length;
        for (int first = 0; first < length - 2; first++) {
            if (first > 0 && nums[first] == nums[first - 1]) {  //判断当前数字是否已经被遍历过
                continue;
            }
            int left = first + 1;
            int right = length - 1;
            int target = -nums[first];

            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    res.add(Arrays.asList(nums[first], nums[left], nums[right]));
                    left++;
                    right--;
                    while (nums[left] == nums[left - 1] && left < right) left++;
                    while (nums[right] == nums[right + 1] && left < right) right--;
                } else if (sum < target) {
                    do left++;
                    while (nums[left] == nums[left - 1] && left < right);
                } else {
                    do right--;
                    while (nums[right] == nums[right + 1] && left < right);
                }
            }
        }
        return res;
    }
}
