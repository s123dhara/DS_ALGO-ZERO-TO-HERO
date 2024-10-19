/**
 * Fint_Kth_Bit_In_Nth_Binary_String
 */
public class Fint_Kth_Bit_In_Nth_Binary_String {

    // Function to reverse and invert the binary string
    public static String reverseAndInvert(String sb) {
        int n = sb.length();
        char[] ch = sb.toCharArray();
        int i = 0;
        int j = n - 1;

        // Reverse and invert the characters
        while (i <= j) {
            // Swap characters
            char temp = ch[i];
            ch[i] = ch[j];
            ch[j] = temp;

            // Invert the swapped characters
            ch[i] = (ch[i] == '0') ? '1' : '0';
            ch[j] = (ch[j] == '0') ? '1' : '0';

            // If i == j, it means we are at the middle character in case of odd-length strings
            // In that case, we should only invert once
            if (i == j) {
                ch[i] = (ch[i] == '0') ? '1' : '0';
            }

            i++;
            j--;
        }

        return new String(ch);
    }

    // Function to find the Kth bit in S(n)
    public static char findKthBit(int n, int k) {
        if (n == 1 && k == 1) {
            return '0';
        }

        // Start with the base string S(1) = "0"
        String prev_string = "0";

        // Build the binary strings from S(2) to S(n)
        for (int i = 2; i <= n; i++) {
            String curr_String = prev_string + "1" + reverseAndInvert(prev_string);
            prev_string = curr_String;
        }

        // Return the Kth bit (remember to use k-1 since string is 0-indexed)
        return prev_string.charAt(k - 1);
    }

    public static void main(String[] args) {
        int n = 4;
        int k = 11;

        System.out.println("OUTPUT: " + findKthBit(n, k));
    }
}
