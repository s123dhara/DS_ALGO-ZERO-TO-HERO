import java.util.Arrays;

public class Count_Unguarded_Cells_In_The_Grid {
    public static int vistedCell;
    public static void helper(int[][] grid, int r, int c) {
        // up direciton
        for (int i = r - 1; i >= 0; i--) {
            if(grid[i][c] == 1) {
                // means visited cell , skip this 
                continue;
            }
            if ((grid[i][c] == 2 || grid[i][c] == 3)) {
                break;
            } else {
                grid[i][c] = 1; // mark for visted
                vistedCell++;
            }
        }

        // left direction
        for (int j = c - 1; j >= 0; j--) {
            if(grid[r][j] == 1) {
                // means visited cell , skip this 
                continue;
            }
            if (grid[r][j] == 2 || grid[r][j] == 3) {
                break;
            } else {
                grid[r][j] = 1; // mark for visted
                vistedCell++;
            }
        }

        // down direction
        for (int i = r + 1; i < grid.length; i++) {
            if(grid[i][c] == 1) {
                // means visited cell , skip this 
                continue;
            }
            if (grid[i][c] == 2 || grid[i][c] == 3) {
                break;
            } else {
                grid[i][c] = 1; // mark for visted
                vistedCell++;
            }
        }

        // right direction
        for (int j = c + 1; j < grid[0].length; j++) {
            if(grid[r][j] == 1) {
                // means visited cell , skip this 
                continue;
            }
            if (grid[r][j] == 2 || grid[r][j] == 3) {
                break;
            } else {
                grid[r][j] = 1; // mark for visted
                vistedCell++;
            }
        }
    }

    public static int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] grid = new int[m][n];
        for (int[] arr : grid) {
            Arrays.fill(arr, -1); // -1 represent the unvisted cell
        }

        // guard for == 2
        // walls for == 3
        vistedCell = 0; // Set vistedCell count is Zero
        int totalGuards = guards.length;
        int totalWalls = walls.length;


        for (int guard[] : guards) {
            int r = guard[0];
            int c = guard[1];

            grid[r][c] = 2;
        }

        for (int wall[] : walls) {
            int r = wall[0];
            int c = wall[1];

            grid[r][c] = 3;
        }

        for (int[] arr : grid) {
            System.out.println(Arrays.toString(arr));
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == 1 || grid[i][j] == 3) {
                    // means visited cell , skip this 
                    continue;
                }
                if (grid[i][j] == 2) { // if not visted by any guard then check
                    // means this cell is guard
                    // from here traverse every direction
                    helper(grid, i, j);
                }
            }
        }

        int count = (m * n) - (vistedCell + totalGuards + totalWalls); // count the unvisted cell
        
        return count;

    }

    public static void main(String[] args) {
        int m = 4, n = 6; // Grid dimensions
        int[][] guards = { { 0, 0 }, { 1, 1 }, { 2, 3 } }; // Guard positions
        int[][] walls = { { 0, 1 }, { 2, 2 }, { 1, 4 } }; // Wall positions

        System.out.println("OUTPUT : " + countUnguarded(m, n, guards, walls));
    }
}

/*
 * class Solution {
    public static void helper(int[][] grid, int r, int c) {
        //up direciton 
        for(int i = r - 1; i >= 0; i--) {
            if(grid[i][c] == 2 || grid[i][c] == 3) {
                break;
            }else {
                grid[i][c] =  1; // mark for visted 
            }
        }

        //left direction 
        for(int j = c - 1; j >= 0; j--) {
            if(grid[r][j] == 2 || grid[r][j] == 3) {
                break;
            }else {
                grid[r][j] =  1; // mark for visted 
            }
        }

        //down direction 
        for(int i = r + 1; i < grid.length; i++) {
            if(grid[i][c] == 2 || grid[i][c] == 3) {
                break;
            }else {
                grid[i][c] =  1; // mark for visted 
            }
        }

        //right direction
        for(int j = c + 1; j < grid[0].length; j++) {
            if(grid[r][j] == 2 || grid[r][j] == 3) {
                break;
            }else {
                grid[r][j] =  1; // mark for visted 
            }
        }
    }

    public static int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] grid = new int[m][n];
        for(int[] arr : grid) {
            Arrays.fill(arr, -1); // -1 represent the unvisted cell
        }


        //guard for == 2
        //walls for == 3

        for(int guard[] : guards) {
            int r = guard[0];
            int c = guard[1];

            grid[r][c] = 2;
        }

        for(int wall[] : walls) {
            int r = wall[0];
            int c = wall[1];

            grid[r][c] = 3;
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 2 && grid[i][j] != -1) { // if not visted by any guard then check 
                    // means this cell is guard 
                    // from here traverse every direction 
                    helper(grid, i, j);
                }
            }
        }

        int count = 0; // count the unvisted cell
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == -1) { 
                    count++;
                }
            }
        }

        return count;

    }
}
 */