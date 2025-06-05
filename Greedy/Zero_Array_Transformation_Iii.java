import java.util.*;

public class Zero_Array_Transformation_Iii {

    private int n;
    private int Q;

    private boolean checkWithDifferenceArrayTeq(int[] nums, int[][] queries, int k) {
        int[] diff = new int[n];

        //O(Q)
        for (int i = 0; i <= k; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            int x = 1;

            diff[l] += x;
            if (r + 1 < n)
                diff[r + 1] -= x;
        }

        int cumSum = 0;
        //O(n)
        for (int i = 0; i < n; i++) {
            cumSum += diff[i];
            diff[i] = cumSum;

            if (nums[i] - diff[i] > 0) { //nums[i] 0 nahi ban paya
                return false;
            }
        }

        return true;
    }


    public int maxRemoval(int[] nums, int[][] queries) {
        // PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
        //     if (a[1] - a[0] == b[1] - b[0]) {
        //         return a[0] - b[0]; // Tie-breaker: sort by a[0] in ascending order
        //     }
        //     return (b[1] - b[0]) - (a[1] - a[0]); // Primary: sort by (b[1] - b[0]) descending
        // });
        n = nums.length;
        Q = queries.length;

        Arrays.sort(queries, (a,b) -> (b[1] - b[0]) - (a[1] - a[0]));
        
        for(int i = 0; i < Q; i++) {
            if(checkWithDifferenceArrayTeq(nums, queries, i)) {
                return Q - i + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] nums = { 2, 0, 2 };
        int[][] queries = { { 0, 2 }, { 0, 2 }, { 1, 1 }};

        Zero_Array_Transformation_Iii obj = new Zero_Array_Transformation_Iii();

        System.out.println("OUTPUT : " + obj.maxRemoval(nums, queries));
    }

}
