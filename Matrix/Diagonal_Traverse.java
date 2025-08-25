public class Diagonal_Traverse {
    public int[] result;
    public int index = 0;

    public int currRow = 0;
    public int currCol = 0;

    public void upRight(int row, int col, int m, int n, int[][] mat) {
        if (row < 0 || col >= n)
            return;

        currRow = row;
        currCol = col;
        result[index++] = mat[row][col];
        upRight(row - 1, col + 1, m, n, mat);
    }

    public void downLeft(int row, int col, int m, int n, int[][] mat) {
        if (row >= m || col < 0)
            return;

        currRow = row;
        currCol = col;
        result[index++] = mat[row][col];
        downLeft(row + 1, col - 1, m, n, mat);
    }

    public int[] findDiagonalOrder(int[][] mat) {
        int row = mat.length;
        int col = mat[0].length;
        result = new int[row * col];

        int startRow = 0;
        int startCol = 0;
        boolean up = true;

        while (index < row * col) {
            if (up) {
                upRight(startRow, startCol, row, col, mat);
                up = false;

                if (currCol + 1 < col) {
                    startCol = currCol + 1;
                    startRow = currRow;
                } else if (currRow + 1 < row) {
                    startRow = currRow + 1;
                    startCol = currCol;
                }
            } else {
                downLeft(startRow, startCol, row, col, mat);
                up = true;

                if (currRow + 1 < row) {
                    startRow = currRow + 1;
                    startCol = currCol;
                } else if (currCol + 1 < col) {
                    startCol = currCol + 1;
                    startRow = currRow;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Diagonal_Traverse obj = new Diagonal_Traverse();
        int[][] mat = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        int[] result = obj.findDiagonalOrder(mat);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}
