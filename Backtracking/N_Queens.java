import java.util.ArrayList;
import java.util.List;

public class N_Queens {
    public static List<List<String>> nQueens;
    public static int N;
    public static int[][] board;

    public static void addSolution(int[][] board) {
        List<String> eachSolution = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < N; j++) {
                if(board[i][j] == 1) {
                    sb.append('Q');
                }else {
                    sb.append('.');
                }
            }

            eachSolution.add(sb.toString());
        }

        nQueens.add(eachSolution);
    }

    public static void solveQueen(int col) {
        if(col == N) {
            // got a solution 
            addSolution(board);
        }

        // start the program here 
        for (int i = 0; i < N; i++) {
            if (isSafe(i, col)) {
                board[i][col] = 1; // Place the queen

                solveQueen(col + 1); // Recursively place the rest of the queens

                board[i][col] = 0; // Backtrack
            }
        }
    }

    public static boolean isSafe(int row, int col) {
        // check row on the left 
        for(int i = 0; i < col; i++) {
            if(board[row][i] == 1) {
                return false;
            }
        }

        // Check upper diagonal on the left
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        // Check lower diagonal on the left
        for (int i = row, j = col; i < N && j >= 0; i++, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true; // No conflicts, position is safe

    }

    public static List<List<String>> solveNQueens(int n) {
        N = n;
        nQueens = new ArrayList<>();
        board = new int[N][N];
        

        solveQueen(0);

        return nQueens;
    }
    
    public static void main(String[] args) {
        int n = 1;

        List<List<String>> result = new ArrayList<>();
        result = solveNQueens(n);

        int solutionsCount = 0;
        for(List<String> list : result) {
            System.out.println("Solution count : "+ ++solutionsCount);
            System.out.println(list);
        }
    }
}