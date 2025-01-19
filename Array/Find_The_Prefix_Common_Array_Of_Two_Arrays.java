import java.util.Arrays;

public class Find_The_Prefix_Common_Array_Of_Two_Arrays {

    public static int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int count = 0;
        int[] result = new int[n];

        int[] freq = new int[n + 1];

        for (int i = 0; i < n; i++) {

            freq[A[i]] = Math.min(2, ++freq[A[i]]);
            freq[B[i]] = Math.min(2, ++freq[B[i]]);

            // System.out.println("Current Frequency of Array : " + Arrays.toString(freq));

            if (freq[A[i]] == 2 && A[i] == B[i]) {
                count += 1;
                result[i] = count;
                continue;
            }

            if (freq[A[i]] == 2) {
                count += 1;
            }

            if (freq[B[i]] == 2) {
                count += 1;
            }

            result[i] = count;
        }

        return result;
    }

    public static void main(String[] args) {
        // int A[] = { 1, 3, 2, 4 };
        int A[] = { 2, 3, 1 };
        // int B[] = { 3, 1, 2, 4 };
        int B[] = { 3, 1, 2 };

        System.out.println("OUTPUT : " + Arrays.toString(findThePrefixCommonArray(A, B)));

    }
}