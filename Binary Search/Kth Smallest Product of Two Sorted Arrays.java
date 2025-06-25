class Solution {
    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        long low = -10000000000L, high = 10000000000L;

        while (low < high) {
            long mid = low + (high - low) / 2;
            long count = countPairsLessEqual(nums1, nums2, mid);

            if (count < k) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    private long countPairsLessEqual(int[] nums1, int[] nums2, long x) {
        long count = 0;
        for (int a : nums1) {
            count += countSmallerEqual(nums2, a, x);
        }
        return count;
    }

    private long countSmallerEqual(int[] nums, int a, long x) {
        int n = nums.length;
        long count = 0;

        if (a > 0) {
            // Need count of nums[j] <= x / a
            int l = 0, r = n;
            while (l < r) {
                int m = (l + r) / 2;
                if ((long)a * nums[m] <= x) {
                    l = m + 1;
                } else {
                    r = m;
                }
            }
            count = l;
        } else if (a < 0) {
            // Need count of nums[j] >= ceil(x / a)
            int l = 0, r = n;
            while (l < r) {
                int m = (l + r) / 2;
                if ((long)a * nums[m] <= x) {
                    r = m;
                } else {
                    l = m + 1;
                }
            }
            count = n - l;
        } else {
            // a == 0
            if (x >= 0) count = nums.length;
        }

        return count;
    }
}
