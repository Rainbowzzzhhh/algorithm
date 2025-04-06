package test;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author rainbow
 * @time 2025-04-02 21:31
 * @description ...
 */
public class Test2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int rows = in.nextInt();
        int cols = in.nextInt();

        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; ++i)
            for (int j = 0; j < cols; ++j) {
                matrix[i][j] = in.nextInt();
            }
        LinkedList<Point> path = new LinkedList<>();
        dfs(matrix, 0, 0, rows - 1, cols - 1, path);
    }

    public static boolean dfs(int[][] matrix, int x, int y, int rows, int cols,
                              LinkedList<Point> path) {
        path.addLast(new Point(x, y));
        matrix[x][y] = 1;
        if (x == rows && y == cols)return true;
        //向下走
        if (x + 1 <= rows && matrix[x + 1][y] == 0) {
            if (dfs(matrix, x + 1, y, rows, cols, path))return true;
        }
        //向上走
        if (x - 1 >= 0 && matrix[x - 1][y] == 0) {
            if (dfs(matrix, x - 1, y, rows, cols, path))return true;
        }
        //向左走
        if (y - 1 >= 0 && matrix[x][y - 1] == 0) {
            if (dfs(matrix, x, y - 1, rows, cols, path))return true;
        }
        //向右走
        if (y + 1 <= cols && matrix[x][y + 1] == 0) {
            if (dfs(matrix, x, y + 1, rows, cols, path))return true;
        }
        matrix[x][y] = 0;
        path.removeLast();
        return false;
    }

    public static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public Point() {}
    }
}
