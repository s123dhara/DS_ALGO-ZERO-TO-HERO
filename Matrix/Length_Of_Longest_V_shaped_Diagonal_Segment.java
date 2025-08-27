public class Length_Of_Longest_V_shaped_Diagonal_Segment {

    // directions: 4 diagonals
    int[][] directions = { { 1, 1 }, { 1, -1 }, { -1, -1 }, { -1, 1 } };
    int m, n;
    int[][][][] t; // memo table [i][j][direction][canTurn]

    private int solve(int i, int j, int d, int canTurn, int val, int[][] grid) {
        int i_ = i + directions[d][0];
        int j_ = j + directions[d][1];

        // Out of bounds or wrong value
        if (i_ < 0 || i_ >= m || j_ < 0 || j_ >= n || grid[i_][j_] != val) {
            return 0;
        }

        if (t[i_][j_][d][canTurn] != -1) {
            return t[i_][j_][d][canTurn];
        }

        int nextVal = (val == 2 ? 0 : 2);

        int result = 0;

        // keep moving straight
        int keepMoving = 1 + solve(i_, j_, d, canTurn, nextVal, grid);
        result = Math.max(result, keepMoving);

        // try turning once
        if (canTurn == 1) {
            int turnAndMove = 1 + solve(i_, j_, (d + 1) % 4, 0, nextVal, grid);
            result = Math.max(result, turnAndMove);
        }

        return t[i_][j_][d][canTurn] = result;
    }

    public int lenOfVDiagonal(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        t = new int[m][n][4][2]; // initialize memo table
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int d = 0; d < 4; d++) {
                    for (int c = 0; c < 2; c++) {
                        t[i][j][d][c] = -1;
                    }
                }
            }
        }

        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    for (int d = 0; d < 4; d++) {
                        result = Math.max(result, 1 + solve(i, j, d, 1, 2, grid));
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] grid = { { 2, 2, 1, 2, 2 }, { 2, 0, 2, 2, 0 }, { 2, 0, 1, 1, 0 }, { 1, 0, 2, 2, 2 },
                { 2, 0, 0, 2, 2 } };
        Length_Of_Longest_V_shaped_Diagonal_Segment obj = new Length_Of_Longest_V_shaped_Diagonal_Segment();
        int result = obj.lenOfVDiagonal(grid);
        System.out.println("Length of longest V-shaped diagonal segment: " + result);
    }
}
