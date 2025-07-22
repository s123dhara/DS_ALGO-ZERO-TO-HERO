import java.util.*;

public class Maximum_Matching_Of_Players_With_Trainers {

    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Thread t1 = new Thread(() -> Arrays.sort(players));
        Thread t2 = new Thread(() -> Arrays.sort(trainers));
        t1.start();
        t2.start();
        try {
            t1.join(); // Waits Until t1 Thread work done.
            t2.join(); // Waits Until t2 Thread work done.
        } catch (Exception e) {
        }

        int n = players.length;
        int m = trainers.length;

        int i = n - 1;
        int j = m - 1;

        int result = 0;
        while (j >= 0 && i >= 0) {
            if (players[i] <= trainers[j]) {
                result++;
                i--;
                j--;
            } else {
                i--;
            }
        }

        return result;

    }

    public static void main(String[] args) {
        int[] players = { 4, 7, 9 };
        int[] trainers = { 8, 2, 5, 8 };

        System.out.println("OUTPUT :  "
                + new Maximum_Matching_Of_Players_With_Trainers().matchPlayersAndTrainers(players, trainers));

        // Comparator<int[]> comparator = (a, b) -> {
        //     if (a[1] == b[1]) {
        //             return b[0] - a[0]; // If frequencies are the same, sort by value in descending order
        //         }
        //         return a[1] - b[1]; // Otherwise, sort by frequency in ascending order
        // };
        
        

    }
}