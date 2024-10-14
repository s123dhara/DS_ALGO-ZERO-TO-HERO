import java.util.PriorityQueue;
/**
 * Maximal_Score_After_Applying_K_operations
 */
public class Maximal_Score_After_Applying_K_operations {

    public static long maxKelements(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a); // Max-heap

        int n = nums.length;
        long ans = 0;

        for (int i = 0; i < n; i++) {
            pq.offer(nums[i]);
        }

        while (!pq.isEmpty() && k > 0) {
            int curr = pq.poll();
            System.out.println("curr "+curr);
            ans += curr;
            // pq.offer( (curr % 3 != 0) ? (curr / 3 + 1) : (curr / 3) );
            pq.offer(Math.ceilDiv(curr, 3));
            k--;
            
        }

        return ans;

    }

    public static void main(String[] args) {
        int[] nums = { 1, 10, 3, 3, 3 };
        int k = 3;

        System.out.println("OUPTUT : " + maxKelements(nums, k));
    }
}