class Solution {
    public boolean rowWiseSum(int[][] grid, int row, int col) {

        for (int i = 0; i < 3; i++) {
            int currSum = 0;
            for (int j = 0; j < 3; j++) {
                currSum += grid[row + i][col + j];
            }

            if (currSum != 15) {
                return false;
            }
        }

        return true;
    }

    public boolean colWiseSum(int[][] grid, int row, int col) {

        for (int i = 0; i < 3; i++) {
            int currSum = 0;
            for (int j = 0; j < 3; j++) {
                currSum += grid[row + j][col + i];
            }

            if (currSum != 15) {
                return false;
            }
        }

        return true;
    }

    public boolean leftDiagonalWiseSum(int[][] grid, int row, int col) {
        int currSum = 0;

        for (int i = 0; i < 3; i++) {
            currSum += grid[row + i][col + i];
        }

        return currSum == 15;
    }

    public boolean rightDiagonalWiseSum(int[][] grid, int row, int col) {
        int currSum = 0;

        for (int i = 0; i < 3; i++) {
            currSum += grid[row + i][col + 2 - i];
        }

        return currSum == 15;
    }

    public boolean isValidSums(int[][] grid, int row, int col) {
        return rowWiseSum(grid, row, col) && colWiseSum(grid, row, col) && leftDiagonalWiseSum(grid, row, col) && rightDiagonalWiseSum(grid, row, col);
    }

    public boolean isAllUnique(int[][] grid, int row, int col) {
        int[] freq = new int[10];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                int x = grid[i + row][j + col];
                if(x < 1 || x > 9) {
                    return false;
                }

		if(freq[x] > 1) return false;
	
                freq[x]++;
            }
        }

        return true;
    }

    public int numMagicSquaresInside(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int result = 0;

        for (int row = 0; row <= rows - 3; row++) {
            for (int col = 0; col <= cols - 3; col++) {

                if (grid[row + 1][col + 1] != 5) continue;

                if(isAllUnique(grid, row, col) && isValidSums(grid, row, col)) {
                    result++;
                }
            }
        }

        return result;
    }
}