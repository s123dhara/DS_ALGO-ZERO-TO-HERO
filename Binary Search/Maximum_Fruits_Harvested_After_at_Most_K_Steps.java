import java.util.Arrays;
import java.util.Comparator;

public class Maximum_Fruits_Harvested_After_at_Most_K_Steps {

    private int lowerBound(int[] positions, int target) {
        int left = 0;
        int right = positions.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (positions[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private int upperBound(int[] positions, int target) {
        int left = 0, right = positions.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (positions[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        // Sort by position (just to be safe)
        Arrays.sort(fruits, Comparator.comparingInt(a -> a[0]));

        int n = fruits.length;
        int[] preSum = new int[n + 1];
        int[] positions = new int[n];

        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + fruits[i][1];
            positions[i] = fruits[i][0];
        }

        int maxFruits = 0;

        for (int d = 0; d <= k / 2; d++) {
            // Case 1: go d left, then right
            int i = startPos - d;
            int j = startPos + (k - 2 * d);

            int left = lowerBound(positions, i);
            int right = upperBound(positions, j) - 1;
            if (left <= right) {
                int total = preSum[right + 1] - preSum[left];
                maxFruits = Math.max(maxFruits, total);
            }

            // Case 2: go d right, then left
            i = startPos - (k - 2 * d);
            j = startPos + d;

            left = lowerBound(positions, i);
            right = upperBound(positions, j) - 1;
            if (left <= right) {
                int total = preSum[right + 1] - preSum[left];
                maxFruits = Math.max(maxFruits, total);
            }
        }

        return maxFruits;
    }

    public static void main(String[] args) {
        int[][] fruits = { { 2, 8 }, { 6, 3 }, { 8, 6 } };
        int startPos = 5;
        int k = 4;

        System.out.println("OUTPUT :  "
                + new Maximum_Fruits_Harvested_After_at_Most_K_Steps().maxTotalFruits(fruits, startPos, k));
    }
}
