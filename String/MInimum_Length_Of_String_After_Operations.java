public class MInimum_Length_Of_String_After_Operations {
    public static int minimumLength(String s) {
        int n = s.length();
        int[] total = new int[26];
        int[] left = new int[26];

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            total[c - 'a']++;
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            left[c - 'a']++;
            int leftCount = (left[c - 'a'] - 1);
            if (leftCount == 0) {
                continue;
            }
            if (total[c - 'a'] > left[c - 'a']) {
                int rightCount = (total[c - 'a'] - left[c - 'a']);
                if (leftCount != 0 && rightCount != 0) {
                    total[c - 'a'] = Math.max(0, total[c - 'a'] -= 2);
                    left[c - 'a'] = Math.max(0, left[c - 'a'] -= 1);

                }
            }
        }

        for (int i = 0; i < 26; i++) {
            if (total[i] != 0) {
                count += total[i];
            }
        }

        return count;
    }

    public static int minimumLength2(String s) {
        int totalLength = 0;
        int[] freq = new int[26];
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        for (int frq : freq) {
            if (frq == 0) {
                continue;
            }

            if (frq % 2 == 0) {
                // If the fequency is Even, then ultimately two characters will left
                totalLength += 2;
            } else {
                // this means, freqency is odd, then ultimately one character will left
                totalLength += 1;
            }
        }

        return totalLength;

    }

    public static void main(String[] args) {
        String s = "abaacbcbb";
        System.out.println("output : " + minimumLength2(s));
    }
}
