import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Count_Number_Of_Bad_Pairs {

    public static long countBadPairs(int[] nums) {
        int n = nums.length;
        int diff[] = new int[n];
        // HashMap<Integer, Integer> map = new HashMap<>();
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            diff[i] = nums[i] - i;
        }

        System.out.println(Arrays.toString(diff));

        long result = 0;

        long counter = 0;
        for (int i = 0; i < n; i++) {

            if (map.isEmpty()) {
                // map.put(diff[i], i);
                map.computeIfAbsent(diff[i], key -> new ArrayList<>()).add(i);
                counter++;
                continue;
            }

            System.out.println("BEFORE ADDING RESULT MAP SIZE = " + map.size());
            if (map.containsKey(diff[i])) {
                // result += map.size() - 1;
                result += counter - map.get(diff[i]).size();
                counter++;
                System.out.println("HI");
            } else {
                // result += map.size();
                result += counter;
                counter++;
            }

            System.out.println("current Map : \n" + map);
            System.out.println("current value = " + diff[i]);
            System.out.println("Result = " +result);
            map.computeIfAbsent(diff[i], key -> new ArrayList<>()).add(i);

        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = { 4, 1, 3, 3 };
        System.out.println("output = " + countBadPairs(nums));
    }

}


/*
    The Optimized way to solve this.
 * class Solution {
    public long countBadPairs(int[] nums) {
        int n = nums.length;
        long result = 0;        
        long counter = 0;  // counter to track the number of processed elements
        HashMap<Integer, Integer> map = new HashMap<>(); // Store counts instead of lists      
        
        for (int i = 0; i < n; i++) {
            int diff = nums[i] - i;
            
            // Count good pairs and calculate bad pairs
            result += counter - map.getOrDefault(diff, 0);

            // Update map for current difference
            map.put(diff, map.getOrDefault(diff, 0) + 1);
            
            counter++;
        }

        return result;
    }
}
 * 
 */


 /*
    This is process where you haven't take the counter variable. 
  * class Solution {
    public long countBadPairs(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = nums.length;        
        long ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            nums[i] = nums[i] - i;
            int cnt = map.getOrDefault(nums[i], 0);
            ans += (n - i) - cnt - 1;
            map.put(nums[i], cnt + 1);
        }

        return ans;
    }
}
  */