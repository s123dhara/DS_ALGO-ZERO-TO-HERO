
public class Total_Waviness_of_Numbers_in_Range_II_MEMO_V2 {
    public int N;
    public String S;

    Long[][][] dpTotalNumbers;
    Long[][][] dpTotalWaviness;

    private long[] solve(int curr, int prevPrev, int prev, boolean isLimitedWithActualNumber, boolean isLeadingZeros) {
        if (curr == N) {
            return new long[] { 1, 0 };
        }

        if(!isLimitedWithActualNumber && !isLeadingZeros && prev >= 0 && prevPrev >= 0) {
            if(dpTotalNumbers[curr][prevPrev][prev] != null && dpTotalWaviness[curr][prevPrev][prev] != null) {
                return new long[] {
                        dpTotalNumbers[curr][prevPrev][prev],
                        dpTotalWaviness[curr][prevPrev][prev]
                };
            }
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

        if(!isLimitedWithActualNumber && !isLeadingZeros && prev >= 0 && prevPrev >= 0) {
            dpTotalNumbers[curr][prevPrev][prev] = totalNumbers;
            dpTotalWaviness[curr][prevPrev][prev] = totalWaviness;
        }

        return new long[] { totalNumbers, totalWaviness };
    }

    private long helper(long num) {
        if (num < 100) {
            return 0;
        }

        this.S = String.valueOf(num);
        this.N = this.S.length();

        // dp state : {N + 1 (MAX 10^15) maximum digits length will be 16, prevPrev (0-9), prev (0-9)
        this.dpTotalNumbers = new Long[16][10][10];
        this.dpTotalWaviness = new Long[16][10][10];

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
        System.out.println("output: " + new Total_Waviness_of_Numbers_in_Range_II_MEMO_V2().totalWaviness(num1, num2));
    }
}
