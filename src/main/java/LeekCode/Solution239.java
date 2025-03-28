package LeekCode;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author rainbow
 * @time 2025-03-28 11:11
 * @description 239 滑动窗口最大值
 */
public class Solution239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;

        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] pair1, int[] pair2) {
                return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
            }
        });

        for (int i = 0; i < k; ++i) {
            pq.offer(new int[]{nums[i], i});
        }

        int[] ans = new int[len - k + 1];
        ans[0] = pq.peek()[0];

        for (int i = k; i < len; ++i) {
            pq.offer(new int[]{nums[i], i});

            while (pq.peek()[1] <= i - k) { //去掉左窗口后面的直到遇到第一个窗口内的
                pq.poll();
            }

            ans[i - k + 1] = pq.peek()[0];

        }
        return ans;
    }

    public int[] maxSlidingWindowDescQueue(int[] nums, int k) {
        int n = nums.length;

        Deque<Integer> deque = new LinkedList<Integer>();

        for (int i = 0; i < k; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }

        int[] ans = new int[n - k + 1];

        ans[0] = nums[deque.peekFirst()];
        for (int i = k; i < n; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }
}
