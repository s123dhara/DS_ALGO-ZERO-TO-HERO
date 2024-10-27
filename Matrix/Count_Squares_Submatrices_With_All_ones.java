public class Count_Squares_Submatrices_With_All_ones {

    public static boolean isSquareOfOnes(int[][] matrix, int r, int c, int sq) {
        for (int i = 0; i < sq; i++) {
            for (int j = 0; j < sq; j++) {
                if (matrix[i + r][j + c] == 0) {
                    return false; // Found a zero, not a valid square submatrix
                }
            }
        }
        return true; // All elements are ones in the square matrix
    }

    public static int countSquares(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int count = 0;

        // Count 1x1 submatrices directly
        for (int[] arr : matrix) {
            for (int num : arr) {
                if (num == 1) {
                    count += 1;
                }
            }
        }

        int minSq = Math.min(n, m);

        // Count larger square submatrices
        for (int sq = 2; sq <= minSq; sq++) {
            for (int i = 0; i <= n - sq; i++) {
                for (int j = 0; j <= m - sq; j++) {
                    if (isSquareOfOnes(matrix, i, j, sq)) {
                        count += 1;
                    }
                }
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
