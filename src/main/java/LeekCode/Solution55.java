package LeekCode;

/**
 * @author rainbow
 * @time 2025-03-04 09:37
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

    public static void main(String[] args) {
        int[] a = {2, 3, 1, 1, 4};
        boolean b = new Solution55().canJump(a);
        System.out.println(b);
    }
}
