import java.util.ArrayList;
import java.util.Arrays;

public class Painting_a_Grid_With_Three_Different_Colors {

    public static ArrayList<String> columnStates;
    public static char[] colors = { 'R', 'G', 'B' };
    public static int MOD = 1_000_000_007;

    public static int[][] t;

    public static void generateColumnStates(String currState, char prevChar, int l, int m) {
        if (l == m) {
            columnStates.add(currState);
            return;
        }

        for (char ch : colors) {
            if (ch == prevChar) {
                continue;
            }

            generateColumnStates(currState + ch, ch, l + 1, m); // <- fixed missing concat
        }
    }

    public static boolean isValidTransition(String a, String b) {
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == b.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static int solve(int remainingColumns, int prevIndex) {
        if (remainingColumns == 0) {
            return 1; // found one possible way
        }

        if (t[remainingColumns][prevIndex] != -1) {
            return t[remainingColumns][prevIndex];
        }

        int ways = 0;
        String prevState = columnStates.get(prevIndex);
        for (int i = 0; i < columnStates.size(); i++) {
            String currState = columnStates.get(i);
            if (isValidTransition(prevState, currState)) {
                ways = (ways + solve(remainingColumns - 1, i)) % MOD;
            }
        }

        return t[remainingColumns][prevIndex] = ways;
    }

    public static int colorTheGrid(int m, int n) {

        // Step-1: generate column states
        columnStates = new ArrayList<>();
        generateColumnStates("", '#', 0, m);

        // initialize memoization 2d array
        int totalStatesSize = columnStates.size();
        t = new int[n + 1][totalStatesSize];

        for (int[] arr : t) {
            Arrays.fill(arr, -1);
        }

        // Step-2: compute result
        int result = 0;
        for (int i = 0; i < columnStates.size(); i++) {
            result = (result + solve(n - 1, i)) % MOD;
        }

        return result;
    }

    public static void main(String[] args) {
        int m = 5;
        int n = 5;

        System.out.println("OUTPUT : " + colorTheGrid(m, n));
    }
}
