class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        int n = nums.length;
        int i = 0, j = 0;
        int score = 0, maxScore = 0;
        
        Set<Integer> set = new HashSet<>();
        
        while (j < n) {
            if (!set.contains(nums[j])) {
                set.add(nums[j]);
                score += nums[j];
                maxScore = Math.max(maxScore, score);
                j++;
            } else {
                set.remove(nums[i]);
                score -= nums[i];
                i++;
            }
        }
        
        return maxScore;
    }
}