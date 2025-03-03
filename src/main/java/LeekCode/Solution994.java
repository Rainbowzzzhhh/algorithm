package LeekCode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author rainbow
 * @time 2025-02-27 16:52
 * @description ...
 */
public class Solution994 {
    public static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    static class pair {
        int row, col;

        pair(int r, int c) {
            row = r;
            col = c;
        }
    }

    public void bfs(boolean[][] visited, int[][] grid, int r, int c, int rn, int cn) {
        Map<pair, Integer> minuteOfNode = new HashMap<>();
        Queue<pair> q = new LinkedList<>();

        pair firstPair = new pair(r, c);
        q.offer(firstPair);
        minuteOfNode.put(firstPair, 0);
        visited[r][c] = true;

        while (!q.isEmpty()) {
            pair nodeNow = q.poll();
            int curRow = nodeNow.row;
            int curCol = nodeNow.col;
            int nextMin = minuteOfNode.get(nodeNow) - 1;

            for (int i = 0; i < 4; i++) {
                int nextRow = curRow + dir[i][0];
                int nextCol = curCol + dir[i][1];
                if (nextRow < 0 || nextRow >= rn || nextCol < 0 || nextCol >= cn || grid[nextRow][nextCol] == 0 || visited[nextRow][nextCol])
                    continue;

                grid[nextRow][nextCol] = grid[nextRow][nextCol] == 1 ? nextMin : Math.max(nextMin, grid[nextRow][nextCol]);
                pair nextPair = new pair(nextRow, nextCol);
                q.offer(nextPair);
                minuteOfNode.put(nextPair, nextMin);
                visited[nextRow][nextCol] = true;
            }
        }
    }

    public int orangesRotting(int[][] grid) {
        int rn = grid.length;
        int cn = grid[0].length;
        for (int i = 0; i < rn; i++) {
            for (int j = 0; j < cn; j++) {
                if (grid[i][j] == 2) {   //为感染源
                    boolean[][] visited = new boolean[rn][cn];
                    bfs(visited, grid, i, j, rn, cn); //用负数标记分钟数
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int[] row : grid) {
            for (int col : row) {
                if (col == 1) return -1;    //还有1则有没被感染的
                if (min > col) min = col;

            }
        }
        return min == 2 ? 0 : -min;
    }
}
