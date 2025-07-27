class Solution {
    public int countHillValley(int[] nums) {
        int n = nums.length;        
        int hill = 0;
        int valley = 0;

        for (int i = 1; i < n - 1; i++) {

            // Skip if current value equals previous value
            if (nums[i] == nums[i - 1]) {
                continue;
            }

            // Find the closest previous non-equal neighbor
            int left = i - 1;
            while (left >= 0 && nums[left] == nums[i]) {
                left--;
            }

            // Find the closest next non-equal neighbor
            int right = i + 1;
            while (right < n && nums[right] == nums[i]) {
                right++;
            }

            // If both neighbors are valid, compare them
            if (left >= 0 && right < n) {
                if (nums[i] > nums[left] && nums[i] > nums[right]) {
                    hill++; // hill
                } else if (nums[i] < nums[left] && nums[i] < nums[right]) {
                    valley++; // valley
                }
            }
        }

        return hill + valley;
    }
}
