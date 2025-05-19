class Solution {    
    public int findLengthOfLCIS(int[] nums) {
        if (nums.length == 0) return 0;

        int maxLen = 1;
        int currLen = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                currLen++;
                maxLen = Math.max(maxLen, currLen);
            } else {
                currLen = 1;
            }
        }

        return maxLen;
    }
}


// Another O(n^2) process
class Solution {    
    public int findLengthOfLCIS(int[] nums) {
        int n = nums.length;
        int result = 0;
        for(int i = 0; i < n; i++) {
            int count = 1;
            for(int j = i + 1; j < n; j++) {
                if(nums[j - 1] < nums[j]) {
                    count++;
                    continue;
                }else {
                    break;
                }
            }

            result = Math.max(result, count);
        }

        return result;
    }
}