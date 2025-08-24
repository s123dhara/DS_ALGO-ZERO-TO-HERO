class Solution {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int result = 0;

        int ones = 0;
        int zeros = 0;

        int i = 0;
        int j = 0;

        while (j < n) {
            if (nums[j] == 0) {
                zeros++;
            } else {
                ones++;
            }

            while (i < n && zeros > 1) {
                if (nums[i] == 0) {
                    zeros--;
                } else {
                    ones--;
                }
                i++;
            }

            result = Math.max(result, ones);
            j++;
        }

        return (zeros == 0) ? result - 1 : result;
    }
}