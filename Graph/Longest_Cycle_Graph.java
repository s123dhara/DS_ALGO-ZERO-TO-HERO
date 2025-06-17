import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Longest_Cycle_Graph {
    public int result;

    public boolean isCycleDFS(Map<Integer, List<Integer>> map, int curr, boolean[] visited, boolean[] inRecursion, int[] nodeDepth, int depth) {            
        visited[curr] = true;
        inRecursion[curr] = true;
        nodeDepth[curr] = depth;

        System.out.println("Visited Array = " + Arrays.toString(visited));
        System.out.println("Recursion Array = " + Arrays.toString(inRecursion));
        System.out.println("Depth = " + depth);
        System.out.println("Current Node = " + curr);
        System.out.println("");
        System.out.println("Map = " + map);
        System.out.println("");

        for (int v : map.getOrDefault(curr, new ArrayList<>())) {
            if (v == -1) {
                continue; // No edge
            }

            if (!visited[v]) {
                if (isCycleDFS(map, v, visited, inRecursion, nodeDepth, depth + 1)) {
                    return true;
                }
            } else if (inRecursion[v]) {
                // Found a cycle

                System.out.println("Found a cycle");
                System.out.println("Current Depth = " + depth);
                System.out.println("Node Depth = " + nodeDepth[v]);
                System.out.println("Depth - Node Depth = " + (depth - nodeDepth[v] + 1));
                result = Math.max(result, depth - nodeDepth[v] + 1);
                return true;
            }
        }

        inRecursion[curr] = false;
        return false;
    }

    public int longestCycle(int[] edges) {
        int n = edges.length;
        result = 0;

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int u = i;
            int v = edges[i];
            map.computeIfAbsent(u, _ -> new ArrayList<>()).add(v);
        }

        boolean[] visited = new boolean[n];
        
        int[] nodeDepth = new int[n];

        for (int i = 0; i < n; i++) {
            boolean[] inRecursion = new boolean[n];
            if (!visited[i]) {
                System.out.println("Call for Node(" + i + ")");
                isCycleDFS(map, i, visited, inRecursion, nodeDepth, 0);
                System.out.println("Call Ended for Node(" + i + ")");
            }
        }

        return result == 0 ? -1 : result;
    }

    /*
        T.C : O(N)
        * public int longestCycle(int[] edges) 
        {
            int n = edges.length;
            boolean[] visited = new boolean[n];
            int maxCycleLen = -1;

            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    Map<Integer, Integer> pathIndexMap = new HashMap<>();
                    int node = i;
                    int step = 0;

                    while (node != -1 && !visited[node]) {
                        pathIndexMap.put(node, step++);
                        visited[node] = true;
                        node = edges[node];
                    }

                    if (node != -1 && pathIndexMap.containsKey(node)) {
                        // Cycle detected
                        int cycleLen = step - pathIndexMap.get(node);
                        maxCycleLen = Math.max(maxCycleLen, cycleLen);
                    }
                }
            }

            return maxCycleLen;
        }
     */

    public static void main(String[] args) {
        // Example 1: Output = 3
        int[] edges = { 3, 3, 4, 2, 3 };

        // Example 2: Output = -1 (no cycles)
        // int[] edges = { 2, -1, 3, 1 };

        // int[] edges = { 3, 3, 0, 0, 7, 0, 7, 5 };

        System.out.println("OUTPUT = " + new Longest_Cycle_Graph().longestCycle(edges));
    }
}
