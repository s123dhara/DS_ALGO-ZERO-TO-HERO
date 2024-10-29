import java.util.Arrays;;
public class Maximum_Number_Of_moves_in_a_Grid {
    public static int n;
    public static int m;

    public static int t[][];

    public static int solve(int[][] grid, int i, int j, int step) {
        // Check if the current position is out of bounds or we have reached the right
        // boundary
        if (i < 0 || i >= n || j >= m) {
            return step;
        }

        if(t[i][j] != -1) return t[i][j];

        // Check moves in three directions: top-right, right, and bottom-right
        int maxSteps = step;

        if (i - 1 >= 0 && j + 1 < m && grid[i - 1][j + 1] > grid[i][j]) {
            maxSteps = Math.max(maxSteps, solve(grid, i - 1, j + 1, step + 1));
        }
        if (j + 1 < m && grid[i][j + 1] > grid[i][j]) {
            maxSteps = Math.max(maxSteps, solve(grid, i, j + 1, step + 1));
        }
        if (i + 1 < n && j + 1 < m && grid[i + 1][j + 1] > grid[i][j]) {
            maxSteps = Math.max(maxSteps, solve(grid, i + 1, j + 1, step + 1));
        }

        return t[i][j] = maxSteps;
    }

    public static int maxMoves(int[][] grid) {
        n = grid.length;
        m = grid[0].length;

        t = new int[n + 1][m + 1];
        for(int[] arr : t) Arrays.fill(arr, -1);

        int result = 0;

        // Start from each cell in the first column and calculate the max moves
        for (int i = 0; i < n; i++) {
            result = Math.max(result, solve(grid, i, 0, 0));
        }

        return result;
    }
    public static void main(String[] args) {
        int[][] grid = {
            {2, 4, 3, 5},
            {5, 4, 9, 3},
            {3, 4, 2, 11},
            {10, 9, 13, 15}
        };
        
        
        System.out.println("Maximum moves: " + maxMoves(grid));
    }
}
