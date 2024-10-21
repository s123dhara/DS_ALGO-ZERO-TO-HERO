import java.util.*;

public class Count_The_number_Of_Special_characters_II {

    public static int numberOfSpecialChars(String word) {
        int[] lowercaseLastIndex = new int[26];
        int[] uppercaseFirstIndex = new int[26];

        Arrays.fill(lowercaseLastIndex, -1); // Fill with -1 as no index means not found
        Arrays.fill(uppercaseFirstIndex, -1);

        int n = word.length();
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            if (c >= 'a' && c <= 'z') {
                lowercaseLastIndex[c - 'a'] = i; // Update last index for lowercase
            }
            if (c >= 'A' && c <= 'Z') {
                if (uppercaseFirstIndex[c - 'A'] == -1) {
                    uppercaseFirstIndex[c - 'A'] = i; // Update first index for uppercase
                }
            }
        }

        int result = 0;
        for (int i = 0; i < 26; i++) {

            if (lowercaseLastIndex[i] != -1 && uppercaseFirstIndex[i] != -1
                    && lowercaseLastIndex[i] < uppercaseFirstIndex[i]) {
                result += 1;

            }
        }

        return result;
    }

    public static void main(String[] args) {
        String word = "aaAbcBC";
        System.out.println("OUTPUT : " + numberOfSpecialChars(word));
    }
}
