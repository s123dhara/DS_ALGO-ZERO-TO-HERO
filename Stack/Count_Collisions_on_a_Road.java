import java.util.*;
public class Count_Collisions_on_a_Road {

    public int countCollisions(String directions) {
        int n = directions.length();
        int collisions = 0;
        int lowerBound = 0;
        int upperBound = n - 1;

        while(lowerBound < n && directions.charAt(lowerBound) == 'L') {
            lowerBound++;
        }

        while(upperBound >= 0 && directions.charAt(upperBound) == 'R') {
            upperBound--;
        }

        for(int i = lowerBound; i <= upperBound; i++) {
            if(directions.charAt(i) != 'S') {
                collisions++;
            }
        }

        return collisions;
        
    }

    public int countCollisions_v2(String directions) {
        int n = directions.length();
        int collisions = 0;
        char[] dirArray = directions.toCharArray();
        for(int i = 0; i < n; i++) {
            System.out.println("Processing car at index " + i + " with direction " + dirArray[i]);
            System.out.println("Current Directions: " + String.valueOf(dirArray));
            

            if(dirArray[i] == 'L') {
                int j = i - 1;
                while(j >= 0 && dirArray[j] == 'R') {
                    collisions += 2; // One collision for 'R' and one for 'L'
                    dirArray[j] = 'S'; // Change 'R' to 'S' after collision
                    dirArray[i] = 'S'; // Change 'L' to 'S' after collision
                    j--;
                }
                if(j >= 0 && dirArray[j] == 'S') {
                    collisions++; // Collision with stationary car
                }
            } else if(dirArray[i] == 'S') {
                int j = i - 1;
                while(j >= 0 && dirArray[j] == 'R') {
                    collisions++;
                    dirArray[j] = 'S'; // Change 'R' to 'S' after collision
                    dirArray[i] = 'S'; // Change 'S' remains 'S' after collision
                    j--;
                }
            }

            System.out.println("Collisions so far: " + collisions);
        }

        return collisions;
    }

    public static void main(String[] args) {
        String directions = "RLRSLL";
        Count_Collisions_on_a_Road solution = new Count_Collisions_on_a_Road();
        int result = solution.countCollisions(directions);
        System.out.println("OUTPUT : " + result);

    }
}
