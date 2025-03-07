package LeekCode;

/**
 * @author rainbow
 * @time 2025-03-07 10:26
 * @description ...
 */
public class Solution45 {
    public int jump(int[] nums) {
        int length = nums.length;
        if (length == 1) return 0;
        int path = 1;
        int maxReachedPlaceNow = nums[0];
        int maxReachedPlaceLast = 0;

        for (int i = 1; maxReachedPlaceNow < length - 1; i++) {
            int maxRangeOfi = i + nums[i];
            if (maxRangeOfi > maxReachedPlaceNow) {
                if (i <= maxReachedPlaceLast) {
                    //为上一个范围内更好的下一步，因此不更新步数
                    maxReachedPlaceNow = maxRangeOfi;
                } else {
                    //为新的一步
                    maxReachedPlaceLast = maxReachedPlaceNow;
                    maxReachedPlaceNow = maxRangeOfi;
                    path++;
                }
            }
        }
        return path;
    }

    public static void main(String[] args) {
        int[] nums = {2, 1};
        int path = new Solution45().jump(nums);
        System.out.println(path);
    }
}
