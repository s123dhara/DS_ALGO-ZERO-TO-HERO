import java.util.*;

public class Count_Good_Triplets_in_an_Array {

    public static void updateSegmentTree(int i, int l, int r, int idx, long[] segmentTree) {
        if (l == r) {
            segmentTree[i] = 1; // mark visited
            return;
        }

        int mid = (l + r) / 2;
        // int mid = l + (r - 1) / 2;
        if (idx <= mid) {
            updateSegmentTree(2 * i + 1, l, mid, idx, segmentTree);
        } else {
            updateSegmentTree(2 * i + 2, mid + 1, r, idx, segmentTree);
        }

        segmentTree[i] = segmentTree[2 * i + 1] + segmentTree[2 * i + 2];
    }

    public static long querySegmentTree(int queryStart, int queryEnd, int i, int l, int r, long[] segmentTree) {

        if (r < queryStart || l > queryEnd) {
            return 0;
        }

        if (l >= queryStart && r <= queryEnd) {
            return segmentTree[i];
        }

        // int mid = l + (r - 1) / 2;
        int mid = (l + r) / 2;

        long left = querySegmentTree(queryStart, queryEnd, 2 * i + 1, l, mid, segmentTree);
        long right = querySegmentTree(queryStart, queryEnd, 2 * i + 2, mid + 1, r, segmentTree);

        return left + right;

    }

    public static long goodTriplets(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums1.length;

        for (int i = 0; i < n; i++) {
            map.put(nums2[i], i);
        }

        // Segment tree
        long[] segmentTree = new long[4 * n];
        long result = 0;

        updateSegmentTree(0, 0, n - 1, map.get(nums1[0]), segmentTree);

        for (int i = 1; i < n; i++) {
            int idx = map.get(nums1[i]);

            long leftCommonCount = querySegmentTree(0, idx, 0, 0, n - 1, segmentTree);
            long leftUnCommonCount = i - leftCommonCount;
            long elementsAfterIdxNums2 = n - 1 - idx;
            long rightCommonCount = elementsAfterIdxNums2 - leftUnCommonCount;

            result += leftCommonCount * rightCommonCount;

            updateSegmentTree(0, 0, n - 1, idx, segmentTree);
        }

        return result;

    }

    public static void main(String[] args) {
        int[] nums1 = { 4, 0, 1, 3, 2 };
        int[] nums2 = { 4, 1, 0, 2, 3 };

        System.out.println("OUTPUT : " + goodTriplets(nums1, nums2));
    }
}