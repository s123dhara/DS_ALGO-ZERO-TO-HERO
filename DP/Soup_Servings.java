import java.util.Arrays;

public class Soup_Servings {

    public int[][] pour = { { 4, 0 }, { 3, 1 }, { 2, 2 }, { 1, 3 } }; // scaled down by /25
    public double[][] dp;

    public double solve(int A, int B) {
        if (A <= 0 && B <= 0)
            return 0.5;
        if (A <= 0)
            return 1.0;
        if (B <= 0)
            return 0.0;

        if (dp[A][B] != -1.0)
            return dp[A][B];

        double prob = 0.0;
        for (int[] p : pour) {
            prob += solve(A - p[0], B - p[1]);
        }

        return dp[A][B] = 0.25 * prob;
    }

    public double soupServings(int n) {
        if (n >= 4800)
            return 1.0; // limit from math observation

        int m = (n + 24) / 25; // round up to nearest 25ml
        dp = new double[m + 1][m + 1];
        for (double[] arr : dp) {
            Arrays.fill(arr, -1.0);
        }
        return solve(m, m);
    }

    public static void main(String[] args) {
        Soup_Servings ss = new Soup_Servings();
        System.out.println(ss.soupServings(50));
    }
}