import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Maximum_Number_Of_K_divisible_Components {
    public static int K;
    public static int count;

    private static int dfs(Map<Integer, List<Integer>> map, int[] values, int currNode, int parent) {
        int sum = values[currNode];

        for (int node : map.getOrDefault(currNode, new ArrayList<>())) {
            if (node != parent) {
                sum += dfs(map, values, node, currNode);
            }
        }

        sum = sum % K; // because we add this line Intergers are overflowing from 32bit 

        if (sum % K == 0) {
            count += 1;        
            return 0; // this indicates that our current components are separated 
            /* So we wouldn't have computed the furthur calculating the sum. */
        }

        return sum; // Return the sum ultimate then check for next neighbour Sum. 
    }

    public static int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        K = k;
        count = 0;

        // Make A Graph
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int arr[] : edges) {
            int u = arr[0];
            int v = arr[1];
            map.computeIfAbsent(u, key -> new ArrayList<>()).add(v);
            map.computeIfAbsent(v, key -> new ArrayList<>()).add(u);
        }

        dfs(map, values, 0, -1);

        return count;
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] edges = { { 0, 2 }, { 1, 2 }, { 1, 3 }, { 2, 4 } };
        int[] values = { 1, 8, 1, 4, 4 };
        int k = 6;

        int result = maxKDivisibleComponents(n, edges, values, k);
        System.out.println("Maximum number of K divisible components: " + result);
    }
}