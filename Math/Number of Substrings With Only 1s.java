class Solution {
    public int numSub(String s) {
        long mod = 1_000_000_007L;
        s = s + "0"; // still allowed
        int n = s.length();

        long result = 0; // use long to avoid overflow
        long consecutiveOnes = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                consecutiveOnes++;
            } else {
                long count = consecutiveOnes;
                long formula = (count * (count + 1) / 2) % mod;
                result = (result + formula) % mod; // safe addition
                consecutiveOnes = 0;
            }
        }

        return (int) result; // final cast
    }
}
