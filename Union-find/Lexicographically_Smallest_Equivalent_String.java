class Solution {
    public int[] parent;

    public int find(int i) {
        if (parent[i] == i) {
            return i;
        }

        parent[i] = find(parent[i]);
        return parent[i];
    }

    public void union(int i, int j) {
        int i_parent = find(i);
        int j_parent = find(j);

        //check lexigraphically who is smaller
        int smaller = Math.min(i_parent, j_parent);
        int higher = Math.max(i_parent, j_parent);

        parent[higher] = smaller;
    }

    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        parent = new int[26];
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }

        int n = s1.length();
        int m = s2.length();

        if (n != m)
            return "";

        for (int i = 0; i < n; i++) {
            int x = s1.charAt(i) - 'a';
            int y = s2.charAt(i) - 'a';

            int x_parent = find(x);
            int y_parent = find(y);
            if(x_parent != y_parent) {
                union(x, y);
            }

        }

        StringBuilder sb = new StringBuilder(baseStr);
        for (int i = 0; i < baseStr.length(); i++) {
            char ch = baseStr.charAt(i);
            int root = find(ch - 'a');
            sb.setCharAt(i, (char) (root + 'a'));
        }

        return sb.toString();
    }
}