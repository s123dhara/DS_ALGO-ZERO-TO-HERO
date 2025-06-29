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

    public int numSubseq(int[] nums, int target) {
        result = 0;
        // solve(nums, target, 0, new PriorityQueue<>(), new
        // PriorityQueue<>(Collections.reverseOrder()));

        generateAllSubsequences(nums, target, 0, new ArrayList<>());
        System.out.println("count = " + count);
        return (int) result;
    }

    public static void main(String[] args) {
        int[] nums = { 3, 5, 6, 7 };
        int target = 9;

        // int[] nums = { 7,10,7,3,7,5,4 };
        // int target = 12;

        // new Number_Of_Subsequences_That_Satisfy_The_Given_Sum_Condition().solve(nums,
        // target, 0, new ArrayList<>());
        System.out.println("OUTPUT :  "
                + new Number_Of_Subsequences_That_Satisfy_The_Given_Sum_Condition().numSubseq(nums, target));
    }
}
