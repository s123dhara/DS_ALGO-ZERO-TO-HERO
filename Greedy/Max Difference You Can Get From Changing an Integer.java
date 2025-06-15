class Solution {
    public int maxDiff(int num) {
        String st = num + ""; // Convert to String 
        char[] maxChar = st.toCharArray();
        char[] minChar = st.toCharArray();        
        int n = maxChar.length;

        // To track which digits we will replace
        char firstNonNineDigit = '$';
        char firstNonZeroDigit = '$';        
        char replaceMinWith = '$';

        for (int i = 0; i < n; i++) {
            // For max: find the first digit that is not 9 and mark it for replacement
            if (firstNonNineDigit == '$' && maxChar[i] != '9') {
                firstNonNineDigit = maxChar[i];
            }

            // For min: handle two cases
            // Case 1: if the first digit is not '1', replace it with '1'
            // Case 2: if the first digit is '1', find first digit (after it) not '0' or '1', replace it with '0'
            if (firstNonZeroDigit == '$') {
                if (i == 0 && minChar[i] != '1') {
                    firstNonZeroDigit = minChar[0];
                    replaceMinWith = '1';
                } else if (i > 0 && minChar[i] != '0' && minChar[i] != '1') {
                    firstNonZeroDigit = minChar[i];
                    replaceMinWith = '0';
                }
            }

            if (firstNonNineDigit != '$' && firstNonZeroDigit != '$') {
                break;
            }
        }

        // Replace all occurrences in both maxChar and minChar
        for (int i = 0; i < n; i++) {
            if (maxChar[i] == firstNonNineDigit) {
                maxChar[i] = '9';
            }
            if (minChar[i] == firstNonZeroDigit) {
                minChar[i] = replaceMinWith;
            }
        }

        int max = Integer.parseInt(new String(maxChar));
        int min = Integer.parseInt(new String(minChar));

        return max - min;
    }
}