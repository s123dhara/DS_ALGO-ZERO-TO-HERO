import java.util.Arrays;

public class Count_Squares_Submatrices_With_All_ones_DP_DFS {

    public static int[][] t;

    public static int solve(int[][] matrix ,int i, int j) {
        if(i >= matrix.length || j >= matrix[0].length || matrix[i][j] == 0) {
            return 0;
        }

        if(t[i][j] != -1) return t[i][j];

        // Right
        int right = solve(matrix, i, j + 1);
        // Diagonal
        int diagonal = solve(matrix, i + 1, j + 1);
        // Below
        int below = solve(matrix, i + 1, j);

        return t[i][j] = 1 + Math.min(right, Math.min(diagonal, below)); // 1 + min(right, below, diagonal) , 1 is added because 
        // every matrix element 1 X 1 is 1 its Square Matrix
    }
   

    public static int countSquares(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        t = new int[n + 1][m + 1];
        for(int[] arr : t) Arrays.fill(arr, -1);

        int count = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                count += solve(matrix, i, j);
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {0, 1, 1, 1},
            {1, 1, 1, 1},
            {0, 1, 1, 1}
        };

        // Display the matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println(); // Newline after each row
        }

        System.out.println("OUTPUT : " + countSquares(matrix));
    }
}
