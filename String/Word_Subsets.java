import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Word_Subsets {

    public static boolean isCheck(String s, int[][] count) {
        int n = count.length;
        boolean allCheck = true;

        int sCount[] = new int[26];
        for(int i = 0; i < s.length(); i++) {
            sCount[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < n; i++) {
            int[] cpy = Arrays.copyOf(count[i], 26);
            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);
                if (cpy[c - 'a'] != 0) {
                    cpy[c - 'a']--;
                }
            }

            boolean check = true;
            // Check words2[i] all zero or not
            for (int cp : cpy) {
                if (cp != 0) {
                    check = false;
                }
            }

            if (!check) {
                allCheck = false;
                break;
            }
        }
        return allCheck;
    }

    public static List<String> wordSubsets(String[] words1, String[] words2) {
        List<String> result = new ArrayList<>();
        int n = words1.length;
        int m = words2.length;

        int[][] count = new int[m][26];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < words2[i].length(); j++) {
                char c = words2[i].charAt(j);
                count[i][c - 'a']++;
            }
        }

        for (int i = 0; i < n; i++) {
            if (isCheck(words1[i], count)) {
                result.add(words1[i]);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String[] words1 = { "amazon", "apple", "facebook", "google", "leetcode" };
        String[] words2 = { "lo", "eo" };

        System.out.println("OUTPUT : " + wordSubsets(words1, words2));
    }
}