import java.util.*;

class Minimum_Cost_To_Make_At_Least_One_Valid_Path_In_A_Grid {

    // Dijsktra Algorithm
    /*
     * //Approach-2 (Dijkstra's Algorithm) - Accepted
     * //T.C : O((m*n) * log(m*n))
     * //S.C : O(m*n)
     */

    private static final int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

    public static int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0])); // Min-heap
        int[][] result = new int[m][n];

        // Initialize result array with maximum values
        for (int[] row : result) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        pq.offer(new int[] { 0, 0, 0 }); // {cost, i, j}
        result[0][0] = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll(); // Remove the top element (min cost)
            int currCost = curr[0];
            int i = curr[1];
            int j = curr[2];

            // // Skip if the current cost is greater than the recorded cost
            // if (result[i][j] < currCost) {
            // continue;
            // }

            // Explore all 4 directions
            for (int dirIndex = 0; dirIndex < 4; dirIndex++) {
                int i_ = i + dir[dirIndex][0];
                int j_ = j + dir[dirIndex][1];

                if (i_ >= 0 && j_ >= 0 && i_ < m && j_ < n) {
                    int gridDir = grid[i][j];
                    int dirCost = (gridDir - 1 != dirIndex) ? 1 : 0; // Calculate direction cost

                    int newCost = currCost + dirCost;

                    // Update result array and push to priority queue if new cost is lower
                    if (newCost < result[i_][j_]) {
                        result[i_][j_] = newCost;
                        pq.offer(new int[] { newCost, i_, j_ });
                    }
                }
            }
        }

        return result[m - 1][n - 1]; // Minimum cost to reach (m-1, n-1)
    }

    public static void main(String[] args) {
        int[][] grid = {
                { 1, 1, 1, 1 },
                { 2, 2, 2, 2 },
                { 1, 1, 1, 1 },
                { 2, 2, 2, 2 }
        };

        System.out.println("Minimum cost: " + minCost(grid));
    }
}
