package LeekCode;

/**
 * @author rainbow
 * @time 2025-03-04 10:35
 * @description ...
 */
public class Solution11 {
    public int maxArea(int[] height) {
        int length = height.length;
        int maxWater = 0;
        int left = 0;
        int right = length - 1;
        while (left < right) {
            int rightHeight = height[right];
            int leftHeight = height[left];
            if (rightHeight < leftHeight) {
                maxWater = Math.max(maxWater, (right - left) * rightHeight);
                right--;
            } else {
                maxWater = Math.max(maxWater, (right - left) * leftHeight);
                left++;
            }
        }
        return maxWater;
    }
}
