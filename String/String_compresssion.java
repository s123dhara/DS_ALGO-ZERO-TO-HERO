import java.util.*;

public class String_compresssion {

    public static String compressedString(String word) {
        StringBuilder comp = new StringBuilder();
        int n = word.length();
        int i = 0;

        while (i < n) {
            int count = 0;
            char ch = word.charAt(i);

            while (i < n && count < 9 && word.charAt(i) == ch) {
                count++;
                i++;
            }

            comp.append(count).append(ch);
        }

        return comp.toString();
    }

    public static void main(String[] args) {
        String word = "mrm";
        System.out.println("OUTPUT: " + compressedString(word)); // Expected: "1m1r1m"
    }
}
