import java.util.*;

public class Longest_Subsequence_Repeated_K_Times {

    String result = "";

    public void generateSubsequence(StringBuilder curr, int[] requiredFreq, boolean[] validChars, int maxLen, int k,
            String s) {
        if (curr.length() > maxLen) {
            return;
        }

        if ((curr.length() > result.length() ||
                (curr.length() == result.length() && curr.toString().compareTo(result) > 0)) &&
                isSubsequence(s, curr, k)) {

            result = curr.toString();

        }

        for (int i = 0; i < 26; i++) {
            if (!validChars[i] || requiredFreq[i] == 0) {
                continue;
            }

            // Do
            char ch = (char) (i + 'a');
            curr.append(ch);
            requiredFreq[i] -= 1;

            // Explore
            generateSubsequence(curr, requiredFreq, validChars, maxLen, k, s);

            // Delete the Last character
            // Undo
            curr.deleteCharAt(curr.length() - 1);
            requiredFreq[i] += 1;

        }
    }

    public boolean ImpovedGenerateSubsequence(StringBuilder curr, int[] requiredFreq, boolean[] validChars, int maxLen,
            int k, String s) {
        if (curr.length() == maxLen) {
            if (isSubsequence(s, curr, k)) {
                result = curr.toString();
                return true;
            }
            return false;
        }

        for (int i = 25; i >= 0; i--) { // 'z' to 'a' for lexicographically larges
            if (!validChars[i] || requiredFreq[i] == 0) {
                continue;
            }

            // Do
            char ch = (char) (i + 'a');
            curr.append(ch);
            requiredFreq[i] -= 1;

            // Explore
            if (ImpovedGenerateSubsequence(curr, requiredFreq, validChars, maxLen, k, s)) {
                return true; // Early exit if valid found
            }

            // Delete the Last character
            // Undo
            curr.deleteCharAt(curr.length() - 1);
            requiredFreq[i] += 1;

        }

        return false;
    }

    public boolean isSubsequence(String s, StringBuilder sub, int k) {
        int i = 0;
        int j = 0;

        int L = sub.length();
        int n = s.length();

        while (i < n && j < L * k) {
            if (s.charAt(i) == sub.charAt(j % L)) {
                j++;
            }
            i++;
        }

        return j == L * k;
    }

    public String longestSubsequenceRepeatedK(String s, int k) {
        int n = s.length();

        // 1. Find the frequency of each character in the string
        int[] freq = new int[26];
        for (int i = 0; i < n; i++) {
            freq[s.charAt(i) - 'a']++;
        }

        // 2. Filter the characters that appear at least k times
        boolean[] validChars = new boolean[26];

        // And We have to find required frequency of each character which will form
        // answer subsequence
        int[] requiredFreq = new int[26];

        for (int i = 0; i < 26; i++) {
            if (freq[i] >= k) {
                validChars[i] = true;
                requiredFreq[i] = freq[i] / k; // atmost this can be used in a Sub Sequence;
            }
        }

        int maximumLengthofSubsequence = n / k;

        // 3. Generate all possible subsequences of length maximumLengthofSubsequence
        // and check if they are valid subsequences of string s
        StringBuilder curr = new StringBuilder();
        generateSubsequence(curr, requiredFreq, validChars, maximumLengthofSubsequence, k, s);

        return result;

    }

    public String longestSubsequenceRepeatedK_improved(String s, int k) {
        int n = s.length();

        // 1. Find the frequency of each character in the string
        int[] freq = new int[26];
        for (int i = 0; i < n; i++) {
            freq[s.charAt(i) - 'a']++;
        }

        // 2. Filter the characters that appear at least k times
        boolean[] validChars = new boolean[26];

        // And We have to find required frequency of each character which will form
        // answer subsequence
        int[] requiredFreq = new int[26];

        for (int i = 0; i < 26; i++) {
            if (freq[i] >= k) {
                validChars[i] = true;
                requiredFreq[i] = freq[i] / k; // atmost this can be used in a Sub Sequence;
            }
        }

        int maxLen = n / k;

        // 3. Try from longest to shortest valid subsequence
        for (int len = maxLen; len >= 1; len--) {
            int[] tempFreq = requiredFreq.clone();
            StringBuilder curr = new StringBuilder();
            if (ImpovedGenerateSubsequence(curr, tempFreq, validChars, len, k, s)) {
                return result;
            }
        }

        return result;

    }

    public static void main(String[] args) {
        String s = "letsleetcode";
        int k = 2;

        System.out.println(
                "OUTPUT :  " + new Longest_Subsequence_Repeated_K_Times().longestSubsequenceRepeatedK_improved(s, k));
    }
}


/**
 * this is Improved version of generateSubsequence
 * 
 * 1. We are not generating all the subsequence and checking if it is valid or not
 * 2. We are generating only those subsequence which are valid and then checking if it is valid or not
    class Solution {
        String result = "";

        public boolean ImpovedGenerateSubsequence(StringBuilder curr, int[] requiredFreq, boolean[] validChars, int maxLen,
                                                int k, String s) {
            if (curr.length() == maxLen) {
                if (isSubsequence(s, curr, k)) {
                    result = curr.toString();
                    return true;
                }
                return false;
            }

            for (int i = 25; i >= 0; i--) {  // 'z' to 'a' for lexicographically largest
                if (!validChars[i] || requiredFreq[i] == 0) {
                    continue;
                }

                // Do
                char ch = (char) (i + 'a');
                curr.append(ch);
                requiredFreq[i] -= 1;

                // Explore
                if (ImpovedGenerateSubsequence(curr, requiredFreq, validChars, maxLen, k, s)) {
                    return true;  // Early exit if valid found
                }

                // Undo
                curr.deleteCharAt(curr.length() - 1);
                requiredFreq[i] += 1;
            }

            return false;
        }

        public boolean isSubsequence(String s, StringBuilder sub, int k) {
            int i = 0, j = 0;
            int L = sub.length(), n = s.length();

            while (i < n && j < L * k) {
                if (s.charAt(i) == sub.charAt(j % L)) {
                    j++;
                }
                i++;
            }

            return j == L * k;
        }

        public String longestSubsequenceRepeatedK(String s, int k) {
            int n = s.length();

            // 1. Frequency of each character
            int[] freq = new int[26];
            for (int i = 0; i < n; i++) {
                freq[s.charAt(i) - 'a']++;
            }

            // 2. Filter characters and compute max usable frequency
            boolean[] validChars = new boolean[26];
            int[] requiredFreq = new int[26];
            for (int i = 0; i < 26; i++) {
                if (freq[i] >= k) {
                    validChars[i] = true;
                    requiredFreq[i] = freq[i] / k;
                }
            }

            int maxLen = n / k;

            // 3. Try from longest to shortest valid subsequence
            for (int len = maxLen; len >= 1; len--) {
                int[] tempFreq = requiredFreq.clone();
                StringBuilder curr = new StringBuilder();
                if (ImpovedGenerateSubsequence(curr, tempFreq, validChars, len, k, s)) {
                    return result;
                }
            }

            return "";
        }
    }

 * 
 * 
 */