public class Construct_K_Palindrome_Strings {

    public static boolean canConstruct(String s, int k) {
        int n = s.length();

        if(n < k) return false;

        if(n == k) return true;

        int[] count = new int[26];

        for(char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }

        int oddFreqCount = 0;
        for(int i = 0; i < 26; i++) {
            if(count[i] % 2 != 0) {
                oddFreqCount++;
            }
        }

        return oddFreqCount <= k;
    }

    public static void main(String[] args) {
        String s = "annabelle";
        int k = 2;

        System.out.println("OUTPUT : " + canConstruct(s, k));
    }
}
