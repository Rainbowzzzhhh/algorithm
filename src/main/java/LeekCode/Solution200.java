package LeekCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author rainbow
 * @time 2025-02-27 15:57
 * @description ...
 */


//用深搜和广搜把每一个岛屿全部遍历并移除（标记为0）
public class Solution200 {
    public static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public void bfs(char[][] grid, int r, int c, int rl, int cl) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(r);
        q.offer(c);
        grid[r][c] = '0';
        while (!q.isEmpty()) {
            Integer row = q.poll();
            Integer col = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextRow = row + dir[i][0];
                int nextCol = col + dir[i][1];
                if (nextRow < 0 || nextRow >= rl || nextCol < 0 || nextCol >= cl || grid[nextRow][nextCol] == '0')
                    continue;
                q.offer(nextRow);
                q.offer(nextCol);
                grid[nextRow][nextCol] = '0';
            }

        }
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int count = 0;
        int rl = grid.length;
        int cl = grid[0].length;
        for (int i = 0; i < rl; i++) {
            for (int j = 0; j < cl; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    bfs(grid, i, j, rl, cl);
                }
            }
        }
        return count;
    }

    public void dfs(char[][] grid, int r, int c, int rl, int cl) {
        if (r < 0 || c < 0 || r >= rl || c >= cl || grid[r][c] == '0') return;

        grid[r][c] = '0';
        dfs(grid, r - 1, c, rl, cl);
        dfs(grid, r + 1, c, rl, cl);
        dfs(grid, r, c - 1, rl, cl);
        dfs(grid, r, c + 1, rl, cl);
    }
}
