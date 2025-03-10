package LeekCode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author rainbow
 * @time 2025-03-10 09:35
 * @description ...
 */
public class Solution128 {
    public int longestConsecutiveOverTime(int[] nums) {
        int length = nums.length;
        if (length == 0) return 0;

        int res = 0;
        HashMap<Integer, Integer> letter = new HashMap<>();

        for (int numNow : nums) {

            int numNowValue = letter.getOrDefault(numNow - 1, 0) + 1;
            letter.put(numNow, numNowValue); //为numNow赋值当前的最大连续次数
            res = Math.max(res, letter.get(numNow));

            //如果对当前数字为序列中的一个，则不必给后面赋值，可以直接跳出
            if ((letter.get(numNow + 1) == null) || ((numNowValue + 1) <= letter.get(numNow + 1)))
                continue;

            while (letter.get(numNow + 1) != null) {    //为已经出现过的numNow后的连续数字刷新最大值
                numNow++;   //->numNow+1
                numNowValue++;  //后移一位
                letter.put(numNow, numNowValue);    //由于这是新增的，因此必然会比以前的连续次数大
            }

            res = Math.max(res, letter.get(numNow));
        }
        return res;
    }

    public int longestConsecutive(int[] nums) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int num : nums) {
            hashSet.add(num);
        }

        int res = 0;

        for (Integer numNow : hashSet) {
            if (hashSet.contains(numNow - 1)) continue;

            int curNum = numNow;
            int curSeqTimes = 1;

            while (hashSet.contains(curNum + 1)) {
                curNum++;
                curSeqTimes++;
            }
            res = Math.max(res,curSeqTimes);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 0, 1, 2};
        int i = new Solution128().longestConsecutive(nums);
        System.out.println(i);

    }
}
