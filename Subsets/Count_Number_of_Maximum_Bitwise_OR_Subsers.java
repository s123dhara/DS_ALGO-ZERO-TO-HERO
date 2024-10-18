/**
 * Count_Number_of_Maximum_Bitwise_OR_Subsers
 */
import java.util.*;
public class Count_Number_of_Maximum_Bitwise_OR_Subsers {

    /*Approach - 2 : With MEMO + RECURSION + SUBSETS */

    public static int MAX;
    public static int N;

    public static int t[][]; // it states that Double Stage changes

    public static int solve(int[] nums, int currentIndex, int currentBitWiseOR) {
        if(currentIndex == N) {
            if(currentBitWiseOR == MAX) {
                return t[currentIndex][currentBitWiseOR]  = 1;
            }

            return t[currentIndex][currentBitWiseOR] = 0;
        }


        if(t[currentIndex][currentBitWiseOR] != -1) {
            //means : previously visited this stage then return the value 
            return t[currentIndex][currentBitWiseOR];
        }

        int takeCount = solve(nums, currentIndex + 1, currentBitWiseOR | nums[currentIndex]);
        int noTakeCount = solve(nums, currentIndex + 1, currentBitWiseOR);

        return t[currentIndex][currentBitWiseOR]  =  takeCount + noTakeCount;
    }


    public static int countMaxOrSubsets(int[] nums) {
        N = nums.length;        
        int maximumBitWiseOR = nums[0];
        for(int num : nums) {
            maximumBitWiseOR = maximumBitWiseOR | num;
        }
        MAX = maximumBitWiseOR; // Declare it global

        // Initalize the DP ARRAY to store the Stages of Repeatation
        t = new int[N + 1][maximumBitWiseOR + 1];
        for(int arr[] : t) {
            Arrays.fill(arr, -1); // Intialize every row and col with -1
        }

        int result = solve(nums, 0, 0);        
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {3,1,2,5};
        System.out.println("OUTPUT : "+countMaxOrSubsets(nums));
    }
}