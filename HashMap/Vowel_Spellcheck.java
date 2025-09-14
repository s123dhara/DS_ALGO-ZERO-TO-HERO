import java.util.*;
import java.util.function.Function;

import javax.sound.sampled.Line;

public class Vowel_Spellcheck {

    public String[] spellchecker(String[] wordlist, String[] queries) {
        // Map first occured word
        Map<String, String> map = new HashMap<>();
        Map<String, String> wildCharMap = new HashMap<>();
        Set<String> set = new HashSet<>();
        String vw = "aeiou";

        String[] result = new String[queries.length];

        for (String word : wordlist) {
            set.add(word);
            if (!map.containsKey(word.toLowerCase())) {
                map.put(word.toLowerCase(), word);
            }
        }

        for (String word : map.keySet()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                if (vw.indexOf(word.charAt(i)) > -1) {
                    sb.append('*');
                } else {
                    sb.append(word.charAt(i));
                }
            }

            wildCharMap.put(word, sb.toString());
        }

        // System.out.println(wildCharMap);
        System.out.println("Set contains : " + set);
        System.out.println("Map contains : " + map);

        // Exact Program Starts
        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            System.out.println("Query Word :" + query);

            // check Exact Word
            if (set.contains(query)) {
                result[i] = query;
                System.out.println("Exact Match Word : " + query);
                continue;
            }

            // Check Capitalisation ignore Case
            if (map.containsKey(query.toLowerCase())) {
                result[i] = map.getOrDefault(query.toLowerCase(), "");
                System.out.println("Ignore Capitalisation word : " + map.get(query.toLowerCase()));
                continue;
            }

            // vowel matching case
            StringBuilder queryString = new StringBuilder(query.toLowerCase());
            boolean isVowelCheckingDone = false;
            for (int j = 0; j < queryString.length(); j++) {
                if (vw.indexOf(queryString.charAt(j)) > -1) {
                    queryString.setCharAt(j, '*');
                }
            }
            System.out.println("queryString : " + queryString);
            for (String word : wildCharMap.keySet()) {
                System.out.println("WildChar Map Word : " + wildCharMap.get(word));
                System.out.println("Checking result : " + queryString.toString().equals(wildCharMap.get(word)));
                if (queryString.toString().equals(wildCharMap.get(word))) {
                    System.out.println("Satisfied");
                    System.out.println("Result word : " + map.get(word));
                    result[i] = map.get(word);
                    isVowelCheckingDone = true;
                    break;
                }
            }

            if (!isVowelCheckingDone) {
                // if nothing matching
                result[i] = "";
            }

        }

        return result;
    }

    private String devowel(String word) {
        char[] chars = word.toLowerCase().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                chars[i] = '*';
            }
        }
        return new String(chars);
    }

    public String[] spellchecker2(String[] wordlist, String[] queries) {
        Set<String> exactSet = new HashSet<>();
        Map<String, String> caseInsensitiveMap = new HashMap<>();
        Map<String, String> vowelWildCharMap = new HashMap<>();
        String[] result = new String[queries.length];

        // Replace vowels with '*' when characters are converted to lowercase and
        // character are vowels
        Function<String, String> devowel = word -> word.toLowerCase().replaceAll("[aeiou]", "*");

        // Preprocess the wordlist and save them in Map and Set
        for (String word : wordlist) {
            exactSet.add(word);

            String lowerCaseWord = word.toLowerCase();
            caseInsensitiveMap.putIfAbsent(lowerCaseWord, word);

            String devoweledWord = devowel.apply(word);
            vowelWildCharMap.putIfAbsent(devoweledWord, word);
        }

        // Process each query
        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];

            // Check for exact match
            if (exactSet.contains(query)) {
                result[i] = query;
                continue;
            }

            // Check for case-insensitive match
            String lowerCaseQuery = query.toLowerCase();
            if (caseInsensitiveMap.containsKey(lowerCaseQuery)) {
                result[i] = caseInsensitiveMap.get(lowerCaseQuery);
                continue;
            }

            // Check for vowel error match
            String devoweledQuery = devowel.apply(query);
            if (vowelWildCharMap.containsKey(devoweledQuery)) {
                result[i] = vowelWildCharMap.get(devoweledQuery);
                continue;
            }

            // If no match found, return an empty string
            result[i] = "";
        }

        return result;

    }

    public String[] spellchecker3(String[] wordlist, String[] queries) {
        Set<String> exactSet = new HashSet<>();
        Map<String, String> caseInsensitiveMap = new HashMap<>();
        Map<String, String> vowelWildCharMap = new HashMap<>();
        String[] result = new String[queries.length];

        // Preprocess the wordlist and save them in Map and Set
        for (String word : wordlist) {
            exactSet.add(word);

            String lowerCaseWord = word.toLowerCase();
            caseInsensitiveMap.putIfAbsent(lowerCaseWord, word);

            String devoweledWord = devowel(word);
            vowelWildCharMap.putIfAbsent(devoweledWord, word);
        }

        // Process each query
        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];

            // Check for exact match
            if (exactSet.contains(query)) {
                result[i] = query;
                continue;
            }

            // Check for case-insensitive match
            String lowerCaseQuery = query.toLowerCase();
            if (caseInsensitiveMap.containsKey(lowerCaseQuery)) {
                result[i] = caseInsensitiveMap.get(lowerCaseQuery);
                continue;
            }

            // Check for vowel error match
            String devoweledQuery = devowel(query);
            if (vowelWildCharMap.containsKey(devoweledQuery)) {
                result[i] = vowelWildCharMap.get(devoweledQuery);
                continue;
            }

            // If no match found, return an empty string
            result[i] = "";
        }

        return result;

    }

    public static void main(String[] args) {
        String[] wordlist = { "KiTe", "kite", "hare", "Hare" };
        String[] queries = { "kite", "Kite", "KiTe", "Hare", "HARE", "Hear", "hear", "keti", "keet", "keto" };
        String[] result = new Vowel_Spellcheck().spellchecker3(wordlist, queries);
        System.out.println("OUTPUT : " + Arrays.toString(result));

    }
}
