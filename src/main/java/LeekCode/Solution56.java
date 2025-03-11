package LeekCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.*;

/**
 * @author rainbow
 * @time 2025-03-11 09:44
 * @description 合并区间
 */
public class Solution56 {
    public int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> res = new ArrayList<>();

        int startNow = intervals[0][0];
        int endNow = intervals[0][0];

        for (int[] interval : intervals) {
            if (interval[0] <= endNow) {    //第i个区间的start小于上一个连续区间的最大值，合并
                endNow = Math.max(interval[1], endNow);
            } else {
                res.add(new int[]{startNow, endNow});
                startNow = interval[0];
                endNow = interval[1];
            }
        }
        res.add(new int[]{startNow, endNow});

        return res.toArray(new int[res.size()][]);
    }

    public int[][] merge2(int[][] intervals) {

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        int[] pre = intervals[0];
        int k = 0;

        for (int[] interval : intervals) {
            if (interval[0] <= pre[1]) {    //第i个区间的start小于上一个连续区间的最大值，合并
                pre[1] = Math.max(interval[1], pre[1]);
            } else {
                intervals[k][0] = pre[0];
                intervals[k++][1] = pre[1];

                pre = interval;
            }
        }
        intervals[k][0] = pre[0];
        intervals[k++][1] = pre[1];

        return Arrays.copyOfRange(intervals, 0, k);
    }
}


























