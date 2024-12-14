import java.util.PriorityQueue;

public class Continuous_Subarrays {

    public static long continuousSubarrays(int[] nums) {
        int n = nums.length;
        long result = 0;

        int i = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));

        for (int j = 0; j < n; j++) {
            minHeap.offer(nums[j]);
            maxHeap.offer(nums[j]);

            // Shrink window if constraint is violated
            while (maxHeap.peek() - minHeap.peek() > 2) {
                minHeap.remove(nums[i]);
                maxHeap.remove(nums[i]);
                i++;
            }

            // Add the number of valid subarrays ending at index j
            result += (j - i + 1);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {5, 4, 2, 4};
        System.out.println("OUTPUT : " + continuousSubarrays(nums));
    }
}
