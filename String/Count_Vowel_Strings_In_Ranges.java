import java.util.Arrays;

public class Count_Vowel_Strings_In_Ranges {

    public static boolean isVowel(char c) {
        // return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
        return "aeiou".indexOf(c) > -1;

    }

    /* this Solution will give reuslt : TLE */
    public static int[] vowelStrings(String[] words, int[][] queries) {
        int n = queries.length;
        int[] ans = new int[n];
        int idx = 0; // keep track of ans[]

        for (int query[] : queries) {
            int l = query[0];
            int r = query[1];

            int count = 0;
            for (int i = l; i <= r; i++) {
                String word = words[i];
                int length = word.length();
                if (isVowel(word.charAt(0)) && isVowel(word.charAt(length - 1))) {
                    count++;
                }
            }
            ans[idx++] = count;
        }

        return ans;
    }

    // Optimal approach : PrefixSum
    public static int[] vowelStrings2(String words[], int[][] queries) {
        int q = queries.length;
        int[] ans = new int[q];
        int n = words.length;

        // prefix Sum
        int[] prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            String word = words[i];
            int length = word.length();

            if (isVowel(word.charAt(0)) && isVowel(word.charAt(length - 1))) {
                prefixSum[i + 1] = prefixSum[i] + 1;
            }else {
                prefixSum[i + 1 ] = prefixSum[i];
            }
        }       
        int idx = 0;
        for (int query[] : queries) {
            int l = query[0];
            int r = query[1];

            ans[idx++] = prefixSum[r + 1] - prefixSum[l];
        }

        return ans;

    }

    public static void main(String[] args) {
        String[] words = { "aba", "bcb", "ece", "aa", "e" };
        int[][] queries = { { 0, 2 }, { 1, 4 }, { 1, 1 } };
        // Call the vowelStrings method and store the result
        int[] result = vowelStrings2(words, queries);
        // Print the result
        System.out.println("Output : " + Arrays.toString(result));
    }
}