public class Total_Waviness_of_Numbers_in_Range_II_MEMO {

    public int N;
    public String S;

    // Separate DP arrays (IMPORTANT FIX)
    long[][][][][] dpNumbers  = new long[20][11][11][2][2];
    long[][][][][] dpWaviness = new long[20][11][11][2][2];
    boolean[][][][][] vis     = new boolean[20][11][11][2][2];

    private long[] solve(int curr, int prevPrev, int prev,
                         boolean isLimitedWithActualNumber,
                         boolean isLeadingZeros) {

        if (curr == N) {
            return new long[]{1, 0};
        }

        int tight = isLimitedWithActualNumber ? 1 : 0;
        int lead  = isLeadingZeros ? 1 : 0;

        int pp = prevPrev + 1; // shift -1..9 → 0..10
        int p  = prev + 1;

        if (vis[curr][pp][p][tight][lead]) {
            return new long[]{
                    dpNumbers[curr][pp][p][tight][lead],
                    dpWaviness[curr][pp][p][tight][lead]
            };
        }

        long totalNumbers = 0;
        long totalWaviness = 0;

        int limitDigit = isLimitedWithActualNumber
                ? (S.charAt(curr) - '0')
                : 9;

        for (int digit = 0; digit <= limitDigit; digit++) {

            boolean newIsLimitedWithActualNumber =
                    isLimitedWithActualNumber && (digit == limitDigit);

            boolean newIsLeadingZeros =
                    isLeadingZeros && (digit == 0);

            int newPrevPrev = prev;
            int newPrev = newIsLeadingZeros ? -1 : digit;

            long[] result = solve(
                    curr + 1,
                    newPrevPrev,
                    newPrev,
                    newIsLimitedWithActualNumber,
                    newIsLeadingZeros
            );

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

        vis[curr][pp][p][tight][lead] = true;
        dpNumbers[curr][pp][p][tight][lead] = totalNumbers;
        dpWaviness[curr][pp][p][tight][lead] = totalWaviness;

        return new long[]{totalNumbers, totalWaviness};
    }

    private long helper(long num) {
        if (num < 100) {
            return 0;
        }

        S = String.valueOf(num);
        N = S.length();

        // reset visited
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 11; j++) {
                for (int k = 0; k < 11; k++) {
                    for (int a = 0; a < 2; a++) {
                        for (int b = 0; b < 2; b++) {
                            vis[i][j][k][a][b] = false;
                        }
                    }
                }
            }
        }

        long[] result = solve(0, -1, -1, true, true);
        return result[1];
    }

    public long totalWaviness(long num1, long num2) {
        return helper(num2) - helper(num1 - 1);
    }

    public static void main(String[] args) {
        int num1 = 120, num2 = 130;

        System.out.println(
                new Total_Waviness_of_Numbers_in_Range_II()
                        .totalWaviness(num1, num2)
        );
    }
}