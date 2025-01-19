import java.util.PriorityQueue;

public class Trapping_Water_II {
    public static int[][] dir = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

    public static int trapRainWater(int[][] heightMap) {
        int totalWaterTrap = 0;
        int m = heightMap.length;
        int n = heightMap[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        boolean[][] visited = new boolean[m][n];
        // Step 1: Push all Boundary cells in Min-heap

        // Push all boundary elements into the heap

        // Push the first and last rows
        for (int j = 0; j < n; j++) {
            // Top row
            pq.offer(new int[] { heightMap[0][j], 0, j });
            visited[0][j] = true;

            // Bottom row
            pq.offer(new int[] { heightMap[m - 1][j], m - 1, j });
            visited[m - 1][j] = true;
        }

        // Push the first and last columns (excluding corners to avoid duplicates)
        for (int i = 1; i < m - 1; i++) {
            // Left column
            pq.offer(new int[] { heightMap[i][0], i, 0 });
            visited[i][0] = true;

            // Right column
            pq.offer(new int[] { heightMap[i][n - 1], i, n - 1 });
            visited[i][n - 1] = true;
        }

        while (!pq.isEmpty()) {
            int[] currPair = pq.poll();
            int currHeight = currPair[0];
            int x = currPair[1];
            int y = currPair[2];
            // check valid Boundary and Explore the neighbour
            for (int[] d : dir) {
                int x_ = x + d[0];
                int y_ = y + d[1];

                if (x_ >= 0 && x_ < m && y_ >= 0 && y_ < n && !visited[x_][y_]) {
                    totalWaterTrap += Math.max(currHeight - heightMap[x_][y_], 0);
                    pq.offer(new int[] { Math.max(currHeight, heightMap[x_][y_]), x_, y_ });

                    visited[x_][y_] = true;
                }
            }
        }

        return totalWaterTrap;
    }

    public static void main(String[] args) {
        int[][] heightMap = { { 1, 4, 3, 1, 3, 2 }, { 3, 2, 1, 3, 2, 4 }, { 2, 3, 3, 2, 3, 1 } };
        // Call the trapRainWater method and print the result
        int result = trapRainWater(heightMap);
        System.out.println("Trapped rainwater: " + result);
    }
}