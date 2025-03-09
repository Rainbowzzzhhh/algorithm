package LeekCode;

import java.util.Arrays;

/**
 * @author rainbow
 * @time 2025-03-09 11:23
 * @description ...
 */
public class Solution34 {
    public int[] searchRange(int[] nums, int target) {
        int length = nums.length - 1;
        if (length == 0) return nums[0] == target ? new int[]{0, 0} : new int[]{-1, -1};
        int left = 0;
        int right = length;
        int[] res = new int[2];
        Arrays.fill(res, -1);

        while (left <= right) {     //取等是因为可以到同一个数字
            int mid = ((right - left) >> 1) + left;    //偏左
            if (nums[mid] == target) {
                left = mid;
                right = mid;
                while (left > 0 && nums[left - 1] == target) left--;
                while (right < length && nums[right + 1] == target) right++;
                res[0] = left;
                res[1] = right;
                break;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,4};
        //System.out.println(((5 - 3) >> 1) + 3);
        int[] ints = new Solution34().searchRange(nums, 4);
        System.out.println(Arrays.toString(ints));
    }
}
