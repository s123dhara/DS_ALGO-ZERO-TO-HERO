public class Maximum_Number_Of_Fish_In_A_Grid {
    public static int m;
    public static int n;
    public static int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public static int solve(int[][] grid, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0) {
            return 0;
        }

        int fish = grid[i][j];
        grid[i][j] = 0; // Mark the cell as visited

        int maxFish = fish;
        for (int[] dir : directions) {
            int i_ = i + dir[0];
            int j_ = j + dir[1];
            maxFish += solve(grid, i_, j_);
        }

        return maxFish;
    }

    public static int findMaxFish(int[][] grid) {
        int result = 0;
        m = grid.length;
        n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    result = Math.max(result, solve(grid, i, j));
                }
            }
        }

        return result;

    }

    public static void main(String[] args) {
        int[][] grid = { { 0, 2, 1, 0 }, { 4, 0, 0, 3 }, { 1, 0, 0, 4 }, { 0, 3, 2, 0 } };
        System.out.println("OUTPUT : " + findMaxFish(grid));
    }
}
