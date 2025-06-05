import java.util.*;

public class Maximum_Candies_You_Can_Get_From_Boxes {

    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int result = 0;
        Set<Integer> visited = new HashSet<>();
        Set<Integer> foundBoxes = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        for (int box : initialBoxes) {
            foundBoxes.add(box);
            if (status[box] == 1) {
                queue.offer(box);
                visited.add(box);

                result += candies[box];
            }
        }

        while (!queue.isEmpty()) {
            int currBox = queue.poll();

            // Process contained boxes
            for (int innerBox : containedBoxes[currBox]) {
                foundBoxes.add(innerBox);
                if (status[innerBox] == 1 && !visited.contains(innerBox)) {
                    queue.offer(innerBox);
                    visited.add(innerBox);

                    result += candies[innerBox];
                }
            }

            // Process Keys
            for (int boxKey : keys[currBox]) {
                status[boxKey] = 1;
                if (foundBoxes.contains(boxKey) && !visited.contains(boxKey)) {
                    queue.offer(boxKey);
                    visited.add(boxKey);
                    result += candies[boxKey];
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // int[] status = { 1, 0, 0, 0, 0, 0 };
        // int[] candies = { 1, 1, 1, 1, 1, 1 };
        // int[][] keys = { { 1, 2, 3, 4, 5 }, {}, {}, {}, {}, {} };
        // int[][] containedBoxes = { { 1, 2, 3, 4, 5 }, {}, {}, {}, {}, {} };
        int[] status = { 1, 0, 1, 0 };
        int[] candies = { 7, 5, 4, 100 };
        int[][] keys = { {}, {}, { 1 }, {} };
        int[][] containedBoxes = { { 1, 2 }, { 3 }, {}, {} };
        int[] initialBoxes = { 0 };

        System.out.println("OUTPUT : " + new Maximum_Candies_You_Can_Get_From_Boxes().maxCandies(status, candies, keys,
                containedBoxes, initialBoxes));
    }
}
