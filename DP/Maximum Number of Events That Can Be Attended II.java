class Solution {
    public int[][] t;
    public int solve(int[][] events, int i, int k) {    
        if(i == events.length || k == 0) {
            return 0;
        }

        if(t[i][k] != -1) {
            return t[i][k];
        }

        int start = events[i][0];
        int end = events[i][1];
        int v1 = events[i][2];

        int notTake = solve(events, i + 1, k);

        // Take 
        // int j = i + 1;
        /**
            Only This below For - Loop is taking O(N) time that's why .. 
            T.C goes to O(NLogN) + O(N^2 * N)

            How to reduce this? That's why Binary Search is Good. Already we got Sorted Array. 
         */
        /* 
        for(j = i + 1; j < events.length; j++) {
            if(events[j][0] > events[i][1]) {
                break;
            }
        }
        */
        int j = binarySearch(events, end);
        int take = v1 + solve(events, j, k - 1); // j means that Event I took

        return t[i][k] = Math.max(take, notTake);

    }
    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, (a,b) -> a[0] - b[0]);

        t = new int[events.length + 1][k + 1];
        for(int[] arr : t) {
            Arrays.fill(arr, - 1);
        }

        return solve(events, 0, k);
    }

    private int binarySearch(int[][] events, int endTime) {
        int low = 0, high = events.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (events[mid][0] > endTime)
                high = mid;
            else
                low = mid + 1;
        }
        return low;
    }
}