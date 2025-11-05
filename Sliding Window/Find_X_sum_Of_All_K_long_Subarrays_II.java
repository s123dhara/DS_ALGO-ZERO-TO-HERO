import java.util.*;

public class Find_X_sum_Of_All_K_long_Subarrays_II {
    long currSum = 0;
    public long computeXSum(Map<Integer, Integer> map, int x) {
        long sum = 0;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> {
            if (a[1] != b[1]) {
                return a[1] - b[1];
            } else {
                return a[0] - b[0];
            }
        });
        // System.out.println(map);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            minHeap.offer(new int[] { entry.getKey(), entry.getValue() });
            sum += (long) entry.getKey() * (long) entry.getValue();
            if (minHeap.size() > x) {
                sum -= (long) minHeap.peek()[0] * (long) minHeap.peek()[1];;
                minHeap.poll();
            }
        }

        // while (!minHeap.isEmpty()) {
        //     int[] poll = minHeap.poll();
        //     System.out.println("Arrays : " + Arrays.toString(poll));
        //     sum += (long) poll[0] * (long) poll[1];
        // }
        // System.out.println("----------------------------");

        return sum;

    }

    public long[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        long[] result = new long[n - k + 1];

        int i = 0;
        int j = 0;
        currSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int idx = 0;
        int size = 0;

        while (j < n) {
            currSum += (long) nums[j];
            map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
            size++;
            // Window Logic
            if (size == k && i < n - k + 1) {
                result[idx] = computeXSum(map, x);

                // decrement the count of Map and remove if count is 0
                int v = map.get(nums[i]);
                map.put(nums[i], v - 1);
                if (map.get(nums[i]) == 0) {
                    map.remove(nums[i]);
                }

                // slide left pointer
                i++;
                currSum -= (long) nums[i - 1];
                size--;
                idx++;

            }
            j++;

        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 1, 2, 2, 3, 4, 2, 3 };
        int k = 6;
        int x = 2;

        long[] result = new Find_X_sum_Of_All_K_long_Subarrays_II().findXSum(nums, k, x);
        System.out.println("OUTPUT : " + Arrays.toString(result));
    }
}