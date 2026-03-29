import java.util.*;

class Group {
    String s;
    boolean isEvenFlag;

    char[] elements = new char[2];

    public Group(String s, boolean flag) {
        this.s = s;
        this.isEvenFlag = flag;
    }

    public void build() {
        if (isEvenFlag) {
            elements[0] = s.charAt(0);
            elements[1] = s.charAt(2);
        } else {
            elements[0] = s.charAt(1);
            elements[1] = s.charAt(3);
        }

        Arrays.sort(elements); // sort here itself
    }
}

public class Check_If_Strings_Can_Be_Made_Equal_With_Operations_I {

    public boolean canBeEqual(String s1, String s2) {

        // EVEN groups
        Group s1Even = new Group(s1, true);
        Group s2Even = new Group(s2, true);

        s1Even.build();
        s2Even.build();

        // ODD groups
        Group s1Odd = new Group(s1, false);
        Group s2Odd = new Group(s2, false);

        s1Odd.build();
        s2Odd.build();

        // Compare even groups
        if (!Arrays.equals(s1Even.elements, s2Even.elements)) {
            return false;
        }

        // Compare odd groups
        if (!Arrays.equals(s1Odd.elements, s2Odd.elements)) {
            return false;
        }

        return true;
    }

    public boolean canBeEqual_v2(String s1, String s2) {

        if (s1.length() != s2.length())
            return false;

        int n = s1.length();

        // Frequency arrays
        int[] even1 = new int[26];
        int[] odd1 = new int[26];

        int[] even2 = new int[26];
        int[] odd2 = new int[26];

        // Build frequency
        for (int i = 0; i < n; i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);

            if (i % 2 == 0) {
                even1[c1 - 'a']++;
                even2[c2 - 'a']++;
            } else {
                odd1[c1 - 'a']++;
                odd2[c2 - 'a']++;
            }
        }

        // Compare even groups
        if (!Arrays.equals(even1, even2)) {
            return false;
        }

        // Compare odd groups
        if (!Arrays.equals(odd1, odd2)) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        String s1 = "abcd";
        String s2 = "cdab";

        System.out.println("OUTPUT : " +
                new Check_If_Strings_Can_Be_Made_Equal_With_Operations_I().canBeEqual_v2(s1, s2));
    }
}