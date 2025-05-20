import java.util.*;

public class Zero_Array_Transformation_I {

    public static boolean isZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        // Form Difference Array
        int[] differenceArray = new int[n];

        for (int[] query : queries) {
            int start = query[0];
            int end = query[1];

            differenceArray[start] += 1;
            if (end + 1 < n) {
                differenceArray[end + 1] -= 1;
            }
        }
        

        // Step-2 : Form Cummulative Array
        int[] cummulativeArray = new int[n];
        cummulativeArray[0] = differenceArray[0];
        for (int i = 1; i < n; i++) {
            cummulativeArray[i] = cummulativeArray[i - 1] + differenceArray[i];
        }

        

        // Step 3: Check if each value can reach 0
        for (int i = 0; i < n; i++) {
            if (cummulativeArray[i] < nums[i]) {
                return false; // nums[i] won't become 0
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[] nums = { 4, 3, 2, 1 };
        int[][] queries = { { 1, 3 }, { 0, 2 } };

        // for (int[] query : queries) {
        //     int s = query[0];
        //     int e = query[1];

        //     for (int i = s; i <= e; i++) {
        //         nums[i] -= 1;
        //     }

        //     System.out.println(Arrays.toString(nums));
        // }

        System.out.println("OUTPUT : " + isZeroArray(nums, queries));
    }
}