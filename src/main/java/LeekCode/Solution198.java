package LeekCode;

/**
 * @author rainbow
 * @time 2025-03-04 10:14
 * @description ...
 */
public class Solution198 {
    //dp[i] = max(dp[i-1],dp[i-2]+nums[i])
    public int rob(int[] nums) {
        int length = nums.length;
        if (length == 1) return nums[0];
        if (length == 2) return Math.max(nums[0], nums[1]);
        if (length == 3) return Math.max(nums[0] + nums[2], nums[1]);

        int[] maxRobAmountNow = new int[length];
        maxRobAmountNow[0] = nums[0];
        maxRobAmountNow[1] = nums[1];
        maxRobAmountNow[2] = Math.max(nums[0] + nums[2], nums[1]);

        for (int i = 3; i < length; i++) {  //从第三个位置开始
            maxRobAmountNow[i] =
                    Math.max(maxRobAmountNow[i - 1],
                            Math.max(maxRobAmountNow[i - 2] + nums[i], maxRobAmountNow[i - 3] + nums[i]));
        }
        return maxRobAmountNow[length - 1];
    }
}
