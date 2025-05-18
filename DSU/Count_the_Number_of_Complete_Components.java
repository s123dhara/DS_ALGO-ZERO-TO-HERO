import java.util.*;

public class Count_the_Number_of_Complete_Components {
    public static int[] parent;
    public static int[] rank;
    public static int[] edgesCount;

    public static int find(int x) {
        // if (parent[x] == x) {
        //     return x;
        // } else {
        //     parent[x] = find(parent[x]); // Path compression
        //     return parent[x];
        // }
        if(parent[x] == x) {
            return x;
        }
        return find(parent[x]);
    }

    public static void union(int x, int y) {
        int x_parent = find(x);
        int y_parent = find(y);

        if (x_parent == y_parent) {
            return;
        }

        if (rank[x_parent] < rank[y_parent]) {
            parent[x_parent] = y_parent;
        } else if (rank[x_parent] > rank[y_parent]) {
            parent[y_parent] = x_parent;
        } else {
            parent[y_parent] = x_parent;
            rank[x_parent]++;
        }
    }

    public static int countCompleteComponents(int n, int[][] edges) {
        parent = new int[n];
        rank = new int[n];
        edgesCount = new int[n];

        // Initialize DSU
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        // Process edges and union components
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            union(x, y);
        }
        System.out.println("PARENT  = " + Arrays.toString(parent));
        // After union, count edges per component root
        for (int[] edge : edges) {
            int x = edge[0];
            int root = find(x);
            System.out.println("x = " + x);
            System.out.println("root = " + root);
            edgesCount[root]++;
        }
        System.out.println("Edge count   = " + Arrays.toString(edgesCount));

        // Count number of nodes in each component
        int[] freq = new int[n];
        for (int i = 0; i < n; i++) {
            int root = find(i);
            freq[root]++;
            // freq[parent[i]]++;
        }
        System.out.println("freq count   = " + Arrays.toString(freq));
        System.out.println("PARENT  = " + Arrays.toString(parent));

        int result = 0;
        for (int i = 0; i < n; i++) {
            if (freq[i] > 0) {
                int expectedEdges = freq[i] * (freq[i] - 1) / 2;
                if (edgesCount[i] == expectedEdges) {
                    result++;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int n = 4;
        // int[][] edges = { { 0, 1 }, { 0, 2 }, { 1, 2 }, { 3, 4 } };
        int[][] edges = { { 2,0 }, { 3,1 }, { 3,2 } };
        

        System.out.println("OUTPUT : " + countCompleteComponents(n, edges));
    }
}