import java.util.*;

public class Longest_Harmonious_Subsequence {

    private int getFilterResult(int[] nums, int left, int right, int min, int max) {
        int filteredResult = 0;
        for (int i = left; i <= right; i++) {
            if (nums[i] >= min && nums[i] <= max) {
                filteredResult++;
            }
        }

        return filteredResult;
    }

    public int findLHS(int[] nums) {
        int n = nums.length;
        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], new int[] { i, i });
            }

            map.get(nums[i])[1] = i;
        }

        // for (int t : map.keySet()) {
        // System.out.println("key = " + t);
        // System.out.println("value = " + Arrays.toString(map.get(t)));
        // }

        int result = 0;

        for (int key : map.keySet()) {
            int[] arr = map.get(key);

            int firstOcc = arr[0];
            int lastOcc = arr[1];

            int min = key - 1;
            int max = key + 1;

            // Get Minimum Element First and Last Occurance
            int currentLength = 0;
            if (map.containsKey(min)) {
                int minFirstOcc = map.get(min)[0];
                int minLastOcc = map.get(min)[1];

                int left = Math.min(firstOcc, minFirstOcc);
                int right = Math.max(lastOcc, minLastOcc);

                int length = getFilterResult(nums, left, right, min, key);
                currentLength = Math.max(length, currentLength);
            }

            if (map.containsKey(max)) {
                int maxFirstOcc = map.get(max)[0];
                int maxLastOcc = map.get(max)[1];

                int left = Math.min(firstOcc, maxFirstOcc);
                int right = Math.max(lastOcc, maxLastOcc);

                int length = getFilterResult(nums, left, right, key, max);
                currentLength = Math.max(length, currentLength);
            }

            result = Math.max(currentLength, result);

        }

        return result;

    }

    public int ImprovedfindLHS(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], new int[] { i, i });
            }

            map.get(nums[i])[1] = i;
        }

        int result = 0;

        for (int key : map.keySet()) {
            int[] arr = map.get(key);

            int firstOcc = arr[0];
            int lastOcc = arr[1];

            int min = key - 1;
            int max = key + 1;

            // Get Minimum Element First and Last Occurance
            int currentLength = 0;
            if (map.containsKey(min)) {
                int minFirstOcc = map.get(min)[0];
                int minLastOcc = map.get(min)[1];

                int left = Math.min(firstOcc, minFirstOcc);
                int right = Math.max(lastOcc, minLastOcc);

                int length = right - left + 1;
                currentLength = Math.max(length, currentLength);
            }

            if (map.containsKey(max)) {
                int maxFirstOcc = map.get(max)[0];
                int maxLastOcc = map.get(max)[1];

                int left = Math.min(firstOcc, maxFirstOcc);
                int right = Math.max(lastOcc, maxLastOcc);

                int length = right - left + 1;
                currentLength = Math.max(length, currentLength);
            }

            result = Math.max(currentLength, result);

        }
        return result;

    }

    public int Improved_2_findLHS(int[] nums) {
        int n = nums.length;

        if (n == 0)
            return 0;

        // Count the Frequency, Ultimately Frequency Matters!
        Map<Integer, Integer> map = new HashMap<>();
        int result = 0;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<Integer> values = new ArrayList<>(map.values());
        values.sort((a, b) -> a - b); // or simply Collections.sort(values);
        System.out.println(values);

        /*
         * for (int key : map.keySet()) {
         * int value = map.get(key);
         * 
         * int min = key - 1;
         * int max = key + 1;
         * 
         * if (map.containsKey(min)) {
         * result = Math.max(result, value + map.get(min));
         * }
         * 
         * if (map.containsKey(max)) {
         * result = Math.max(result, value + map.get(max));
         * }
         * 
         * }
         */

        for (int key : map.keySet()) {
            if (map.containsKey(key + 1))
                result = Math.max(result, map.get(key) + map.get(key + 1));
        }

        return result;

    }

    public int Improved_3_findLHS(int[] nums) {
        // Sliding Window Approach
        int n = nums.length;
        Arrays.sort(nums);

        int l = 0;
        int r = 0;

        int result = 0;

        while(r < n) {
            int diff = nums[r] - nums[l];
            if(diff == 1) result = Math.max(result, r - l + 1);

            if(diff <= 1) r++;
            else l++;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 3, 2, 2, 5, 2, 3, 7 };
        System.out.println("OUTPUT : " + new Longest_Harmonious_Subsequence().Improved_3_findLHS(nums));
    }
}
