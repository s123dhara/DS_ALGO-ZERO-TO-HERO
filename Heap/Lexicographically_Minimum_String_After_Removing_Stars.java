import java.util.*;

public class Lexicographically_Minimum_String_After_Removing_Stars {

    private int[] findMinimumChar(ArrayDeque<Integer>[] indicesMap) {
        for (int i = 0; i < 26; i++) {
            if (!indicesMap[i].isEmpty()) {
                return new int[]{i, indicesMap[i].pollLast()};
            }
        }
        return new int[]{-1, -1};
    }

    public String clearStars(String s) {
        int n = s.length();
        ArrayDeque<Integer>[] indicesMap = new ArrayDeque[26];
        for (int i = 0; i < 26; i++) {
            indicesMap[i] = new ArrayDeque<>();
        }

        boolean[] removed = new boolean[n]; // fixed length
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == '*') {
                removed[i] = true;
                
                int[] minChar = findMinimumChar(indicesMap);
                if (minChar[0] != -1) {
                    removed[minChar[1]] = true;
                }
            } else {
                indicesMap[ch - 'a'].addLast(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (!removed[i] && s.charAt(i) != '*') {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "leet**cod*e";
        System.out.println("OUTPUT : " + new Lexicographically_Minimum_String_After_Removing_Stars().clearStars(s));
    }
}
