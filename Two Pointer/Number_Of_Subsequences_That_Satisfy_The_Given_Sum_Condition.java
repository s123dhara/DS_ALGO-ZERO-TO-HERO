import java.util.*;

public class Number_Of_Subsequences_That_Satisfy_The_Given_Sum_Condition {
    private long result;
    private long MOD = 1_000_000_007;

    public void solve(int[] nums, int target, int index, PriorityQueue<Integer> minHeap,
            PriorityQueue<Integer> maxHeap) {

        if (index == nums.length) {
            if (minHeap.size() > 0 && minHeap.peek() + maxHeap.peek() <= target) {
                // System.out.println(currList);
                result++;
                result %= MOD;
            }
            return;
        }

        // Do
        minHeap.offer(nums[index]);
        maxHeap.offer(nums[index]);

        // Explore
        solve(nums, target, index + 1, minHeap, maxHeap);

        // Undo
        minHeap.remove(nums[index]);
        maxHeap.remove(nums[index]);

        // Explore
        solve(nums, target, index + 1, minHeap, maxHeap);
    }

    public int count = 0;

    public void generateAllSubsequences(int[] nums, int target, int index, List<Integer> currList) {
        if (index == nums.length) {
            if (currList.size() > 0) {
                count++;
                System.out.println(currList);
            }
            return;
        }

        // Do
        currList.add(nums[index]);

        // Explore
        generateAllSubsequences(nums, target, index + 1, currList);

        // Undo
        currList.remove(currList.size() - 1);

        // Explore
        // Exclude nums[currIndex]
        generateAllSubsequences(nums, target, index + 1, currList);
    }

    public int numSubseq_test(int[] nums, int target) {
        result = 0;
        // solve(nums, target, 0, new PriorityQueue<>(), new PriorityQueue<>(Collections.reverseOrder()));

        generateAllSubsequences(nums, target, 0, new ArrayList<>());
        System.out.println("count = " + count);
        return (int) result;
    }

    public int numSubseq(int[] nums, int target) {
        result = 0;
        int n = nums.length;
        Arrays.sort(nums);
        int left = 0;
        int right = n - 1;

        // Precomute the value of Power because it exceed the range of Integer, may be Some points it get the Wrong Answer! 
        
        long[] power = new long[n];
        power[0] = 1;
        for (int i = 1; i < n; i++) {
            power[i] = (power[i - 1] * 2) % MOD;
        }

        while (left <= right) {
            if (nums[left] + nums[right] <= target) {
                int diff = right - left;
                result = (result + power[diff]) % MOD;
                left++;
            } else {
                right--;
            }
        }

        return (int) result;
    }

    public static void main(String[] args) {
        // int[] nums = { 3, 5, 6, 7 };
        // int target = 9;

        // int[] nums = { 7,10,7,3,7,5,4 };
        // int target = 12;

        int[] nums = { 14, 4, 6, 6, 20, 8, 5, 6, 8, 12, 6, 10, 14, 9, 17, 16, 9, 7, 14, 11, 14, 15, 13, 11, 10, 18, 13,
                17, 17, 14, 17, 7, 9, 5, 10, 13, 8, 5, 18, 20, 7, 5, 5, 15, 19, 14 };
        int target = 22;

        // new Number_Of_Subsequences_That_Satisfy_The_Given_Sum_Condition().solve(nums,
        // target, 0, new ArrayList<>());
        System.out.println("OUTPUT :  "
                + new Number_Of_Subsequences_That_Satisfy_The_Given_Sum_Condition().numSubseq(nums, target));
    }
}
