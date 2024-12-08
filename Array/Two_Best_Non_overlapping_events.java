import java.util.Arrays;

public class Two_Best_Non_overlapping_events {
    public static int n;
    public static int[][] dp;

    // Binary search for the next event start time greater than the current event's
    // end time
    private static int binarySearch(int[][] events, int endTime) {
        int left = 0, right = n - 1, result = n;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (events[mid][0] > endTime) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    public static int solve(int[][] events, int i, int count) {
        if (count == 2 || i >= n) {
            return 0;
        }

        if (dp[i][count] != -1) {
            return dp[i][count];
        }

        int nextValidEventIndex = binarySearch(events, events[i][1]);
        int take = events[i][2] + solve(events, nextValidEventIndex, count + 1);

        int notTake = solve(events, i + 1, count);

        return dp[i][count] = Math.max(take, notTake);

    }

    public static int maxTwoEvents(int[][] events) {
        n = events.length;
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));
    
        if (n == 1) {
            return events[0][2];
        }
        dp = new int[n + 1][n + 1];

        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }

        return solve(events, 0, 0);

    }

    public static void main(String[] args) {
        int[][] events = { { 1, 3, 2 }, { 4, 5, 2 }, { 2, 4, 3 } }; // Initialize the 2D array
        System.out.println(maxTwoEvents(events));
    }
}