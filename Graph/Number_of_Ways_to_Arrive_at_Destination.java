import java.util.*;
public class Number_of_Ways_to_Arrive_at_Destination {    
    public static int countPaths(int n, int[][] roads) {
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        int mod = (int)1_000_000_007;
        PriorityQueue<long[]> pq = new PriorityQueue<>((a,b) -> Long.compare(a[1], b[1]));
        //TreeMap<Integer, Integer> treeMap = new TreeMap<>();


        for(int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int w = road[2];

            map.computeIfAbsent(u, key -> new ArrayList<>()).add(new int[]{v, w});
            map.computeIfAbsent(v, key -> new ArrayList<>()).add(new int[]{u, w});
        }


        for(int key  : map.keySet()) {
            System.out.println("key = " + key);
            for(int[] arr : map.get(key)) {
                System.out.println(Arrays.toString(arr));
            }
        }

        long[] dist = new long[n];
        int[] dp = new int[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;
        dp[0] = 1;
        pq.offer(new long[] {0, 0});

        while(!pq.isEmpty()) {
            long curr[] = pq.poll();
            int u = (int)curr[0];
            long w = curr[1];


            System.out.println("CURRENT POLL NODE = " + u);
            if(w > dist[u]) {
                continue;
            }

            for(int[] pair : map.getOrDefault(u, new ArrayList<>())) {
                int v = pair[0];
                int weight = pair[1];

                System.out.println("destination of vertex " + v + " from vertex " + u );


                if(dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    dp[v] = dp[u];
                    pq.offer(new long[]{v, dist[v]});
                }else if(dist[u] + weight == dist[v]) {
                    dp[v] = (dp[v] + dp[u]) % mod;
                }

                System.out.println("updated dist  = " + Arrays.toString(dist));
            }
        }


        // System.out.println("ways  " + treeMap.toString());

        return dist[n - 1] == Integer.MAX_VALUE ? 0 : dp[n - 1];



    }

    public static void main(String[] args) {
        int n = 7;
        int[][] roads = { { 0, 6, 7 }, { 0, 1, 2 }, { 1, 2, 3 }, { 1, 3, 3 }, { 6, 3, 3 }, { 3, 5, 1 }, { 6, 5, 1 },
                { 2, 5, 1 }, { 0, 4, 5 }, { 4, 6, 2 } };


        System.out.println("OUTPUT : " + countPaths(n, roads));
    }
}
