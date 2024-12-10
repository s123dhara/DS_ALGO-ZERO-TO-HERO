import java.util.Arrays;
public class Special_Array_II {

    public static boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int n = queries.length;
        boolean[] ans = new boolean[n];

        int[] cumulativeSum = new int[nums.length];

        // Build the cumulative sum array
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] % 2 == nums[i - 1] % 2) {
                cumulativeSum[i] = cumulativeSum[i - 1] + 1;
            }else {
                cumulativeSum[i] = cumulativeSum[i - 1];
            }
        }

        // Process each query
        int index = 0;
        for (int[] query : queries) {
            int start = query[0];
            int end = query[1];

            // Check if there are no transitions in the subarray
            if (cumulativeSum[end] - cumulativeSum[start] == 0) {
                ans[index] = true;
            } else {
                ans[index] = false;
            }
            index++;
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = { 3, 4, 1, 2, 6 };
        int[][] queries = { { 0, 4 } };

        boolean[] result = isArraySpecial(nums, queries);

        // Print the result
        System.out.println(Arrays.toString(result));
    }
}
