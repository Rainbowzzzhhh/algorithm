package LeekCode;

/**
 * @author rainbow
 * @time 2025-03-07 09:12
 * @description ...
 */
public class Solution53 {
    public int maxSubArray(int[] nums) {
        int length = nums.length;
        int[] dp = new int[length];
        dp[0] = Math.max(nums[0], 0);
        int max = nums[0];
        for (int i = 1; i < length; i++) {
            if (nums[i] + dp[i - 1] > 0) {
                dp[i] = nums[i] + dp[i - 1];  //如果和大于0则记录进子数组
                if (dp[i] > max) max = dp[i];   //更新max
            } else {
                dp[i] = 0;
                if (nums[i] > max) max = nums[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int i = new Solution53().maxSubArray(nums);
        System.out.println(i);
    }
}
