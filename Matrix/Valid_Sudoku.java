import java.util.HashSet;

public class Valid_Sudoku {

    public boolean isValidSudoku(char[][] board) {
        // row check
        for (int row = 0; row < 9; row++) {
            // HashSet<Character> rowSet = new HashSet<>();
            boolean[] rowSet = new boolean[10];
            for (int col = 0; col < 9; col++) {
                // if (rowSet.contains(board[row][col])) {
                // return false;
                // } else if (board[row][col] != '.') {
                // rowSet.add(board[row][col]);
                // }
                if (board[row][col] == '.') {
                    continue;
                }   

                if (rowSet[board[row][col] - '0'] == true) {
                    return false;
                } else {
                    rowSet[board[row][col] - '0'] = true;
                }
            }
        }

        // col check
        for (int row = 0; row < 9; row++) {
            // HashSet<Character> colSet = new HashSet<>();
            boolean[] colSet = new boolean[10];
            for (int col = 0; col < 9; col++) {
                // if (colSet.contains(board[col][row])) {
                // return false;
                // } else if (board[col][row] != '.') {
                // colSet.add(board[col][row]);
                // }
                if (board[col][row] == '.') {
                    continue;
                }                
                if (colSet[board[col][row] - '0'] == true) {
                    return false;
                } else {
                    colSet[board[col][row] - '0'] = true;
                }
            }
        }

        // 3 X 3 boxes checking
        for (int sr = 0; sr < 9; sr += 3) {
            int er = sr + 2;
            for (int sc = 0; sc < 9; sc += 3) {
                int ec = sc + 2;
                // HashSet<Character> boxSet = new HashSet<>();
                boolean[] boxSet = new boolean[10];
                // Validation Checking for 3 X 3 boxes
                for (int i = sr; i <= er; i++) {
                    for (int j = sc; j <= ec; j++) {
                        // if (boxSet.contains(board[i][j])) {
                        // return false;
                        // } else if (board[i][j] != '.') {
                        // boxSet.add(board[i][j]);
                        // }
                        if (board[i][j] == '.') {
                            continue;
                        }

                        if (boxSet[board[i][j] - '0'] == true) {
                            return false;
                        } else {
                            boxSet[board[i][j] - '0'] = true;
                        }
                    }
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Valid_Sudoku validator = new Valid_Sudoku();
        char[][] board = {
                { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
                { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
                { '4', '.', '6', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
                { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
        };
        System.out.println(validator.isValidSudoku(board));
    }
}
