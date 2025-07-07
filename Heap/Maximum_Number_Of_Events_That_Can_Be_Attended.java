import java.util.*;

public class Maximum_Number_Of_Events_That_Can_Be_Attended {
    public int maxEvents(int[][] events) {
        // Sort events by start time
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));

        // Min-heap to store events by end time
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int day = 1, count = 0, i = 0, n = events.length;

        while (i < n || !pq.isEmpty()) {
            // Add all events that start today or earlier
            while (i < n && events[i][0] <= day) {
                pq.offer(events[i][1]); // push end time
                i++;
            }

            // Remove expired events
            while (!pq.isEmpty() && pq.peek() < day) {
                pq.poll();
            }

            // Attend one event today if possible
            if (!pq.isEmpty()) {
                pq.poll(); // attend event with earliest end day
                count++;
            }

            day++;
        }

        return count;
    }

    public static void main(String[] args) {
        // int[][] events = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 1, 2 } };
        int[][] events = { { 1, 4 },
                { 4, 4 },
                { 2, 2 },
                { 3, 4 },
                { 1, 1 }
        };
        System.out.println("OUTPUT : " + new Maximum_Number_Of_Events_That_Can_Be_Attended().maxEvents(events));
    }
}
