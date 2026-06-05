import java.util.Arrays;

public class Jump_Game_V {


    int[] dp;
    public int solve(int[] nums, int i, int d) {
        // Base case: If the index is out of bounds, return 0
        if (i < 0 || i >= nums.length) {
            return 0;
        }

        if(dp[i] != -1) {
            return dp[i];
        }

        // why maxJumps = 1 ?
        // Because we can always jump to the current index itself, 
        // so we start with 1 as the minimum number of jumps.
        int maxJumps = 1; 

        // Check jumps to the left
        for(int x = 1; x <= d; x++) {
            
            // Out of Bound check
            if (i - x < 0) {
                break;
            }

            // check Left jump get blocked by higher or equal value
            if (nums[i - x] >= nums[i]) {
                break;
            }

            maxJumps = Math.max(maxJumps, 1 + solve(nums, i - x, d));
        }

        // Check jumps to the right
        for(int x = 1; x <= d; x++) {
            
            // Out of Bound check
            if (i + x >= nums.length) {
                break;
            }

            // check right jump get blocked by higher or equal value
            if (nums[i + x] >= nums[i]) {
                break;
            }

            maxJumps = Math.max(maxJumps, 1 + solve(nums, i + x, d));
        }
        
        return dp[i] = maxJumps;
    }


    public int maxJumps(int[] arr, int d) {

        // Base case: If the array is empty, return 0
        if (arr == null || arr.length == 0) {
            return 0;
        }

        this.dp = new int[arr.length];
        Arrays.fill(dp, -1);

        int maxJumps = 0;
        for (int i = 0; i < arr.length; i++) {
            maxJumps = Math.max(maxJumps, solve(arr, i, d));
        }

        return maxJumps;

    }

    public static void main(String[] args) {
        int[] arr = {6,4,14,6,8,13,9,7,10,6,12};
        int d = 2;

        System.out.println("OUTPUT :  " + new Jump_Game_V().maxJumps(arr, d));
    }
}
