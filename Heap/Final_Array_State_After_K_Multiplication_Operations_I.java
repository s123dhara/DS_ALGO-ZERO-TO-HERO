import java.util.Arrays;
import java.util.PriorityQueue;

public class Final_Array_State_After_K_Multiplication_Operations_I {

    public static int[] getFinalState2(int[] arr, int k, int multiplier) {
        while (k > 0) {
            int x = 0;
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] < arr[x]) {
                    x = i;
                }
            }
            arr[x] = arr[x] * multiplier;
            k--;
        }
        return arr;
    }

    public static int[] getFinalState(int[] nums, int k, int multiplier) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0])
                return a[1] - b[1];
            return a[0] - b[0];
        });

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            pq.offer(new int[] { nums[i], i });
        }

        while (!pq.isEmpty() && k > 0) {
            int[] curr = pq.poll();
            int i = curr[1];
            nums[i] = curr[0] = curr[0] * multiplier;
            k--;
            pq.offer(curr);
        }

        return nums;
    }

    public static void main(String[] args) {
        int nums[] = { 2, 1, 3, 5, 6 };
        int k = 5;
        int multiplier = 2;

        System.out.println("OUTPUT : " + Arrays.toString(getFinalState2(nums, k, multiplier)));
    }
}