import java.util.Arrays;
import java.util.HashSet;

public class Minimum_Cost_For_Tickets {
    public static int[] dp;

    /* Top-Down : Recursion + Memo */
    public static int solve(int[] days, int[] costs, int i) {
        if (i >= days.length) {
            return 0;
        }

        if (dp[i] != -1)
            return dp[i];

        // 1st Day pass
        int cost_1 = costs[0] + solve(days, costs, i + 1);

        // 7th day pass
        int j = i;
        int max_day = days[i] + 7;

        while (j < days.length && days[j] < max_day) {
            j++;
        }

        int cost_7 = costs[1] + solve(days, costs, j);

        // 30th day pass
        j = i;
        max_day = days[i] + 30;

        while (j < days.length && days[j] < max_day) {
            j++;
        }

        int cost_30 = costs[2] + solve(days, costs, j);

        return dp[i] = Math.min(cost_30, Math.min(cost_1, cost_7));
    }

    public static int mincostTickets(int[] days, int[] costs) {
        dp = new int[days.length + 1];
        Arrays.fill(dp, -1);
        return solve(days, costs, 0);

    }

    /* Bottom Up : 1-D array  */
    public static int mincostTickets2(int[] days, int[] costs) {
        /* t[i] = min cost to reach till day 'i' */
        int n = days.length;
        int size = days[n - 1];

        //Declare the State 1-D Array
        int[] t = new int[size + 1]; 
        t[0] = 0;
        HashSet<Integer> set = new HashSet<>();
        for(int num : days) set.add(num);

        for(int i = 1; i <= size; i++) {
            if(!set.contains(i)) {
                t[i] = t[i - 1];
                // skip this day because i do not have to travel
                continue;
            }

            t[i] = Integer.MAX_VALUE;
            int day_1_pass = costs[0] + t[Math.max(i - 1, 0)];
            int day_7_pass = costs[1] + t[Math.max(i - 7, 0)];
            int day_30_pass = costs[2] + t[Math.max(i - 30, 0)];

            t[i] = Math.min(day_30_pass, Math.min(day_1_pass, day_7_pass));
        }


        return t[size];
    }

    public static void main(String[] args) {
        int[] days = { 1, 4, 6, 7, 8, 20 };
        int[] costs = { 2, 7, 15 };

        System.out.println("output : " + mincostTickets2(days, costs));
    }
}