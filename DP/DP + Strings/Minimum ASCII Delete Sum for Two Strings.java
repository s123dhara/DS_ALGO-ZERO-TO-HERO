class Solution {
    // String s1;
    // String s2;
    // Integer[][] dp;

    // public int findAscii(String s, int start) {
    //     int cost = 0;
    //     for (int i = start; i < s.length(); i++) {
    //         cost += s.charAt(i);
    //     }
    //     return cost;
    // }

    // public int solve(int i, int j) {
    //     // base cases
    //     if (i == s1.length()) {
    //         return findAscii(s2, j);
    //     }
    //     if (j == s2.length()) {
    //         return findAscii(s1, i);
    //     }

    //     if (dp[i][j] != null)
    //         return dp[i][j];

    //     int ans;
    //     if (s1.charAt(i) == s2.charAt(j)) {
    //         ans = solve(i + 1, j + 1);
    //     } else {
    //         int deleteS1 = s1.charAt(i) + solve(i + 1, j);
    //         int deleteS2 = s2.charAt(j) + solve(i, j + 1);
    //         ans = Math.min(deleteS1, deleteS2);
    //     }

    //     return dp[i][j] = ans;
    // }

    public int minimumDeleteSum(String s1, String s2) {
        // this.s1 = s1;
        // this.s2 = s2;
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n + 1][m + 1];

        // base cases
        for (int i = n - 1; i >= 0; i--) {
            dp[i][m] = s1.charAt(i) + dp[i + 1][m];
        }

        for (int j = m - 1; j >= 0; j--) {
            dp[n][j] = s2.charAt(j) + dp[n][j + 1];
        }

        // fill table bottom-up
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    int d1 = s1.charAt(i) + dp[i + 1][j];
                    int d2 = s2.charAt(j) + dp[i][j + 1];
                    dp[i][j] = Math.min(d1, d2);
                }
            }
        }

        return dp[0][0];

        // return solve(0, 0);
    }
}
