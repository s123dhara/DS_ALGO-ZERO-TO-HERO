import java.util.*;

public class Find_the_Original_Typed_String_II {

    public long MOD = 1_000_000_007;
    public int[][] t;

    public int solve(int i, int count, List<Integer> freq, int k) {

        if (i >= freq.size()) {
            if (count <= k) {
                return 1; // Found the Invalid String
            }

            return 0; // NO Invalid String
        }

        if (t[i][count] != -1) {
            return t[i][count];
        }

        long result = 0;
        for (int take = 1; take <= freq.get(i); take++) {
            if (take + count < k) {
                // Take is Current Characters and Count is Present String Charcters Count
                // This is Invalid String
                result = (result + solve(i + 1, count + take, freq, k)) % MOD;
            } else {
                break; // Early Exit, because we do not need to explore valid String
            }
        }

        return t[i][count] = (int) result;
    }

    public int possibleStringCount(String word, int k) {

        if (k > word.length()) {
            return 0;
        }

        List<Integer> freq = new ArrayList<>();

        int count = 1;
        for (int i = 1; i < word.length(); i++) {
            if (word.charAt(i) == word.charAt(i - 1)) {
                count++;
            } else {
                freq.add(count);
                count = 1;
            }
        }
        freq.add(count);

        // Total Possibilites String
        long P = 1;

        // Iterate over the frequencies to get Total result
        for (int fr : freq) {
            P = (P * fr) % MOD;
        }

        // Check If(unique charactes >= k) return P;

        if (freq.size() >= k) {
            return (int) P;
        }

        t = new int[freq.size()][k + 1];
        for (int[] arr : t) {
            Arrays.fill(arr, -1);
        }

        long inValidCount = solve(0, 0, freq, k);

        return (int) ((P - inValidCount + MOD) % MOD); // To avoid negative modulo results
    }

    public int BottomUp_possibleStringCount(String word, int k) {
        if (k > word.length()) return 0;

        List<Integer> freq = new ArrayList<>();
        int count = 1;
        for (int i = 1; i < word.length(); i++) {
            if (word.charAt(i) == word.charAt(i - 1)) {
                count++;
            } else {
                freq.add(count);
                count = 1;
            }
        }
        freq.add(count);

        long P = 1; // total possible strings
        for (int f : freq) {
            P = (P * f) % MOD;
        }

        if (freq.size() >= k) return (int) P;

        int n = freq.size();
        int[][] t = new int[n + 1][k + 1];

        for (int c = k - 1; c >= 0; c--) {
            t[n][c] = 1;
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int c = k - 1; c >= 0; c--) {
                long result = 0;
                for (int take = 1; take <= freq.get(i); take++) {
                    if (c + take < k) {
                        result = (result + t[i + 1][c + take]) % MOD;
                    } else {
                        break;
                    }
                }
                t[i][c] = (int) result;
            }
        }

        long invalidCount = t[0][0];
        return (int)((P - invalidCount + MOD) % MOD);
    }

    //  Prefix Sum in DP ARRAY .. to REDUCCE THE 3 FOR LOOP CHECKING 
    public int Improved_3_possibleStringCount(String word, int k) {
        if (k > word.length())
            return 0;

        int MOD = (int)1_000_000_007;
        List<Integer> freq = new ArrayList<>();
        int count = 1;
        for (int i = 1; i < word.length(); i++) {
            if (word.charAt(i) == word.charAt(i - 1)) {
                count++;
            } else {
                freq.add(count);
                count = 1;
            }
        }
        freq.add(count);

        long P = 1; // total possible strings
        for (int f : freq) {
            P = (P * f) % MOD;
        }

        if (freq.size() >= k)
            return (int) P;

        int n = freq.size();
        int[][] t = new int[n + 1][k + 1];

        for (int c = k - 1; c >= 0; c--) {
            t[n][c] = 1;
        }

        for (int i = n - 1; i >= 0; i--) {
            int[] prefix = new int[k + 2];
            for (int h = 1; h <= k; h++) {
                prefix[h] = (prefix[h - 1] + t[i + 1][h - 1]) % MOD;
            }

            for (int c = k - 1; c >= 0; c--) {
                int l = c + 1;
                int r = c + freq.get(i);

                if (r + 1 > k) {
                    r = k - 1;
                }

                if (l <= r) {
                    t[i][c] = (prefix[r + 1] - prefix[l] + MOD) % MOD;
                }
            }
        }

        long invalidCount = t[0][0];
        return (int) ((P - invalidCount + MOD) % MOD);
    }

    public static void main(String[] args) {
        String word = "aaabbb";
        int k = 3;

        // String word = "aabbccdd";
        // int k = 7;

        System.out.println("OUTPUT : " + new Find_the_Original_Typed_String_II().Improved_3_possibleStringCount(word, k));
    }
}
