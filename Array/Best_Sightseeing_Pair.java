import java.util.Arrays;

public class Best_Sightseeing_Pair {

    public int maxScoreSightseeingPair(int[] values) {
        // (values[i] + i) + (values[j] - j)
        int n = values.length;
        int[] leftMaxValue = new int[n];
        int max = leftMaxValue[0] = values[0];
        int maxScore = 0; // return the result

        for (int i = 1; i < n; i++) {
            if (values[i] + i > max) {
                max = values[i] + i;
            }
            leftMaxValue[i] = max;
        }

        for (int i = 1; i < n; i++) {
            maxScore = Math.max(maxScore, leftMaxValue[i - 1] + values[i] - i);
        }

        return maxScore;
    }

    public static void main(String[] args) {
        int[] values = { 8, 1, 5, 2, 6 };
        Best_Sightseeing_Pair object = new Best_Sightseeing_Pair();
        System.out.println("ouput : " + object.maxScoreSightseeingPair(values));
    }
}