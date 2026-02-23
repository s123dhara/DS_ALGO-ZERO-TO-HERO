// 1461. Check If a String Contains All Binary Codes of Size K
class Solution {
    public boolean hasAllCodes(String s, int k) {

        if (s.length() - k + 1 < (1 << k))
            return false;

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i <= s.length() - k; i++) {
            String sub = s.substring(i, i + k);

            // convert binary string to integer
            int value = Integer.parseInt(sub, 2);

            set.add(value);
            if(set.size() == (1 << k)) {
                return true;
            }
        }

        return set.size() == (1 << k);
    }
}

// Approach - 2
class Solution {
    public boolean hasAllCodes(String s, int k) {
        if (s.length() - k + 1 < (1 << k))
            return false;

        Set<String> set = new HashSet<>();

        for (int i = 0; i <= s.length() - k; i++) {
            set.add(s.substring(i, i + k));
        }

        return set.size() == (1 << k);
    }
}
