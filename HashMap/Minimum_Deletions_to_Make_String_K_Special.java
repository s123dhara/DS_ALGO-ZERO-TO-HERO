import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Minimum_Deletions_to_Make_String_K_Special {

    public int minimumDeletions(String word, int k) {
        int[] freq = new int[26];
        for (char ch : word.toCharArray()) {
            freq[ch - 'a']++;
        }

        Arrays.sort(freq);

        // Remove zeros
        List<Integer> list = Arrays.stream(freq)
                .filter(fr -> fr != 0)
                .boxed()
                .collect(Collectors.toList());

        int minDeletions = Integer.MAX_VALUE;
        int n = list.size();

        for (int i = 0; i < n; i++) {
            int baseFreq = list.get(i);
            int deletions = 0;

            System.out.println("Base frequency: " + baseFreq);
            // Reduce all frequencies greater than baseFreq + k
            for (int j = 0; j < n; j++) {
                System.out.println(" - list.get(j): " + list.get(j) + " baseFreq + k: " + (baseFreq + k) + " Before deletions: " + deletions);
                if (list.get(j) > baseFreq + k) {
                    deletions += list.get(j) - (baseFreq + k);
                }
                // Remove characters from lower indices (i.e., smaller frequencies)
                else if (j < i) {
                    deletions += list.get(j);
                }

                System.out.println(" - Deletions: " + deletions);
            }

            minDeletions = Math.min(minDeletions, deletions);
            System.out.println("Minimum deletions: " + minDeletions);            
        }

        return minDeletions;
    }

    public static void main(String[] args) {
        // String word = "dabdcbdcdcd";
        // int k = 2;
        String word = "ahahnhahhah";
        int k = 1;

        System.out.println("OUTPUT : " + new Minimum_Deletions_to_Make_String_K_Special().minimumDeletions(word, k));
    }
}


/**
 * class Solution {
    public int minimumDeletions(String word, int k) {
        int[] freq = new int[26];
        for (char ch : word.toCharArray()) {
            freq[ch - 'a']++;
        }

        // Arrays.sort(freq);

        int minDeletions = Integer.MAX_VALUE;

        for (int i = 0; i < 26; i++) {
            if (freq[i] == 0) continue;

            int x = freq[i];
            int deletions = 0;

            for (int j = 0; j < 26; j++) {
                if (freq[j] == 0) continue;
                if(i == j) continue;

                int y = freq[j];
                // if (y > x + k) {
                if(y < x) {
                    deletions += y;
                }
                else if(Math.abs(y - x) > k)
                    deletions += Math.abs(y - x) - k;
                // } else if (j < i) {
            }

            minDeletions = Math.min(minDeletions, deletions);
        }

        return minDeletions;
    }
}

 */