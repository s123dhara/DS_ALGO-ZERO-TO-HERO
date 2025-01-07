import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Unique_Length_3_Palindromic_Subsequences {

    public static int countPalindromicSubsequence(String s) {
        int n = s.length();
        int[] freq = new int[26];
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        HashSet<String> set = new HashSet<>();

        int count = 0;
        for (int i = 0; i < n - 1; i++) {
            int[] cpyFreq = Arrays.copyOf(freq, 26);
            for (int j = i + 1; j < n; j++) {
                // first substract the count 'i'th character
                // first substract the count 'j'th character

                try {
                    System.out.println(Arrays.toString(cpyFreq));
                    cpyFreq[s.charAt(i) - 'a']--;
                    cpyFreq[s.charAt(j) - 'a']--;
                } catch (Error e) {
                    System.out.println(e.getMessage());
                }

                StringBuilder sb = new StringBuilder();
                sb.append(s.charAt(i));
                sb.append(s.charAt(j));
                sb.append(s.charAt(i));
                if (cpyFreq[s.charAt(i) - 'a'] != 0 && !set.contains(sb.toString())) {
                    set.add(sb.toString());
                    System.out.println("STRING : " + sb.toString());
                    count++;
                }

                // restore it
                cpyFreq[s.charAt(i) - 'a']++;
            }
            System.out.println("----------------");
            freq[s.charAt(i) - 'a']--;
        }

        return count;
    }

    public static int countPalindromicSubsequence2(String s) {
        int n = s.length();
        int[] indexMap = new int[26];
        Arrays.fill(indexMap, -1);

        // Map to track first occurrence of each character
        int[] firstOccurrence = new int[26];
        Arrays.fill(firstOccurrence, -1);

        // Update first and last occurrence of each character
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (firstOccurrence[c - 'a'] == -1) {
                firstOccurrence[c - 'a'] = i; // First occurrence
            }
            indexMap[c - 'a'] = i; // Last occurrence
        }

        int count = 0;

        // Iterate over each character from 'a' to 'z'
        for (int c = 0; c < 26; c++) {
            int first = firstOccurrence[c];
            int last = indexMap[c];

            if (first != -1 && last != -1 && first < last) {
                // Use a set to find unique middle characters
                Set<Character> middleChars = new HashSet<>();
                for (int i = first + 1; i < last; i++) {
                    middleChars.add(s.charAt(i));
                }
                count += middleChars.size();
            }
        }

        return count;
    }

    /* Optimal approach : (modified Method 2) */
    public static int countPalindromicSubsequence3(String s) {
        int n = s.length();
        int count = 0;
        
        for(char c = 'a'; c <= 'z'; c++) {
            int first = -1;
            int last  = -1;

            //iterate over the String 
            for(int i = 0; i < n; i++) {
                char currChar = s.charAt(i);
                if(c == currChar) {
                    if(first == -1) first = i;  
                    
                    last = i;
                }
            }

            // find the Middle characters, so I took a HashSet
            // we Can another checking 
            if(first != -1 && last != -1 && first < last) {
                HashSet<Character> set = new HashSet<>();
                for(int i = first + 1; i < last; i++) { // finding between characters 
                    char ch = s.charAt(i);
                    set.add(ch);
                }
                count += set.size();
                
            }
            
        }

        return count;
    }
    public static void main(String[] args) {
        // String s = "aabca";
        String s = "ckafnafqo";
        System.out.println("OUTPUT : " + countPalindromicSubsequence2(s));
    }
}