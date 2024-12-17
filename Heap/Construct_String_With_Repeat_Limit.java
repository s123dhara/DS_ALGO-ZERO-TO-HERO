import java.util.PriorityQueue;

public class Construct_String_With_Repeat_Limit {

    public static String repeatLimitedString(String s, int repeatLimit) {
        int[] freq = new int[26];
        StringBuilder sb = new StringBuilder();
        /* int[] = {charIndex, frequency} */
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]); // max-heap of the characters from z - a
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0)
                pq.offer(new int[] { i, freq[i] });
        }

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int charIndex = curr[0];
            int count = curr[1];

            int useCount = Math.min(count, repeatLimit);
            for (int i = 0; i < useCount; i++) {
                sb.append((char) (charIndex + 97));
            }

            if (count > useCount) { // means : Still there is remaining character so..
                /* We can't add character of repeatedLimit */
                if (pq.isEmpty()) {
                    // somehow there is no character left not needed to check furthur
                    break;
                }

                // Step 3: to break the streak of character add next character
                int[] next = pq.poll();
                if (next[1] > 0) {
                    // add next character
                    sb.append((char) (next[0] + 97));
                }
                next[1] -= 1; // Decrease the count which is used.

                // Step 4: then Re-added the next Character and Current Character to Heap for
                // furthur checking
                if (next[1] > 0) {
                    // check once if frequency of next character is zero no need to add.
                    pq.offer(next);
                }

                // current character Re-add
                pq.offer(new int[] { charIndex, count - useCount });

            }

        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "cczazcc";
        int repeatLimit = 3;

        System.out.println("OUTPUT : " + repeatLimitedString(s, repeatLimit));

    }

}
