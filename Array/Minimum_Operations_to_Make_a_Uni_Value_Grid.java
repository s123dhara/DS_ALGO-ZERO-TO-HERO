import java.util.*;

public class Minimum_Operations_to_Make_a_Uni_Value_Grid {
    public static int minOperations(int[][] grid, int x) {
        int m = grid.length;
        int n = grid[0].length;

        int arr[] = new int[m * n];
        int idx = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[idx++] = grid[i][j];
            }
        }

        Arrays.sort(arr);
        int result = 0;
        int length = arr.length;

        if (length % 2 == 0) {
            // Two elements
            int mid = arr[length / 2];
            int next = arr[(length / 2) - 1];

            int cou1 = 0;
            int cou2 = 0;
            int v;
            for (int i = 0; i < length; i++) {
                System.out.println("current arr[i] = " + arr[i]);
                System.out.println("for MID = " + mid);
                if (arr[i] == mid) {
                    cou1 += 0;
                }
                v = Math.abs(arr[i] - mid);
                System.out.println("v = " + v);
                if (v % x != 0) {
                    return -1;
                }
                cou1 += v / x;
                System.out.println("COU1 = " + cou1);

                System.out.println("for NEXT = " + next);
                if (arr[i] == next) {
                    cou2 += 0;
                }
                v = Math.abs(arr[i] - next);
                System.out.println("v = " + v);
                if (v % x != 0) {
                    return -1;
                }
                cou2 += v / x;
                System.out.println("COU2 = " + cou2);
            }
            return Math.min(cou1, cou2);
        } else {
            int mid = arr[length / 2];
            int cou1 = 0;
            for (int i = 0; i < length; i++) {
                if (arr[i] <= mid) {
                    cou1 += Math.abs(arr[i] - mid);
                } else {
                    int v = Math.abs(arr[i] - mid);
                    if (v % x != 0) {
                        return -1;
                    }
                    cou1 += v / x;
                }
            }
            return cou1;
        }
    }

    public static void main(String[] args) {
        int[][] grid = {
                { 2, 4 },
                { 6, 8 }
                // {1,5},
                // {2,3}
        };
        int x = 2;

        System.out.println("OUTPUT : " + minOperations(grid, x));
    }
}
