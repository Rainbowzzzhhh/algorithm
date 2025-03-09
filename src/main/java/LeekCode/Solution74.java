package LeekCode;

/**
 * @author rainbow
 * @time 2025-03-07 11:05
 * @description ...
 */
public class Solution74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rowIndex = binarySearchFirstColumn(matrix, target);
        if (rowIndex < 0) {
            return false;
        }
        return binarySearchRow(matrix[rowIndex], target);
    }

    public int binarySearchFirstColumn(int[][] matrix, int target) {
        /*
        将 low 初始化为-1，旨在覆盖目标值可能小于所有行首元素的情况
        当 low 和 high 相等时终止
        mid = (high - low + 1) / 2 + low
            这种计算方式确保在剩余偶数个元素时，中间值偏向右侧，防止死循环。
            在剩余奇数个元素时，仍然为中间的元素
        若 target >= matrix[mid][0]，说明目标值可能在当前行或后续行，调整 low = mid 以继续搜索右侧。
        否则，目标值只能在前面的行，调整 high = mid - 1 缩小搜索范围到左侧。
        TODO 一定要跟着搜索的过程来确定条件的判断逻辑
         */
        int low = -1, high = matrix.length - 1;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (matrix[mid][0] <= target) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public boolean binarySearchRow(int[] row, int target) {
        /*
        先找等于，再找其他
         */
        int low = 0, high = row.length - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            if (row[mid] == target) {
                return true;
            } else if (row[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println((3 + 10) >> 1);
        System.out.println(((10 - 3) >> 1) + 3);
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};

        boolean b = new Solution74().searchMatrix(matrix, 3);
        System.out.println(b);
    }
}
