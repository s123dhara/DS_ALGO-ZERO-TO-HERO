import java.util.*;

// class Group {
//     String s;
//     boolean isEvenFlag;

//     List<Character> elements = new ArrayList<>();

//     public Group(String s, boolean flag) {
//         this.s = s;
//         this.isEvenFlag = flag;
//     }

//     public void build() {
//         for (int i = 0; i < s.length(); i++) {
//             if (isEvenFlag && i % 2 == 0) {
//                 elements.add(s.charAt(i));
//             } else if (!isEvenFlag && i % 2 != 0) {
//                 elements.add(s.charAt(i));
//             }
//         }

//         Collections.sort(elements); // sort group
//     }
// }

class Group_v2 {
    String s;
    boolean isEvenFlag;

    int[] elements = new int[26];

    public Group_v2(String s, boolean flag) {
        this.s = s;
        this.isEvenFlag = flag;
    }

    public void build() {
        for (int i = 0; i < s.length(); i++) {
            if (isEvenFlag && i % 2 == 0) {
                elements[s.charAt(i) - 'a']++;
            } else if (!isEvenFlag && i % 2 != 0) {
                elements[s.charAt(i) - 'a']++;
            }
        }
    }
}

public class Check_If_Strings_Can_Be_Made_Equal_With_Operations_II {

    public boolean canBeEqual(String s1, String s2) {

        if (s1.length() != s2.length()) return false;

        // EVEN groups
        Group_v2 s1Even = new Group_v2(s1, true);
        Group_v2 s2Even = new Group_v2(s2, true);

        s1Even.build();
        s2Even.build();

        // ODD groups
        Group_v2 s1Odd = new Group_v2(s1, false);
        Group_v2 s2Odd = new Group_v2(s2, false);

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

    public static void main(String[] args) {
        String s1 = "abcd";
        String s2 = "cdab";

        System.out.println("OUTPUT : " +
            new Check_If_Strings_Can_Be_Made_Equal_With_Operations_I().canBeEqual(s1, s2));
    }
}