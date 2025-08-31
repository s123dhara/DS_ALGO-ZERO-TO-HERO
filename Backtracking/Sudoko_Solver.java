import java.util.Arrays;
import java.util.HashSet;

public class Sudoko_Solver {

    // Helper: find box index (0..8) from (i, j)
    public int getBoxIndex(int i, int j) {
        return (i / 3) * 3 + (j / 3);
    }

    // Helper: move to next cell
    public int[] nextCell(int i, int j) {
        if (j == 8) {
            return new int[] { i + 1, 0 };
        } else {
            return new int[] { i, j + 1 };
        }
    }

    // Backtracking
    public boolean backtrack(char[][] board, int i, int j, HashSet<Character>[] rowSet, HashSet<Character>[] colSet,
            HashSet<Character>[] boxSet) {
        if (i == 9) {
            // Reached beyond last row -> solution found
            return true;
        }

        // Move to next cell
        int[] next = nextCell(i, j);
        int next_i = next[0];
        int next_j = next[1];

        // If already filled, go to next cell
        if (board[i][j] != '.') {
            return backtrack(board, next_i, next_j, rowSet, colSet, boxSet);
        }

        // Try numbers 1..9
        for (char num = '1'; num <= '9'; num++) {
            int boxIndex = getBoxIndex(i, j);
            if (!rowSet[i].contains(num) &&
                    !colSet[j].contains(num) &&
                    !boxSet[boxIndex].contains(num)) {

                // Place the number
                board[i][j] = num;
                rowSet[i].add(num);
                colSet[j].add(num);
                boxSet[boxIndex].add(num);

                // Recurse
                if (backtrack(board, next_i, next_j, rowSet, colSet, boxSet)) {
                    return true;
                }

                // Undo (backtrack)
                board[i][j] = '.';
                rowSet[i].remove(num);
                colSet[j].remove(num);
                boxSet[boxIndex].remove(num);
            }
        }

        // If no number works -> fail
        return false;
    }

    public void solveSudoku(char[][] board) {
        // Step 1: Initialize sets
        HashSet<Character>[] rowSet = new HashSet[9];
        HashSet<Character>[] colSet = new HashSet[9];
        HashSet<Character>[] boxSet = new HashSet[9];

        for (int i = 0; i < 9; i++) {
            rowSet[i] = new HashSet<>();
            colSet[i] = new HashSet<>();
            boxSet[i] = new HashSet<>();
        }

        // Step 2: Fill sets with existing board values
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    rowSet[i].add(board[i][j]);
                    colSet[j].add(board[i][j]);
                    int boxIndex = getBoxIndex(i, j);
                    boxSet[boxIndex].add(board[i][j]);
                }
            }
        }

        // Step 3: Backtrack from (0,0)
        backtrack(board, 0, 0, rowSet, colSet, boxSet);
    }

    public boolean isValid(char[][] board, int row, int col, char d) {
        // Row Validation
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == d)
                return false;
        }

        // Col Validation
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == d)
                return false;
        }

        // Box 3 X 3 validation
        // How get the box start_row and start_col index
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRow + i][startCol + j] == d) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean solve(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {

                    for (char d = '1'; d <= '9'; d++) {
                        if (isValid(board, i, j, d)) {
                            // Do part
                            board[i][j] = d;

                            if (solve(board)) {
                                return true;
                            }

                            // Undo part
                            board[i][j] = '.'; // Reset
                        }
                    }

                    return false; // No valid answer

                }
            }
        }

        return true; // sudoku solved
    }

    public void solveSudoku_2(char[][] board) {
        solve(board);
    }

    public static void main(String[] args) {
        char[][] board1 = {
                { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
                { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
                { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
                { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
        };

        char[][] board2 = {
                { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
                { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
                { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
                { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
        };

        char[][] board3 = {
                { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
                { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
                { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
                { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
        };

        Sudoko_Solver solver = new Sudoko_Solver();

        // --- Measure HashSet-based solver ---
        long start1 = System.nanoTime();
        solver.solveSudoku(board1);
        long end1 = System.nanoTime();
        System.out.println("Time for HashSet solver: " + (end1 - start1) / 1_000_000.0 + " ms");

        // --- Measure isValid-based solver ---
        long start2 = System.nanoTime();
        solver.solveSudoku_2(board2);
        long end2 = System.nanoTime();
        System.out.println("Time for isValid solver: " + (end2 - start2) / 1_000_000.0 + " ms");

        // --- Measure isValid-based solver ---
        long start3 = System.nanoTime();
        new Sudoko_Solver_InnerClass().solveSudoku(board3);
        long end3 = System.nanoTime();
        System.out.println("Time for isValid solver: " + (end3 - start3) / 1_000_000.0 + " ms");

        // Print solved board from one solver
        System.out.println("Solved Sudoku:");
        for (char[] row : board1) {
            System.out.println(Arrays.toString(row));
        }
    }

}

class Sudoko_Solver_InnerClass {

    // boolean trackers
    private boolean[][] row = new boolean[9][10]; // row[i][d] = true if digit d used in row i
    private boolean[][] col = new boolean[9][10]; // col[j][d] = true if digit d used in col j
    private boolean[][] box = new boolean[9][10]; // box[k][d] = true if digit d used in box k

    public void solveSudoku(char[][] board) {
        // Step 1: Initialize boolean arrays with existing numbers
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int d = board[i][j] - '0';
                    row[i][d] = true;
                    col[j][d] = true;
                    int boxIndex = (i / 3) * 3 + (j / 3);
                    box[boxIndex][d] = true;
                }
            }
        }

        // Step 2: Start solving
        solve(board, 0, 0);
    }

    private boolean solve(char[][] board, int i, int j) {
        // if reached end → solved
        if (i == 9)
            return true;

        // get next cell
        int nextI = (j == 8) ? i + 1 : i;
        int nextJ = (j == 8) ? 0 : j + 1;

        // if filled → move on
        if (board[i][j] != '.') {
            return solve(board, nextI, nextJ);
        }

        int boxIndex = (i / 3) * 3 + (j / 3);

        // try digits 1–9
        for (int d = 1; d <= 9; d++) {
            if (!row[i][d] && !col[j][d] && !box[boxIndex][d]) {
                // place digit
                board[i][j] = (char) ('0' + d);
                row[i][d] = col[j][d] = box[boxIndex][d] = true;

                // recurse
                if (solve(board, nextI, nextJ))
                    return true;

                // undo
                board[i][j] = '.';
                row[i][d] = col[j][d] = box[boxIndex][d] = false;
            }
        }

        return false; // no solution for this path
    }
}
