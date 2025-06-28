import java.util.*;

public class Find_Subsequence_Of_Length_K_With_The_Largest_Sum {

    public int[] maxSubsequence(int[] nums, int k) {

        int n = nums.length;
        int[] result = new int[k];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]); // Min-heap
        PriorityQueue<int[]> pq2 = new PriorityQueue<>((a, b) -> a[1] - b[1]); // Min-heap

        for (int i = 0; i < n; i++) {
            pq.add(new int[] { nums[i], i });

            if (pq.size() > k) {
                pq.poll();
            }
        }

        int idx = 0;
        pq2.addAll(pq);
        while (!pq2.isEmpty())
            result[idx++] = pq2.poll()[0];

        return result;
    }

    public static void main(String[] args) {
        int[] nums = { -1, -2, 3, 4 };
        // int[] nums = { -35 };
        int k = 3;

        int[] result = new Find_Subsequence_Of_Length_K_With_The_Largest_Sum().maxSubsequence(nums, k);

        System.out.println("OUTPUT : " + Arrays.toString(result));

    }
}
/*
class Solution {
    private List<Integer> result;
    private int largestSum;

    public boolean solve(int[] nums, int k, int currIndex, int currSum,
            List<Integer> currList) {

        if (currList.size() > k) {
            return false;
        }

        if (currIndex == nums.length) {
            if (currList.size() == k && currSum > largestSum) {
                result.clear();
                result.addAll(currList);
                largestSum = currSum;
            }
            return false;
        }

        // Include nums[currIndex]
        // Do
        currList.add(nums[currIndex]);

        // Explore
        solve(nums, k, currIndex + 1, currSum + nums[currIndex], currList);

        // Undo
        currList.remove(currList.size() - 1);

        // Explore
        // Exclude nums[currIndex]
        solve(nums, k, currIndex + 1, currSum, currList);

        return false; // Not returning based on success, just completing all possibilities
    }

    public int[] maxSubsequence(int[] nums, int k) {
        result = new ArrayList<>();
        largestSum = Integer.MIN_VALUE;

        solve(nums, k, 0, 0, new ArrayList<>());
        int[] ans = new int[result.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = result.get(i);
        }
        return ans;
    }
}
 */