
/**
 * Longest_Happy_String
 */
import java.util.PriorityQueue;

public class Longest_Happy_String {

    static class Pair {
        public char character;
        public int count;

        Pair(char character, int count) {
            this.character = character;
            this.count = count;
        }
    }

    public static String longestDiverseString(int a, int b, int c) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> y.count - x.count); // Max-Heap 

        if (a > 0)
            pq.offer(new Pair('a', a));
        if (b > 0)
            pq.offer(new Pair('b', b));
        if (c > 0)
            pq.offer(new Pair('c', c));

        StringBuilder result = new StringBuilder();
        Pair lastUsed = null;

        while (!pq.isEmpty()) {
            Pair current = pq.poll();

            if (lastUsed == null || lastUsed.character != current.character) {

                int addCount = Math.min(2, current.count); // Minimum We Can Add 2 characters or Minimum 1 Characters
                for (int i = 0; i < addCount; i++)
                    result.append(current.character);

                current.count -= addCount; // Decrease the count of Current Character

                if (current.count > 0) {
                    // if current character count is Still Exists
                    // then push it to Heap
                    pq.offer(current);
                }
                lastUsed = current;
            } else {

                // thats mean : last character either null for 1st Case
                // or, Last character is same with current character
                // We should poll next character and add it once
                if (pq.isEmpty())
                    break; // No furthur checking is Required

                Pair next = pq.poll();
                result.append(next.character);

                next.count -= 1; // Decrease Once

                if (next.count > 0) { // if more than 1 then add it to heap
                    pq.offer(next);
                }

                if (current.count > 0) { // we have pulled it out , then add it for furthur execution
                    pq.offer(current);
                }
                lastUsed = next;
            }

        }

        return result.toString();
    }

    public static void main(String[] args) {
        int a = 0;
        int b = 8;
        int c = 11;

        System.out.println("OUTPUT: " + longestDiverseString(a, b, c));
    }
}
