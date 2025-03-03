package LeekCode;

import java.util.*;

/**
 * @author rainbow
 * @time 2025-02-28 10:44
 * @description ...
 */
public class Solution207 {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, ArrayList<Integer>> map = new HashMap<>(); //记录文件依赖关系
        int[] inDegree = new int[numCourses];   //记录每门课程的入度

        for (int[] preReq : prerequisites) {
            int pre = preReq[1];
            int tar = preReq[0];
            map.computeIfAbsent(pre, k -> new ArrayList<>()).add(tar); // 简化Map操作
            inDegree[tar]++; // 正确增加目标课程的入度
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) q.offer(i);   //将入度为0的全部加入
        }

        ArrayList<Integer> res = new ArrayList<>();
        while (!q.isEmpty()) {
            Integer pre = q.poll();
            res.add(pre);

            ArrayList<Integer> outDependencies = map.getOrDefault(pre, new ArrayList<>());
            for (Integer outDependency : outDependencies) {
                if (--inDegree[outDependency] == 0) q.offer(outDependency);
            }
        }

        return res.size() == numCourses;
    }
}
