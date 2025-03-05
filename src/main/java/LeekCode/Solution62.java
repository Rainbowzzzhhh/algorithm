package LeekCode;

/**
 * @author rainbow
 * @time 2025-03-05 09:17
 * @description ...
 */
public class Solution62 {
    public int uniquePaths(int m, int n) {
        int[][] pathsToThisPlace = new int[m][n];

        for (int i = 0; i < n; i++) {
            pathsToThisPlace[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            pathsToThisPlace[i][0] = 1;
        }

        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                pathsToThisPlace[row][col] = pathsToThisPlace[row - 1][col] + pathsToThisPlace[row][col - 1];
            }
        }

        return pathsToThisPlace[m - 1][n - 1];
    }
}
