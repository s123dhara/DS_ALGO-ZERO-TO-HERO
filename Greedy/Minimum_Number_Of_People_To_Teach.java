import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Minimum_Number_Of_People_To_Teach {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        int m = languages.length;

        // Build map: user -> set of languages
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int lang : languages[i]) {
                map.computeIfAbsent(i + 1, _ -> new HashSet<>()).add(lang);
            }
        }

        // Step 1: Find users involved in problematic friendships
        Set<Integer> problematicUsers = new HashSet<>();
        for (int[] friendship : friendships) {
            int u = friendship[0];
            int v = friendship[1];

            Set<Integer> langsU = map.get(u);
            Set<Integer> langsV = map.get(v);

            // Check if they share a language
            boolean canCommunicate = false;
            for (int lang : langsU) {
                if (langsV.contains(lang)) {
                    canCommunicate = true;
                    break;
                }
            }

            // If not, mark both users as problematic
            if (!canCommunicate) {
                problematicUsers.add(u);
                problematicUsers.add(v);
            }
        }

        // Step 2: Try teaching each language
        int result = Integer.MAX_VALUE;
        for (int lang = 1; lang <= n; lang++) {
            int count = 0;
            for (int user : problematicUsers) {
                if (!map.get(user).contains(lang)) {
                    count++;
                }
            }
            result = Math.min(result, count);
        }

        return result == Integer.MAX_VALUE ? 0 : result;
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] languages = { { 2 }, { 1, 3 }, { 1, 2 }, { 3 } };
        int[][] friendships = { { 1, 4 }, { 1, 2 }, { 3, 4 }, { 2, 3 } };

        System.out.println("Minimum number of people to teach: "
                + new Minimum_Number_Of_People_To_Teach().minimumTeachings(n, languages, friendships));
    }
}
