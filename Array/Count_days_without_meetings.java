import java.util.*;

public class Count_days_without_meetings {

    public static int countDays(int days, int[][] meetings) {
        int n = meetings.length;
        Arrays.sort(meetings, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });

        for (int[] arr : meetings) {
            System.out.println(Arrays.toString(arr));
        }

        int result = 0;
        int last = 0;

        if(n > 1 && 1 < meetings[0][0]) {
            result += meetings[0][0] - 1;  
        }
        last = meetings[0][1];
    
        for (int i = 1; i < n; i++) {
            int u1 = meetings[i][0];
            int u2 = meetings[i][1];

            // int v1 = meetings[i + 1][0];
            // int v2 = meetings[i + 1][1];

            //Non-overlap 
            if(u1 > last + 1) {
                result += (u1 - last - 1);
                last = u2;
            }else {
                //overlap 
                last = Math.max(last, u2);
            }

            
        }                        
        if (last < days) {
            result += days - last;
        }
        

        return result;
    }

    public static void main(String[] args) {
        int days = 10;
        int[][] meetings = { { 5, 7 }, { 1, 3 }, { 9, 10 } };
        // int[][] meetings = { { 3, 4 }, { 4, 8 }, { 2, 5 }, { 3, 8 } };
        // int[][] meetings = { { 1, 6 } };

        System.out.println("OUTPUT : " + countDays(days, meetings));
    }
}
