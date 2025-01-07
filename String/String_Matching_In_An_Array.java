import java.util.ArrayList;
import java.util.List;

public class String_Matching_In_An_Array {
    public static List<String> stringMatching(String[] words) {
        List<String> result = new ArrayList<>();
        int n = words.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j != i && !result.contains(words[j]) && words[i].contains(words[j])) {
                    result.add(words[j]);
                }
            }
        }

        return result;
    }

    /*
        // O(nlogn+n^2)
     * public static List<String> stringMatching(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() - b.length()); // Sort by length
        List<String> result = new ArrayList<>();
        
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (words[j].contains(words[i])) {
                    result.add(words[i]);
                    break; // No need to check further for words[i]
                }
            }
        }
        
        return result;
    }
     */

    public static void main(String[] args) {
        String[] words = { "mass", "as", "hero", "superhero" };
        System.out.println("OUTPUT : " + stringMatching(words));

        System.out.println("mass".contains("as"));
    }
}