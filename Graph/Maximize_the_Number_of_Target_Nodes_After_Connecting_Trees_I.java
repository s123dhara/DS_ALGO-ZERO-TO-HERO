import java.util.*;

public class Maximize_the_Number_of_Target_Nodes_After_Connecting_Trees_I {
    private int dfs(int curr, Map<Integer, List<Integer>> adj, int d, int parent) {
        if (d < 0)
            return 0;

        int count = 1; // count current node

        for (int neighbor : adj.getOrDefault(curr, new ArrayList<>())) {
            if (neighbor != parent) {
                count += dfs(neighbor, adj, d - 1, curr);
            }
        }

        return count;
    }

    public int[] findCount(int[][] edges, int d) {
        int N = edges.length + 1;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            map.computeIfAbsent(u, _ -> new ArrayList<>()).add(v);
            map.computeIfAbsent(v, _ -> new ArrayList<>()).add(u);
        }

        int[] result = new int[N];
        for (int i = 0; i < N; i++) {
            result[i] = dfs(i, map, d, -1);
        }

        return result;
    }

    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
         int N = edges1.length + 1;
        int[] result1 = findCount(edges1, k);
        int[] result2 = findCount(edges2, k - 1);


        int maxTargetNodesCount = Arrays.stream(result2).max().getAsInt();

        for(int i = 0; i < N; i++) {
            result1[i] += maxTargetNodesCount;
        }

        return result1;

    }

    public static void main(String[] args) {
        int[][] edges1 = { { 0, 1 }, { 0, 2 }, { 2, 3 }, { 2, 4 } };
        int[][] edges2 = { { 0, 1 }, { 0, 2 }, { 0, 3 }, { 2, 7 }, { 1, 4 }, { 4, 5 }, { 4, 6 } };
        int k = 2;

        int[] result = new Maximize_the_Number_of_Target_Nodes_After_Connecting_Trees_I().maxTargetNodes(edges1, edges2,
                k);

        System.out.println("OUTPUT : " + Arrays.toString(result));
    }
}