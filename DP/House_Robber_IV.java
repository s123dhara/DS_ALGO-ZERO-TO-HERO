import java.util.Arrays;

public class House_Robber_IV {
    /*
    public static int solve(int[] nums, int index, int k, int[][] dp) {
        if(k == 0) {
            return 0;
        }

        if(index >= nums.length) {
            if(k == 0) {
                return 0;
            }
            return Integer.MAX_VALUE;
        }

        if(dp[index][k] != -1) {
            return dp[index][k];
        }

        int take = Math.max(nums[index], solve(nums, index + 2, k - 1, dp));
        int notTake = solve(nums, index + 1, k, dp);

        return dp[index][k] = Math.min(take, notTake);
    }

    public static int minCapability(int[] nums, int k) {
        int n = nums.length;
        int dp[][] = new int[n + 1][k + 1];
        for(int[] arr : dp)
            Arrays.fill(arr, -1);
        return solve(nums, 0, k, dp);
    }
    */

    //Binary Seach on Answer
    private static boolean isPossible(int[] nums, int mid, int k) {
        int house = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= mid) {
                house++;
                i++; // skipping adjacent house
            }
        }
        
        return house >= k; // we managed to rob at least k houses
    }
    
    public static int minCapability(int[] nums, int k) {
        int n = nums.length;
        
        int l = Arrays.stream(nums).min().getAsInt();
        int r = Arrays.stream(nums).max().getAsInt();
        
        int result = r;
        
        while (l <= r) { // O(log(maxC) * n )
            int mid = l + (r - l) / 2; // capability
            
            if (isPossible(nums, mid, k)) {
                result = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        
        return result;
    }

    public static void main(String[] args) {
        int[] nums = { 2, 3, 5, 9 };
        int k = 2;        
        System.out.println("OUPUT : " + minCapability(nums, k));
    }
}
