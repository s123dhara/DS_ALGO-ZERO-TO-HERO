// APPROACH -1 
class Solution {
   public static void floydWarshall(int[][] grid) {
        for (int via = 0; via < 26; via++) {
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    if (grid[i][via] != Integer.MAX_VALUE && grid[via][j] != Integer.MAX_VALUE) {
                        grid[i][j] = Math.min(grid[i][j], grid[i][via] + grid[via][j]);
                    }
                }
            }
        }
    }

    public static long minimumCost(String source, String target, char[] original, char[] changed, int[] costs) {
        // USING FLOYD WARSHALL
        int n = costs.length;
        int[][] grid = new int[26][26];

        // Initialize grid with infinity for all distances except self loops
        for (int i = 0; i < 26; i++) {
            Arrays.fill(grid[i], Integer.MAX_VALUE);
            grid[i][i] = 0; // distance to self is zero
        }

        // Fill grid with initial costs
        for (int k = 0; k < n; k++) {
            int i = original[k] - 'a';
            int j = changed[k] - 'a';
            grid[i][j] = Math.min(grid[i][j], costs[k]); 
        }

        floydWarshall(grid);

        long min_cost = 0;
        int m = source.length();
        for (int k = 0; k < m; k++) {
            int i = source.charAt(k) - 'a';
            int j = target.charAt(k) - 'a';

            if (grid[i][j] == Integer.MAX_VALUE) {
                return -1; // No valid conversion path
            }

            min_cost += grid[i][j];
        }

        return min_cost;
    }

}


// APPROACH -2
class Node{
    char dest;
    int currCost;

    Node(char dest, int cost){
        this.dest = dest;
        this.currCost = cost;
    }

}


class Solution {
    public static void dijkstra_modified(Map<Character, List<Node>> map, char src, int[][] distances) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> a.currCost - b.currCost);
        boolean[] visited = new boolean[26];
        int srcIdx = src - 'a';
        distances[srcIdx][srcIdx] = 0;

        pq.offer(new Node(src, 0));

        while (!pq.isEmpty()) {
            Node currNode = pq.poll();
            char u = currNode.dest;
            int uIdx = u - 'a';
            int d = currNode.currCost;

            if (visited[uIdx]) continue;
            visited[uIdx] = true;

            for (Node node : map.getOrDefault(u, new ArrayList<>())) {
                char v = node.dest;
                int vIdx = v - 'a';
                int w = node.currCost;

                if (d + w < distances[srcIdx][vIdx]) {
                    distances[srcIdx][vIdx] = d + w;
                    pq.offer(new Node(v, distances[srcIdx][vIdx]));
                }
            }
        }
    }

    public static long minimumCost(String source, String target, char[] original, char[] changed, int[] costs) {
        int n = costs.length;
        int m = source.length();
        long min_cost = 0;

        Map<Character, List<Node>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char src = original[i];
            char dest = changed[i];
            int cost = costs[i];

            map.computeIfAbsent(src, key -> new ArrayList<>()).add(new Node(dest, cost)); // directed map
        }

        int[][] distances = new int[26][26];

        // Initialize distances array with infinity
        for (int i = 0; i < 26; i++) {
            Arrays.fill(distances[i], Integer.MAX_VALUE);
        }

        // Run Dijkstra for each character in the source string
        for (int i = 0; i < m; i++) {
            dijkstra_modified(map, source.charAt(i), distances);
        }

        long ans = 0;
        for (int i = 0; i < m; i++) {
            if (source.charAt(i) == target.charAt(i)) continue;

            int idx_i = source.charAt(i) - 'a';
            int idx_j = target.charAt(i) - 'a';

            if (distances[idx_i][idx_j] == Integer.MAX_VALUE) {
                return -1; // No valid conversion path
            } else {
                ans += distances[idx_i][idx_j];
            }
        }

        return ans;
    }

}

//APPROCH-3 (TLE) 
class Node {
    char node;
    int cost;

    public Node(char node, int cost) {
        this.node = node;
        this.cost = cost;
    }
}
class Solution {
    public int[] dijsktra(Map<Character, List<Node>> map, char src, char des, int[] cost) {
        // Min-heap based on cost
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);

        // Distance array for 26 characters
        int[] dist = new int[26];
        Arrays.fill(dist, Integer.MAX_VALUE);

        // Visited set
        Set<Character> visited = new HashSet<>();

        // Start from source
        pq.offer(new Node(src, 0));
        dist[src - 'a'] = 0;

        while (!pq.isEmpty()) {
            Node currNode = pq.poll();
            char curr = currNode.node;
            int currCost = currNode.cost;

            // Skip if already visited
            if (visited.contains(curr)) continue;
            visited.add(curr);

            // If destination reached
            if (curr == des) {
                return new int[]{currCost, 1};
            }

            // Relax neighbors
            for (Node neighbor : map.getOrDefault(curr, new ArrayList<>())) {
                char next = neighbor.node;
                int nextCost = currCost + neighbor.cost;

                if (nextCost < dist[next - 'a']) {
                    dist[next - 'a'] = nextCost;
                    pq.offer(new Node(next, nextCost));
                }
            }
        }

        return new int[]{Integer.MAX_VALUE, 0}; // destination not reachable

    }
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        // Step 1: Build the Map for Graph 
        Map<Character, List<Node>> map = new HashMap<>();

        for(int i = 0; i < original.length; i++) {
            char src = original[i];
            char des = changed[i];
            int value = cost[i];
            map.computeIfAbsent(src, _ -> new ArrayList<>()).add(new Node(des, value));
        }

        // Traverse Each character in Source, can it reach to target
        // if each character can reach to target character, then find the cost 
        // if not, then return -1. finish the Traversal 

        // Declare the Variable for return the final Result
        long totalCost = 0;

        for(int i = 0; i < source.length(); i++) {
            char src = source.charAt(i);
            char des = target.charAt(i);

            // pairedResult[]{ minimumCostToReach, canPossible(0/1) };
            int[] pairedResult = dijsktra(map, src, des, cost); 
            int minimumCostToReach = pairedResult[0];
            int canPossible = pairedResult[1];

            if(canPossible != 1) {
                return -1;
            }
            
            totalCost += (long) minimumCostToReach;
        }

        return totalCost;
    }
}