import java.util.*;

class UnionFind {
    private int[] rank;
    private int[] parent;
    private int size;

    UnionFind(int size) {
        this.size = size;
        parent = new int[this.size];
        rank = new int[this.size];
        for (int i = 0; i < this.size; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int i) {
        if (parent[i] == i) return i;
        parent[i] = find(parent[i]);
        return parent[i];
    }

    public void union(int i, int j) {
        int i_parent = find(i);
        int j_parent = find(j);
        if (i_parent == j_parent) return;

        if (rank[i_parent] < rank[j_parent]) {
            parent[i_parent] = j_parent;
        } else if (rank[i_parent] > rank[j_parent]) {
            parent[j_parent] = i_parent;
        } else {
            parent[j_parent] = i_parent;
            rank[i_parent]++;
        }
    }
}

public class Power_Grid_Maintenance {

    public int[] processQueries(int c, int[][] connections, int[][] queries) {

        UnionFind uf = new UnionFind(c + 1);
        Map<Integer, TreeSet<Integer>> map = new HashMap<>();
        boolean[] onlineGrids = new boolean[c + 1];
        Arrays.fill(onlineGrids, true);
        List<Integer> result = new ArrayList<>();

        // Step 1: Union-Find for all connections
        for (int[] connection : connections) {
            uf.union(connection[0], connection[1]);
        }

        // Step 2: Build map of root → TreeSet of members
        for (int i = 1; i <= c; i++) {
            int root = uf.find(i);
            map.computeIfAbsent(root, key -> new TreeSet<>()).add(i);
        }

        // Step 3: Process queries
        for (int[] query : queries) {
            int flag = query[0];
            int grid = query[1];
            int root = uf.find(grid);

            if (flag == 1) {
                // Maintenance query
                if (onlineGrids[grid]) {
                    result.add(grid);
                } else {
                    TreeSet<Integer> set = map.get(root);
                    if (set == null || set.isEmpty()) result.add(-1);
                    else result.add(set.first());
                }
            } else {
                // Set offline
                if (onlineGrids[grid]) {
                    onlineGrids[grid] = false;
                    map.get(root).remove(grid);
                }
            }
        }

        int[] ans = new int[result.size()];
        for (int i = 0; i < result.size(); i++) ans[i] = result.get(i);
        return ans;
    }

    public static void main(String[] args) {
        int c = 6;
        int[][] connections = {{1,2},{2,3},{4,5}};
        int[][] queries = {
            {1,1},
            {2,2},
            {1,2},
            {1,5},
            {2,4},
            {1,4},
            {1,6}
        };

        int[] result = new Power_Grid_Maintenance().processQueries(c, connections, queries);
        System.out.println("OUTPUT: " + Arrays.toString(result));
    }
}
