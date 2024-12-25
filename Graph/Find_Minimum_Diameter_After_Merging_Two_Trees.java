import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/* It is a very good problem among Graph and the Main Conecept of this problem is Tree Diameter 
 * First Clear the concept of Tree Diameter then do this problem. 
 * Thank You Mik for better understanding and clearing the concept. 
 * Youtube Video Link : https://www.youtube.com/watch?v=uz_WISpySFs 
 */

public class Find_Minimum_Diameter_After_Merging_Two_Trees {

    public static Map<Integer, List<Integer>> buildAdjList(int[][] edges) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            // bi-directional Graph
            map.computeIfAbsent(u, key -> new ArrayList<>()).add(v);
            map.computeIfAbsent(v, key -> new ArrayList<>()).add(u);
        }

        return map;
    }

    public static int findDiameter(Map<Integer, List<Integer>> map) {
        // int[] farthestNode = new int[2]; // {farthestNode , Distance}
        /* Step-1 Find the farthest Node from a random node - 0 */
        int[] farthestNode = BFS(map, 0);

        // Step-2 the farthest Node we got abot is nothing but one end of the diameter
        // of adjList / tree

        // step-3 Find the farthestNode from the node we got above, that will be the
        // otehr end of aiameter == diameter
        int[] otherEndNode = BFS(map, farthestNode[0]); // {otherendNode, diameter}

        return otherEndNode[1];
    }

    public static int[] BFS(Map<Integer, List<Integer>> map, int source) {
        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, Boolean> visited = new HashMap<>();
        int distance = 0;
        int farthestNode = source;

        queue.offer(source);
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int currNode = queue.poll();

                if (visited.getOrDefault(currNode, false))
                    continue;
                visited.put(currNode, true);

                farthestNode = currNode;

                for (int nbr : map.getOrDefault(currNode, new ArrayList<>())) {
                    if (!visited.getOrDefault(nbr, false)) {
                        queue.offer(nbr);
                    }
                }
            }

            if (!queue.isEmpty()) {
                distance++;
            }
        }

        return new int[] { farthestNode, distance };

    }

    public static int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
        // Build Adjacency List for Both Trees
        Map<Integer, List<Integer>> map1 = buildAdjList(edges1);
        Map<Integer, List<Integer>> map2 = buildAdjList(edges2);

        // Compute the Diameter of Both AdjList
        int d1 = findDiameter(map1);
        int d2 = findDiameter(map2);

        // Calculate the longest path that spans across both trees
        int combined = (d1 + 1) / 2 + (d2 + 1) / 2 + 1;

        return Math.max(combined, Math.max(d1, d2));

    }

    public static void main(String[] args) {
        int[][] edges1 = { { 0, 1 }, { 0, 2 }, { 0, 3 } };
        int[][] edges2 = { { 0, 1 } };

        System.out.println("OUTPUT : " + minimumDiameterAfterMerge(edges1, edges2));

    }
}