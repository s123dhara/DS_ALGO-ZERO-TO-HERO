
// this approach is DP with MEMO, but it is not optimal, it is O(n^2)
// this is not optimal because we are checking the same subsequence multiple times
class Solution {
    int n;
    Map<String, Integer> memo = new HashMap<>();

    private int solve(String s, int k, int i) {
        if (i < 0) return 0;

        String key = i + "," + k;
        if (memo.containsKey(key)) return memo.get(key);

        int take = 0;
        int bit = s.charAt(i) - '0';
        long value = (bit == 1 ? (1L << (n - i - 1)) : 0);

        if (value <= k) {
            take = 1 + solve(s, (int)(k - value), i - 1);
        }

        int skip = solve(s, k, i - 1);
        int result = Math.max(take, skip);
        memo.put(key, result);

        return result;
    }

    public int longestSubsequence(String s, int k) {
        n = s.length();
        return solve(s, k, n - 1);
    }
}


// this is optimal and O(n) time and O(1) space
// this is Greedy approach only taking '0' bit to consider
class Solution {
    public int longestSubsequence(String s, int k) {
        int length = 0;
        long powerValue = 1;

        for (int i = s.length() - 1; i >= 0; --i) {
            if (s.charAt(i) == '0') {
                length++;
            } else if (powerValue <= k) {
                length++;
                k -= powerValue;
            }

            if (powerValue <= k) {
                powerValue <<= 1; // powerValue *= 2
            }
        }

        return length;
    }
}