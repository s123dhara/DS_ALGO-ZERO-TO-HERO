import java.util.*;

public class Minimum_Cost_Walk_In_Weighted_Graph {

    public static int[] rank;
    public static int[] parent;

    // find with path compression
    public static int find(int i) {

        if (parent[i] == i) {
            return i;
        } else {
            parent[i] = find(parent[i]); // Compressed the path ...
            return parent[i];
        }
    }

    public static void union(int i, int j) {

        int i_parent = find(i);
        int j_parent = find(j);

        if (i_parent == j_parent) {
            return;
        }

        if (rank[i_parent] < rank[j_parent]) {

            parent[i_parent] = j_parent;

            // no update in rank

        } else if (rank[i_parent] > rank[j_parent]) {
            parent[j_parent] = i_parent;
            // no update in rank
        } else {

            parent[j_parent] = i_parent;
            rank[i_parent] += 1;
        }
    }

    public static int[] minimumCost(int n, int[][] edges, int[][] query) {
        parent = new int[n];
        rank = new int[n];
        int[] cost = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
            cost[i] = -1;
        }

        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            int parent_u = find(u);
            int parent_v = find(v);

            if(parent_u != parent_v) {
                union(parent_u, parent_v);
                cost[parent_u] &= cost[parent_v];
            }

            cost[parent_u] &= w;
        }

        int result[] = new int[query.length];
        for(int i = 0; i < query.length; i++) {
            int s = query[i][0];
            int d = query[i][1];

            int parent_s = find(s);
            int parent_d = find(d);

            if(s == d) {
                result[i] = 0;
            }
            else if(parent_s != parent_d) {
                result[i] = -1;                
            }else {
                result[i] = cost[parent_s];
            }
        }

        return result;
    }

    public static void main(String args[]) {
        int n = 5;
        int[][] edges = { { 0, 1, 7 }, { 1, 3, 7 }, { 1, 2, 1 } };
        int[][] query = { { 0, 3 }, { 3, 4 } };

        int[] answer = minimumCost(n, edges, query);
        System.out.println("Final output : " + Arrays.toString(answer));
    }
}
