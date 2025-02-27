package Map;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author rainbow
 * @time 2025-02-27 13:04
 * @description ...
 */
public class Solution797 {
    ArrayList<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public void dfs(int point, int[][] graph, int n) {
        path.addLast(point);
        if (point == n) {
            res.add(new ArrayList<>(path));
            path.removeLast();
            return;
        }
        for (int i : graph[point]) {
            dfs(i, graph, n);
        }
        path.removeLast();
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        dfs(0, graph, graph.length - 1);
        return res;
    }

    public static void main(String[] args) {
        int[][] graph = {{1, 2}, {3}, {3}, {}};
        for (int[] i : graph) {
            System.out.println(i);
        }
        List<List<Integer>> res = new Solution797().allPathsSourceTarget(graph);
        System.out.println(res);
    }
}
