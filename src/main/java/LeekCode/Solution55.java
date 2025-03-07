package LeekCode;

/**
 * @author rainbow
 * @time 2025-03-07 11:04
 * @description ...
 */
public class Solution55 {
    public boolean canJump(int[] nums) {
        int length = nums.length - 1;
        if (length == 0) return true;
        int maxReachableIndex = nums[0];
        for (int i = 1; i <= length; i++) {
            if (i <= maxReachableIndex)
                maxReachableIndex = Math.max(i + nums[i], maxReachableIndex);
            if (maxReachableIndex >= length) return true;
        }
        return false;
    }
}
