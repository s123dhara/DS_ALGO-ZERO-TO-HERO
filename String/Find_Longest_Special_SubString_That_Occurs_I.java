import java.util.HashMap;
import java.util.Map;

public class Find_Longest_Special_SubString_That_Occurs_I {

    public static class Pair {
        char key;
        int value;

        Pair(char key, int value) {
            this.key = key;
            this.value = value;
        }

        public char getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }
    }

    public static int maximumLength(String s) {
        // Create a map to store the count of all substrings
        Map<Pair, Integer> count = new HashMap<>();
        int substringLength = 0;

        for (int start = 0; start < s.length(); start++) {
            char character = s.charAt(start);
            substringLength = 0;

            for (int end = start; end < s.length(); end++) {
                // If the current character matches the initial character, increment the count
                if (character == s.charAt(end)) {
                    substringLength++;
                    Pair key = new Pair(character, substringLength);
                    count.put(key, count.getOrDefault(key, 0) + 1);
                } else {
                    break;
                }
            }
        }

        // Variable to store the longest substring length with frequency at least 3
        int ans = 0;
        for (Map.Entry<Pair, Integer> entry : count.entrySet()) {
            int length = entry.getKey().getValue(); // Access length from Pair
            if (entry.getValue() >= 3 && length > ans) {
                ans = length;
            }
        }

        return ans == 0 ? -1 : ans;
    }

    public static int maximumLength_2_modifed(String s) {
        int n = s.length();
        int[][] matrix = new int[26][n + 1]; // Matrix to store counts for each character and length
        char prevChar = s.charAt(0);
        int length = 0;

        // Populate the matrix
        for (int i = 0; i < n; i++) {
            char currChar = s.charAt(i);

            if (currChar == prevChar) {
                length += 1;
                matrix[currChar - 'a'][length] += 1;
            } else {
                length = 1;
                matrix[currChar - 'a'][length] += 1;
                prevChar = currChar;
            }
        }

        int result = 0;

        // Calculate the result by checking cumulative sums in reverse order
        for (int i = 0; i < 26; i++) {
            int cumSum = 0;
            for (int j = n; j >= 1; j--) {
                cumSum += matrix[i][j];
                if (cumSum >= 3) {
                    result = Math.max(result, j);
                    break;
                }
            }
        }

        return result == 0 ? -1 : result;
    }

    public static void main(String[] args) {
        String s = "aaaa";
        System.out.println(maximumLength_2_modifed(s));
    }
}
