package LeekCode;

import java.util.Arrays;

/**
 * @author rainbow
 * @time 2025-03-12 09:36
 * @description 轮转数组
 */
public class Solution189 {
    public void rotate(int[] nums, int k) {
        int length = nums.length;
        if (length <= k) k %= length;
        if (k == 0) return;

        int rest = length % k;

        for (int i = length - 1; i >= k; i--) {
            int temp = nums[i];
            nums[i] = nums[i - k];
            nums[i - k] = temp;
        }

        if (rest == 0) return;

        int[] tempArr = new int[k];

        System.arraycopy(nums, 0, tempArr, k - rest, rest);
        System.arraycopy(nums, rest, tempArr, 0, k - rest);
        System.arraycopy(tempArr, 0, nums, 0, k);

    }

    public void rotateLeetcode(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int count = gcd(k, n);
        for (int start = 0; start < count; ++start) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % n;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
            } while (start != current);
        }
    }

    public int gcd(int x, int y) {
        return y > 0 ? gcd(y, x % y) : x;
    }

}
