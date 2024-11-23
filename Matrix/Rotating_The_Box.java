import java.util.Arrays;

public class Rotating_The_Box {

    public static char[][] rotateTheBox(char[][] box) {
        int m = box.length;
        int n = box[0].length;

        // Step 1: Rotate the box clockwise by 90 degrees
        char[][] rotatedBox = new char[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rotatedBox[j][m - i - 1] = box[i][j];
            }
        }

        // Step 2: Apply gravity on the rotated box
        for (int j = 0; j < rotatedBox[0].length; j++) { // Iterate over columns
            int emptyPos = rotatedBox.length - 1; // Track the lowest empty position
            for (int i = rotatedBox.length - 1; i >= 0; i--) {
                if (rotatedBox[i][j] == '*') {
                    emptyPos = i - 1; // Reset empty position to just above obstacle
                } else if (rotatedBox[i][j] == '#') {
                    if (i < emptyPos) {
                        rotatedBox[emptyPos][j] = '#';
                        rotatedBox[i][j] = '.';
                        emptyPos--;
                    } else {
                        emptyPos = i - 1;
                    }
                }
            }
        }

        return rotatedBox;
    }

    public static void main(String[] args) {
        char[][] box = { { '#', '.', '*', '.' }, { '#', '#', '*', '.' } };
        char[][] result = rotateTheBox(box);
        System.out.println("Result:");
        for (char[] row : result) {
            System.out.println(Arrays.toString(row));
        }
    }
}
