package LeekCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author rainbow
 * @time 2025-03-11 10:46
 * @description 找到字符串中所有字母异位词
 */
public class Solution438 {
    public List<Integer> findAnagrams(String s, String p) {
        int lenOfP = p.length();
        int lenOfS = s.length();
        if (lenOfS < lenOfP) return new ArrayList<>();
        ArrayList<Integer> res = new ArrayList<>();
        int[] letterS = new int[26];
        int[] letterP = new int[26];
        for (int i = 0; i < lenOfP; i++) {
            letterS[s.charAt(i) - 'a']++;
            letterP[p.charAt(i) - 'a']++;
        }

        if (Arrays.equals(letterS, letterP)) {
            res.add(0);
        }

        int left = 0;
        letterS[s.charAt(left++) - 'a']--;
        int right = lenOfP;  //左闭右闭

        while (right < lenOfS) {
            letterS[s.charAt(right) - 'a']++;

            if (Arrays.equals(letterS, letterP)) {
                res.add(left);
            }
            letterS[s.charAt(left++) - 'a']--;
            right++;
        }
        return res;
    }
}
