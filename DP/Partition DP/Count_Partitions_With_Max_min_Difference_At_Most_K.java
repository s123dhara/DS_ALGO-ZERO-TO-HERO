
import java.util.*;
/**
 * this problem actually need more thinking 
 * it include some topics like 
 *      1. Parition for DP 
 *      2. Two pointer for Valid Window ( min max difference at most k) 
 *      3. Valid window checking(impotant part) : monotic queue ( deque) or multiset 
 *      4. counting parition with DP + prefix sum 
 */

public class Count_Partitions_With_Max_min_Difference_At_Most_K {

    public int countPartitions(int[] nums, int k) {
        int n = nums.length;
        int mod = 1000000007;
        int[] dp = new int[n + 1];
        int[] prefix = new int[n + 1];
        dp[0] = 1;
        prefix[0] = 1;

        ArrayDeque<Integer> maxDeque = new ArrayDeque<>();
        ArrayDeque<Integer> minDeque = new ArrayDeque<>();

        int i = 0;
        int j = 0; 

        while (j < n) {
            // Max elemnent in the window
            while(!maxDeque.isEmpty() && nums[maxDeque.peekLast()] <= nums[j]) { 
                maxDeque.pollLast();
            }

            // Min element in the window
            while(!minDeque.isEmpty() && nums[minDeque.peekLast()] >= nums[j]) {
                minDeque.pollLast();
            }

            // this confirm that we have valid window from i to j
            maxDeque.offerLast(j);
            minDeque.offerLast(j);

            // then we need to move i pointer  until the window become valid
            while(i <= j && nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] > k) {
                i++;

                if(!maxDeque.isEmpty() && maxDeque.peekFirst() < i ) {
                    maxDeque.pollFirst();
                }
                if(!minDeque.isEmpty() && minDeque.peekFirst() < i ) {
                    minDeque.pollFirst();
                }
            }

            // now we have valid window from i to j
            // we can update our dp and prefix array
            if(i - 1 >= 0) {
                dp[j + 1] = prefix[j] - prefix[i - 1];
            }
            else {
                dp[j + 1] = prefix[j];
            }

            dp[j + 1] = (dp[j + 1] + mod) % mod;
            prefix[j + 1] = (prefix[j] + dp[j + 1]) % mod;
            j++;

        }
        // dp[i] means number ways to create partition from 0 to i-1
        return dp[n];
    }

    public static void main(String[] args) {
        int[] nums = {9,4,1,3,7};
        int k = 4;

        System.out.println("OUTPUT : " + new Count_Partitions_With_Max_min_Difference_At_Most_K().countPartitions(nums, k));
        
    }
    
}