import java.util.HashSet;

/**
 * Split_String_into_max_number_of_Unique_Strings
 */
public class Split_String_into_max_number_of_Unique_Strings {
    public static int result;
    public static int N;
    public static void solve(String s, int currIndex, HashSet<String> set, int currCount) {

        if(currIndex == N) {
            result = Math.max(result, currCount);
            return;
        }

        /* Slight Improvement for excluding Irrelevant Test Cases  */
        // Pruning
        if((currCount + (N - currIndex)) <= result) {
            return;
        }


        for(int i = currIndex; i < N; i++) {
            String currString = s.substring(currIndex, i + 1);

            if(!set.contains(currString)) {
                set.add(currString);
                solve(s, i + 1, set, currCount + 1);
                set.remove(currString);
            }
        }

    } 

    public static int maxUniqueSplit(String s) {
        N = s.length();
        result = Integer.MIN_VALUE;
        solve(s, 0, new HashSet<>(), 0);

        return result;

    }

    public static void main(String[] args) {
        String s = "ababccc";
        System.out.println("OUTPUT : "+maxUniqueSplit(s));
    }
}