
public class Total_Waviness_of_Numbers_in_Range_II {
    public int N;
    public String S;

    private long[] solve(int curr, int prevPrev, int prev, boolean isLimitedWithActualNumber, boolean isLeadingZeros) {
        if (curr == N) {
            return new long[] { 1, 0 };
        }

        long totalNumbers = 0;
        long totalWaviness = 0;

        int limitDigit = isLimitedWithActualNumber ? (S.charAt(curr) - '0') : 9;

        for (int digit = 0; digit <= limitDigit; digit++) {
            boolean newIsLimitedWithActualNumber = isLimitedWithActualNumber && (digit == limitDigit);
            boolean newIsLeadingZeros = isLeadingZeros && (digit == 0);

            int newPrevPrev = prev;
            int newPrev = newIsLeadingZeros ? -1 : digit;

            long[] result = solve(curr + 1, newPrevPrev, newPrev, newIsLimitedWithActualNumber, newIsLeadingZeros);
            long remainingTotalNumbers = result[0];
            long remainingTotalWaviness = result[1];

            totalNumbers += remainingTotalNumbers;
            totalWaviness += remainingTotalWaviness;

            if (!newIsLeadingZeros && prevPrev >= 0 && prev >= 0) {
                boolean isPeak = (prevPrev < prev && prev > digit);
                boolean isValley = (prevPrev > prev && prev < digit);

                if (isPeak || isValley) {
                    totalWaviness += remainingTotalNumbers;
                }
            }
        }

        return new long[] { totalNumbers, totalWaviness };
    }

    private long helper(long num) {
        if (num < 100) {
            return 0;
        }

        this.S = String.valueOf(num);
        this.N = this.S.length();

        // { totalNumbes, totalWaviness }
        long[] result = solve(0, -1, -1, true, true);

        return result[1];
    }

    public long totalWaviness(long num1, long num2) {
        long X = helper(num2);
        long Y = helper(num1 - 1);

        return X - Y;
    }

    public static void main(String[] args) {
        int num1 = 120, num2 = 130;
        System.out.println("output: " + new Total_Waviness_of_Numbers_in_Range_II().totalWaviness(num1, num2));
    }
}
