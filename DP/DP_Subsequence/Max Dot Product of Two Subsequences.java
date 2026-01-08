class Solution {
    int[] nums1, nums2;
    Integer[][] memo;
    static final int NEG_INF = Integer.MIN_VALUE / 2;

    public int maxDotProduct(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        memo = new Integer[nums1.length][nums2.length];
        return dfs(0, 0);
    }

    private int dfs(int i, int j) {
        if (i == nums1.length || j == nums2.length)
            return NEG_INF;

        if (memo[i][j] != null)
            return memo[i][j];

        int take = nums1[i] * nums2[j] + Math.max(0, dfs(i + 1, j + 1));
        int skip1 = dfs(i + 1, j);
        int skip2 = dfs(i, j + 1);

        return memo[i][j] = Math.max(take, Math.max(skip1, skip2));
    }
}
