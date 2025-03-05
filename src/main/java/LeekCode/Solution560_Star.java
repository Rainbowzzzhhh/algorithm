package LeekCode;

import java.util.HashMap;

/**
 * @author rainbow
 * @time 2025-03-05 10:05
 * @description 前缀和配合哈希表
 */
public class Solution560_Star {
    public int subarraySum(int[] nums, int k) {

        /*
         pre[i] - pre[j-1] = sum(j...i) = k
         -> pre[j-1] = pre[i] - k
         */

        HashMap<Integer, Integer> jMap = new HashMap<>();   //用于记录pre[j-1]
        int pre = 0;
        int count = 0;

        for (int num : nums) {
            //在遍历到第i个数时，通过存jMap的方式把所有的子数组前缀和pre[j-1]获取到，并与pre[i]-k做比较得出结果。jMap还能记录所有的出现过的相同pre[j-1]
            jMap.put(pre, jMap.getOrDefault(pre, 0) + 1);
            pre += num;    //pre[i]
            if (jMap.containsKey(pre - k)) {
                count += jMap.get(pre - k);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {-1, -1, 1};
        int i = new Solution560_Star().subarraySum(nums, 0);
        System.out.println(i);
    }
}
