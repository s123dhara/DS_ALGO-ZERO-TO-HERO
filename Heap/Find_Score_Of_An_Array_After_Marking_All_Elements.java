import java.util.PriorityQueue;

public class Find_Score_Of_An_Array_After_Marking_All_Elements {

    public static class Pair {
        int num;
        int index;

        Pair(int num, int index) {
            this.num = num;
            this.index = index;
        }
    }

    public static long findScore(int[] nums) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
            if (a.num == b.num)
                return a.index - b.index;
            return a.num - b.num;
        }); // min-heap
        int n = nums.length;
        boolean[] visited = new boolean[n];
        long result = 0;

        for (int i = 0; i < n; i++) {
            pq.offer(new Pair(nums[i], i));
        }

        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            int i = curr.index;

            if (visited[i]) {
                continue;
            }

            result += curr.num;

            visited[i] = true;
            if (i > 0) {
                visited[i - 1] = true;
            }
            if (i < n - 1) {
                visited[i + 1] = true;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // int nums[] = {2, 1, 3, 4, 5, 2};
        int nums[] = { 2, 5, 6, 6, 10 };

        System.out.println("OUTPUT: " + findScore(nums)); // Expected OUTPUT: 7
    }
}
