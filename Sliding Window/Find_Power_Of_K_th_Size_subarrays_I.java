import java.util.Arrays;

public class Find_Power_Of_K_th_Size_subarrays_I {

    public static int[] resultsArray(int[] nums, int k) {
        int n = nums.length;

        int[] result = new int[n - k + 1];
        // Initialize the result array with -1
        Arrays.fill(result, -1);

        int count = 1; // Count of consecutive elements

        // Preprocess the first window
        for (int i = 1; i < k; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                count++;
            } else {
                count = 1;
            }
        }

        // Check if the first window satisfies the condition
        if (count == k) {
            result[0] = nums[k - 1];
        }

        int i = 1;
        int j = k;

        // Process the sliding window
        while (j < n) {
            if (nums[j] == nums[j - 1] + 1) {
                count++;
            } else {
                count = 1;
            }

            if (count >= k) {
                result[i] = nums[j];
            }

            i++;
            j++;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,3,2,5};
        int k = 3;

        int[] result = resultsArray(nums, k);
        System.out.println("OUTPUT : "+Arrays.toString(result));
    }
}