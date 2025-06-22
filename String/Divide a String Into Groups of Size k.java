class Solution {
    public String[] divideString(String s, int k, char fill) {
        int n = s.length();
        int q = n / k;
        int r = n % k;

        String[] result = (r != 0) ? new String[q + 1] : new String[q];

        int idx = 0;
        for (int i = 0; i + k <= n; i += k) {
            result[idx++] = s.substring(i, i + k);
        }

        if (r != 0) {
            StringBuilder last = new StringBuilder(s.substring(n - r));
            for (int i = 0; i < k - r; i++) {
                last.append(fill);
            }
            result[idx] = last.toString();
        }

        return result;
    }
}
