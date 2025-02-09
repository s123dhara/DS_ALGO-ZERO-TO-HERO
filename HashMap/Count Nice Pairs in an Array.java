class Solution {
    public static int rev(int n) {
        int r = 0;
        while (n != 0) {
            r = r * 10 + (n % 10);
            n /= 10;
        }
        return r;
    }

    public int countNicePairs(int[] nums) {
        int n = nums.length;
        long mod = 1_000_000_007;
        Map<Integer, Integer> map = new HashMap<>();
        long result = 0;

        for (int i = 0; i < n; i++) {
            int diff = nums[i] - rev(nums[i]);            
            result = (result + map.getOrDefault(diff, 0)) % mod;            
            map.put(diff, map.getOrDefault(diff, 0) + 1);
        }

        return (int) result;
    }
}
