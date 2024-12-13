import java.util.Arrays;

public class Find_Score_Of_An_Array_After_Marking_All_Elements {

    public static long findScore(int[] nums) {
        int n = nums.length;
        long result = 0;
        int[][] sortedArray = new int[n][2];
        for(int i = 0; i < n; i++) {
            sortedArray[i][0] = nums[i];
            sortedArray[i][1] = i;
        }

        boolean[] visited = new boolean[n];
        int allVisitedCount = 0;

        Arrays.sort(sortedArray, (a,b) -> {
            if(a[0] == b[0]) {
                return a[1] - b[1];
            }

            return a[0] - b[0];
        });

        for(int i = 0; i < n && allVisitedCount < n; i++) {
            int num = sortedArray[i][0];
            int index = sortedArray[i][1];

            if(visited[index]) continue;

            visited[index] = true;
            if(index - 1 >= 0 && !visited[index - 1]) {
                visited[index - 1] = true;
                allVisitedCount += 1;
            }

            if(index + 1 < n && !visited[index + 1]) {
                visited[index + 1] = true;
                allVisitedCount += 1;
            }

            System.out.println(num);
            result += num;
        }


        return result;
    }

    public static void main(String[] args) {
        int nums[] = { 2, 1, 3, 4, 5, 2 };
        System.out.println("OUTPUT : " + findScore(nums));
    }
}