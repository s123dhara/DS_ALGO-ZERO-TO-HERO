public class Sum_of_K_Mirror_Numbers {

    public StringBuilder convertToBaseK(long n, int k) {
        StringBuilder binary = new StringBuilder();
        while (n > 0) {
            long rem = n % k;
            binary.append(rem);
            n = n / k;
        }

        return binary.reverse();
    }

    public boolean isPalindrome(StringBuilder binary) {
        int i = 0;
        int j = binary.length() - 1;

        while (i < j) {
            if (binary.charAt(i) != binary.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public long kMirror(int k, int n) {
        long sum = 0;
        int length = 1;

        while (n > 0) {
            int halfLength = (length + 1) / 2;
            long minNum = (long) Math.pow(10, halfLength - 1);
            long maxNum = (long) Math.pow(10, halfLength) - 1;

            for (long num = minNum; num <= maxNum; num++) {
                String firstHalf = Long.toString(num);
                String secondHalf = new StringBuilder(firstHalf).reverse().toString();

                String fullPalindrome;
                if (length % 2 == 0) {
                    fullPalindrome = firstHalf + secondHalf;
                } else {
                    fullPalindrome = firstHalf + secondHalf.substring(1);
                }

                long palNum = Long.parseLong(fullPalindrome);
                StringBuilder baseK = convertToBaseK(palNum, k);

                if (isPalindrome(baseK)) {
                    sum += palNum;
                    n--;
                    if (n == 0) {
                        return sum;
                    }
                }
            }
            length++;
        }
        return sum;
    }


    /**
     * 
     *  // Brute Force Solution
     * 🔹 Let m be the number of total integers tried before finding n such numbers.
        Then the total time complexity is:

        O(m × log m)
        (where log m accounts for the base conversions and palindrome checks)
        
        class Solution {   
            public StringBuilder getKthBinaryString(int n, int k) {
                StringBuilder sb = new StringBuilder();
                while (n > 0) {
                    int rem = n % k;
                    sb.append(rem);
                    n = n / k;
                }
                return sb.reverse();  // Reverse to get correct base-k representation
            }
        
            public boolean isPallindrome(StringBuilder str) {
                int i = 0, j = str.length() - 1;
                while (i < j) {
                    if (str.charAt(i) != str.charAt(j)) return false;
                    i++;
                    j--;
                }
                return true;
            }
            public long kMirror(int k, int n) {
                long sum = 0;
                int i = 1;
                while (n > 0) {
                    StringBuilder baseK = getKthBinaryString(i, k);
                    if (isPallindrome(baseK) && isPallindrome(new StringBuilder(String.valueOf(i)))) {
                        sum += i;
                        n--;
                    }
                    i++;
                }
                return sum;
            }
        }
     */


    public static void main(String[] args) {
        int n = 5;
        int k = 2;

        System.out.println("OUTPUT : " + new Sum_of_K_Mirror_Numbers().kMirror(k, n));
    }
}