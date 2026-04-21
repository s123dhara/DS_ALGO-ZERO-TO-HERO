import java.util.*;
public class Minimize_Hamming_Distance_After_Swap_Operations {

    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        UnionFind uf = new UnionFind(n);
        for (int[] swap : allowedSwaps) {
            uf.union(swap[0], swap[1]);
        }

        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = uf.find(i);
            map.computeIfAbsent(root, key -> new HashMap<>()).merge(source[i], 1, Integer::sum);
        }

        int hammingDistance = 0;
        for (int i = 0; i < n; i++) {
            int root = uf.find(i);
            Map<Integer, Integer> countMap = map.get(root);
            
            if (countMap.getOrDefault(target[i], 0) > 0) {
                countMap.merge(target[i], -1, Integer::sum);
            } else {
                hammingDistance++;
            }
        }

        return hammingDistance;        
    }


    public static void main(String[] args) {
        int[] source = {1, 2, 3, 4};
        int[] target = {2, 1, 4, 5};
        int[][] allowedSwaps = {{0, 1}, {2, 3}};

        System.out.println("OUTPUT : " + new Minimize_Hamming_Distance_After_Swap_Operations().minimumHammingDistance(source, target, allowedSwaps));
    }
}
