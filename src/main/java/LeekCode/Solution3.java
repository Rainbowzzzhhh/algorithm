package LeekCode;

import java.util.LinkedList;

/**
 * @author rainbow
 * @time 2025-03-03 12:48
 * @description ...
 */
public class Solution3 {
    public int lengthOfLongestSubstring(String s) {
        int[] ExistChar = new int[130];
        int left = 0;
        int right = 0;
        int length = s.length();
        int maxDis = 0;
        while (right < length) {
            if (ExistChar[s.charAt(right)] == 0) {//当前字母没有访问过
                ExistChar[s.charAt(right)]++;
                right++;    //已经指向下个字母，后续right - left即为答案
                maxDis = Math.max(maxDis, right - left);
            } else {
                ExistChar[s.charAt(left)]--;
                left++;
            }
        }
        return maxDis;
    }

    public static void main(String[] args) {
        String s = "pwwkew";
        int i = new Solution3().lengthOfLongestSubstring(s);
        System.out.println(i);
    }
}
