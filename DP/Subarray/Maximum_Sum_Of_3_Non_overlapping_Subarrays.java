import java.util.Arrays;

public class Maximum_Sum_Of_3_Non_overlapping_Subarrays {
    public static int[] subarraySum;
    public static int[] result;
    public static int idx;

    // Memoziation
    public static int[][] dp; // variable {count, i};

    // Only Use Memoziation in Helper Function
    public static int helper(int[] nums, int count, int i, int k) {
        if (count == 0)
            return 0;
        if (i >= nums.length)
            return Integer.MIN_VALUE;

        if (dp[count][i] != -1) {
            return dp[count][i];
        }

        int take = subarraySum[i] + helper(nums, count - 1, i + k, k);
        int notTake = helper(nums, count, i + 1, k);

        return dp[count][i] = Math.max(take, notTake);
    }

    public static void solve(int[] nums, int count, int i, int k) {
        if (count == 0) {
            return;
        }

        if (i >= nums.length) {
            return;
        }

        int take_i = subarraySum[i] + helper(nums, count - 1, i + k, k);
        int notTake_i = helper(nums, count, i + 1, k);

        if (take_i >= notTake_i) {
            result[idx++] = i;
            solve(nums, count - 1, i + k, k);
        } else {
            solve(nums, count, i + 1, k);
        }

    }

    public static int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        subarraySum = new int[n];
        result = new int[3];
        idx = 0;

        // Memoziation
        dp = new int[3 + 1][n + 1];
        for (int arr[] : dp) {
            Arrays.fill(arr, -1);
        }

        int i = 0;
        int j = 0;
        int windowSum = 0;

        while (j < n) {
            windowSum += nums[j];

            if (j - i + 1 == k) {
                subarraySum[i] = windowSum;
                windowSum -= nums[i];
                i++;
            }
            j++;
        }

        solve(nums, 3, 0, k);

        return result;

    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 1, 2, 6, 7, 5, 1 };
        int k = 2;

        int[] result = maxSumOfThreeSubarrays(nums, k);
        System.out.println("OUPUT : " + Arrays.toString(result));
    }
}