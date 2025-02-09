import java.util.Arrays;

public class Maximum_Ascending_Subarray_Sum {
    public static int maxAscendingSum(int[] nums) {
        int n = nums.length;
        int currSum = 0;
        int maxSum = 0;
        if (n == 1)
            return nums[0];
        int[] arr = new int[n];
        arr[0] = nums[0];
        maxSum = Math.max(maxSum, arr[0]);

        for (int i = 1; i < n; i++) {
            if (nums[i - 1] < nums[i]) {
                arr[i] = arr[i - 1] + nums[i];
            } else {
                arr[i] = nums[i];
            }
            System.out.println(Arrays.toString(arr));
            maxSum = Math.max(maxSum, arr[i]);
            System.out.println("maxsum = "  + maxSum);
        }
        System.out.println(Arrays.toString(arr));
        // for (int i = 0; i < n; i++) {
        // maxSum = Math.max(maxSum, arr[i]);
        // }
        return maxSum;
    }

    public static void main(String[] args) {
        // int[] nums = { 10, 20, 30, 5, 10, 50 };
        int[] nums = { 100, 10, 1 };
        System.out.println("OUTPUT : " + maxAscendingSum(nums));
    }
}
