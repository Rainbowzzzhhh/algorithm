package LeekCode;

/**
 * @author rainbow
 * @time 2025-03-25 09:55
 * @description ...
 */
public class Solution238 {
    public int[] productExceptSelf1(int[] nums) {
        int length = nums.length;

        int[] answer = new int[length];

        int[] pre = new int[length];
        int[] behind = new int[length];
        //初始化第一个值为1
        pre[0] = 1;
        behind[length - 1] = 1;

        for (int i = 1; i < length; i++) {
            //由题目要求，pre【0】不取值
            pre[i] = pre[i - 1] * nums[i - 1];

            //behind[(len-1)-i] = behind[(len-1)-(i-1)]*nums[(len-1)-(i-1)]
            behind[length - 1 - i] = behind[length - i] * nums[length - i];
        }

        for (int i = 0; i < length; i++) {
            answer[i] = pre[i] * behind[i];
        }

        return answer;
    }

    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int[] answer = new int[length];

        // answer[i] 表示索引 i 左侧所有元素的乘积
        // 因为索引为 '0' 的元素左侧没有元素， 所以 answer[0] = 1
        answer[0] = 1;
        for (int i = 1; i < length; i++) {
            answer[i] = nums[i - 1] * answer[i - 1];
        }

        // R 为右侧所有元素的乘积
        // 刚开始右边没有元素，所以 R = 1
        int R = 1;
        for (int i = length - 1; i >= 0; i--) {
            // 对于索引 i，左边的乘积为 answer[i]，右边的乘积为 R
            answer[i] = answer[i] * R;
            // R 需要包含右边所有的乘积，所以计算下一个结果时需要将当前值乘到 R 上
            R *= nums[i];
        }
        return answer;
    }
}
