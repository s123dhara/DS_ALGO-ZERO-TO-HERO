class Solution {
    public int possibleStringCount(String word) {
        int n = word.length();

        int count = 0;
        for(int i = 0; i < n - 1; i++) {
            // check consecutive Characters are matching,
            // then count += 1;
            if(word.charAt(i) == word.charAt(i + 1)) {
                count += 1;
            }
            // if not matched, then the character should be in String not multiple typed
            else  {
                continue;
            }
        }

        return count + 1;
    }
}