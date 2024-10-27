public class Count_Squares_Submatrices_With_All_ones_DP_BOTTOMUP {

    public static int countSquares(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int[][] t = new int[n + 1][m + 1];
    
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (i == 0 || j == 0) {
                    t[i][j] = matrix[i][j];

                } else {

                    if (matrix[i][j] == 1) {
                        /* current State : 1 + Math.min(left, diagonal, up) top-down Opposite */
                        t[i][j] = 1 + Math.min(t[i][j - 1], Math.min(t[i - 1][j], t[i - 1][j - 1]));
                    }
                }
                count += t[i][j];

            }
        }

        return count;

    }

    public static void main(String[] args) {
        int[][] matrix = {
                { 0, 1, 1, 1 },
                { 1, 1, 1, 1 },
                { 0, 1, 1, 1 }
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
